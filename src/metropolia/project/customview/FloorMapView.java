package metropolia.project.customview;

import java.util.ArrayList;

import metropolia.project.DAL.Floor;
import metropolia.project.metromap.MainActivity;
import metropolia.project.metromap.R;
import metropolia.project.metromap.SingleFloor;
import metropolia.project.utility.AnimationThread;
import metropolia.project.utility.EventHandler;
import metropolia.project.utility.MetroMapEvent;
import metropolia.project.utility.MetroMapSurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;

/*
 * Handles painting of images, animating and touch listeners
 */
public class FloorMapView extends MetroMapSurfaceView implements
		OnTouchListener, SurfaceHolder.Callback {

	private final int TOLERANCE = 100; // used to check if user has touched
										// close
										// enough to floor maps
	private final boolean DEBUG = true; // enables debug data to this view
	private final int Y_BOUNDARY_TOP = 0;
	private final int Y_BOUNDARY_BOTTOM = 1000;
	private final int MAP_X_POSITION = 200; // 100
	private final int MAP_Y_BOTTOMMOST_POSITION = 600; // 450
	private final int MAP_Y_SPACE_BETWEEN_FLOORS = 200; // 150

	private Paint mPaint;
	private Floor[] floor = new Floor[4];
	private GestureDetector mDetector;
	private AnimationThread aThread;
	private Context context;
	private OnTouchListener touchListener;
	private ArrayList<MetroMapEvent> alMMEvent = new ArrayList<MetroMapEvent>();

	public FloorMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initBall();

	}

	// Initializes animation thread and canvas paints
	private void initBall() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // reduce the
		mPaint.setColor(Color.BLACK); // jaggedness of lines in graphics
		mPaint.setTextSize(13);
		mPaint.setTypeface(Typeface.SANS_SERIF);

		// haha en tee tätä oikein hv
		addFloorMaps();

		getHolder().addCallback(this);
		aThread = new AnimationThread(getHolder(), this);

		this.setOnTouchListener(this);

		mDetector = new GestureDetector(getContext(),
				new SimpleOnGestureListener() {

					// check if user wants to select correct floor
					public boolean onSingleTapConfirmed(MotionEvent e) {
						alMMEvent.clear();
						MetroMapEvent mme = new MetroMapEvent(
								EventHandler.TYPE_PASS_POINT, (int) e.getX(),
								(int) e.getY());
						alMMEvent.add(mme);
						checkIfMapPress(mme);
						return true;
					}

					/*
					 * (non-Javadoc) Used to get motion from flings and
					 * assigning new target locations for floors
					 * 
					 * @see
					 * android.view.GestureDetector.SimpleOnGestureListener#
					 * onFling(android.view.MotionEvent,
					 * android.view.MotionEvent, float, float)
					 */
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {

						handleFling(velocityY);

						invalidate();
						return true;
					}

				});

	}

	/*
	 * Does drawing to canvas, called from animation thread
	 */
	public void doDraw(Canvas canvas, ArrayList<MetroMapEvent> e) {
		canvas.drawColor(Color.WHITE);

		EventHandler eh = new EventHandler(e);
		MetroMapEvent eventTime = eh.resolveEvent(EventHandler.TYPE_PASS_TIME);
		MetroMapEvent eventLocation = eh
				.resolveEvent(EventHandler.TYPE_PASS_POINT);

		if (DEBUG) {

			if (eventTime != null) {
				canvas.drawText(String.valueOf("ms: " + eventTime.getTime()),
						20, 20, mPaint);
			}

			if (eventLocation != null) {
				canvas.drawText(
						String.valueOf("x: " + eventLocation.getLocation().x),
						20, 40, mPaint);
				canvas.drawText(
						String.valueOf("y: " + eventLocation.getLocation().y),
						20, 60, mPaint);
			}

		}

		for (Floor f : floor) {
			canvas.drawBitmap(f.getPicture(), f.getLocation().x,
					f.getLocation().y, mPaint);
		}
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		for (Floor f : floor) {
			canvas.drawBitmap(f.getPicture(), f.getLocation().x,
					f.getLocation().y, mPaint);
		}
	}

	/*
	 * called once in each animation cycle
	 */
	public void tick() {
		for (Floor f : floor) {
			f.move();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if (!aThread.isAlive()) {
			aThread = new AnimationThread(getHolder(), this);
		}
		aThread.setRunning(true);
		aThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		aThread.setRunning(false);
		while (retry) {
			try {
				aThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	public boolean onTouch(View v, MotionEvent event) {
		mDetector.onTouchEvent(event);
		int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {
			invalidate();
		}
		return true;
	}

	/*
	 * Initializes Floor objects with places and relates .png files
	 */
	public void addFloorMaps() {

		for (int i = 0; i < 4; i++) {
			floor[i] = new Floor(
					new Point(
							MAP_X_POSITION,
							(MAP_Y_BOTTOMMOST_POSITION - (MAP_Y_SPACE_BETWEEN_FLOORS * i))),
					i);
		}

		floor[3].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor3);
		floor[3].picture = Bitmap.createScaledBitmap(floor[3].picture, 150,
				150, true);

		floor[2].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor2);
		floor[2].picture = Bitmap.createScaledBitmap(floor[2].picture, 150,
				150, true);

		floor[1].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor1);
		floor[1].picture = Bitmap.createScaledBitmap(floor[1].picture, 150,
				150, true);

		floor[0].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor0);
		floor[0].picture = Bitmap.createScaledBitmap(floor[0].picture, 150,
				150, true);
	}

	// called by animation thread to get happened events
	public ArrayList<MetroMapEvent> getEvents() {
		return this.alMMEvent;
	}

	/*
	 * called by animation thread to check if user has pressed any floor icons
	 */
	public void checkIfMapPress(MetroMapEvent e) {
		for (Floor f : floor) {

			if (Math.abs(e.getLocation().x - f.getLocation().x) < TOLERANCE) {
				if (Math.abs(e.getLocation().y - f.getLocation().y) < TOLERANCE) {
					MainActivity ma = (MainActivity) context;
					ma.setTargetFloor(f.getFloorNumber());
					ma.changeFragment(new SingleFloor());
				}
			}
		}
	}

	/*
	 * Handles fling movement
	 */
	public void handleFling(float velocityY) {

		// when moving down
		if (velocityY > 0) {
			int velY = (int) velocityY / 10;
			int tempY = velY;
			int biggestY = floor[0].getLocation().y;
			Floor f = floor[0];

			// find out bottomost floor
			for (Floor fl : floor) {
				if (fl.getLocation().y > biggestY) {
					f = fl;
				}
			}

			// check if our fling would take it too far out
			if (f.getLocation().y + velY > Y_BOUNDARY_BOTTOM) {
				tempY = -Y_BOUNDARY_BOTTOM + f.getLocation().y;
				// Y_BOUNDARY_BOTTOM means screensize, should get this
				// dynamically but ->
				// LAZY. This checks if our fling would go totally overboard
			}

			velY = tempY;

			// set movement target for every floor
			for (Floor flo : floor) {
				flo.setTarget(new Point(flo.getLocation().x,
						(flo.getLocation().y + velY)));
			}// for

			// when moving up
		} else if (velocityY < 0) {
			int velY = (int) velocityY / 10;
			int tempY = velY;
			int smallestY = floor[0].getLocation().y;
			Floor f = floor[0];

			// find out topmost floor
			for (Floor fl : floor) {
				if (fl.getLocation().y < smallestY) {
					f = fl;
				}
			}

			// check if our fling would take it too far out
			if (f.getLocation().y - velY < Y_BOUNDARY_TOP) {
				tempY = -Y_BOUNDARY_TOP - f.getLocation().y;
				// 800 means screensize, should get this dynamically but ->
				// LAZY. This checks if our fling would go totally overboard
			}

			velY = tempY;

			for (Floor flo : floor) {
				flo.setTarget(new Point(flo.getLocation().x,
						(flo.getLocation().y + velY)));
			}// for
		}// if

	}// handleFling
}

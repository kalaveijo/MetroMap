package metropolia.project.customview;

import java.util.ArrayList;

import metropolia.project.DAL.NavigationDrawer;
import metropolia.project.DAL.NavigationDrawerItem;
import metropolia.project.DAL.SingleRoom;
import metropolia.project.metromap.MainActivity;
import metropolia.project.metromap.R;
import metropolia.project.metromap.Room;
import metropolia.project.utility.AnimationThread;
import metropolia.project.utility.EventHandler;
import metropolia.project.utility.Map;
import metropolia.project.utility.MetroMapEvent;
import metropolia.project.utility.MetroMapSurfaceView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

/*
 * Handles painting of images, animating and touch listeners
 */
public class SingleFloorView extends MetroMapSurfaceView implements
		OnTouchListener, SurfaceHolder.Callback {
	private final boolean DEBUG = false; // enables debug data to this view

	private Paint mPaint;
	private Map map;
	private GestureDetector mDetector;
	private AnimationThread aThread;
	private Context context;
	private OnTouchListener touchListener;
	private int currentFloor;
	private boolean sliderOpen = false;
	private NavigationDrawer naviDraw;
	private int sliderTolerance = 30;
	private int tolerance = 20;

	public SingleFloorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		MainActivity ma = (MainActivity) context;
		currentFloor = ma.getTargetFloor();
		initBall(ma.getTargetFloor());
		naviDraw = new NavigationDrawer(ma.getRoomManager(), ma);

		if (ma.getRoomShouldBeHighlighted() != null) {
			for (NavigationDrawerItem ndi : naviDraw.getNavItems()) {
				if (ndi.getRoom().getRoomName()
						.equals(ma.getRoomShouldBeHighlighted().getRoomName())) {
					ndi.getRoom().highlightRoom();
				}
			}

			ma.setRoomShouldBeHighlighted(null);
		}
	}

	// Initializes animation thread and canvas paints
	private void initBall(int floorNumber) {

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // reduce the
		mPaint.setColor(Color.BLACK); // jaggedness of lines in graphics
		mPaint.setTextSize(13);
		mPaint.setTypeface(Typeface.SANS_SERIF);
		loadPicture(floorNumber);

		getHolder().addCallback(this);
		aThread = new AnimationThread(getHolder(), this);

		this.setOnTouchListener(this);

		mDetector = new GestureDetector(getContext(),
				new SimpleOnGestureListener() {

					public boolean onDoubleTap(MotionEvent e) {
						invalidate();
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

						// megainputhack
						if (e1.getX() < NavigationDrawerItem.buttonSizeX) {
							handleDrawer(velocityX, velocityY, e1);
						} else {
							handleFling(velocityY);
						}

						invalidate();
						return true;
					}

					// used to check if user has pressed navigation drawer items
					public boolean onSingleTapConfirmed(MotionEvent e) {
						// check if press is close to drawers
						if (e.getX() < NavigationDrawerItem.buttonSizeX) {

							// checks if user pressed highlight button
							// if not, show room info
							SingleRoom sr = naviDraw.checkWhichRoom(e);
							if (e.getX() > NavigationDrawerItem.buttonSizeX / 1.5) {
								if (sr != null) {
									sr.highlightRoom();
								}
							} else {
								if (sr != null) {
									MainActivity ma = (MainActivity) context;
									ma.setTargetRoom(sr);
									ma.changeFragment(new Room());
								}
							}

						}

						/*
						 * // check if user has selected room from map
						 * MainActivity ma = (MainActivity) context; RoomManager
						 * rm = ma.getRoomManager();
						 * 
						 * for (SingleRoom sr : rm
						 * .findRoomsForSingleFloor(currentFloor)) { if
						 * (Math.abs(e.getX() - sr.getLocation().x) <=
						 * tolerance) { if (Math.abs(e.getY() -
						 * sr.getLocation().y) <= tolerance) {
						 * ma.setTargetRoom(sr); ma.changeFragment(new Room());
						 * } } }
						 */

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
			canvas.drawText(String.valueOf("Floor: " + currentFloor), 20, 40,
					mPaint);
		}
		map.draw(canvas);
		naviDraw.draw(canvas);
	}

	/*
	 * called once in each animation cycle
	 */
	public void tick() {
		naviDraw.move();
		map.move();
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

	// loads a picture depending on floornumber, returns true on success and
	// false on failure
	// notes on approved .svg files
	// https://code.google.com/p/svg-android/wiki/Tutorial
	public boolean loadPicture(int floornumber) {
		try {
			SVG svg;
			MainActivity ma = (MainActivity) context;
			switch (floornumber) {

			case 0:
				svg = SVGParser
						.getSVGFromResource(getResources(), R.raw.floor0);
				map = new Map(svg.getPicture(), floornumber,
						ma.getRoomManager());
				return true;

			case 1:
				svg = SVGParser
						.getSVGFromResource(getResources(), R.raw.floor1);
				map = new Map(svg.getPicture(), floornumber,
						ma.getRoomManager());
				return true;

			case 2:
				svg = SVGParser
						.getSVGFromResource(getResources(), R.raw.floor2);
				map = new Map(svg.getPicture(), floornumber,
						ma.getRoomManager());
				return true;

			case 3:
				svg = SVGParser
						.getSVGFromResource(getResources(), R.raw.floor3);
				map = new Map(svg.getPicture(), floornumber,
						ma.getRoomManager());
				return true;
			}

			return false;
		} catch (Exception e) {
			Log.d("picture load error:", e.getMessage());
			return false;
		}
	}

	public void changeFloor(int targetFloor) {
		currentFloor = targetFloor;
		loadPicture(targetFloor);
	}

	public void handleFling(float velocityY) {
		int velY = (int) velocityY / 10;
		// when moving down
		if (velocityY > 0) {

			map.setTarget(new Point(map.getPosition().x, map.getPosition().y
					+ velY), velY);
			// when going up
		} else if (velocityY < 0) {
			map.setTarget(new Point(map.getPosition().x, map.getPosition().y
					+ velY), velY);
		}

	}

	public void handleDrawer(float velocityX, float velocityY, MotionEvent e1) {

		// if fling is big enough on X direction
		Float x = e1.getX();
		if (e1.getX() < NavigationDrawerItem.buttonSizeX + sliderTolerance) {
			if (Math.abs(velocityX) > 200) {
				// when taking navidrawer out
				if (velocityX > 0) {
					if (!sliderOpen) {
						naviDraw.setTarget(true, 600);
						sliderOpen = true;
					}
				}
				// when putting navidrawer in
				if (velocityX < 0) {
					if (sliderOpen) {
						naviDraw.setTarget(true, -600);
						sliderOpen = false;
					}
				}

			}
		}

		// if slider is open, register y direction swipes
		if (sliderOpen) {
			if (velocityY > 200) {
				int newVelo = (int) velocityY / 10;
				naviDraw.setTarget(false, newVelo);
			}

			if (velocityY < -200) {
				int newVelo = (int) velocityY / 10;
				naviDraw.setTarget(false, newVelo);
			}
		}
	}

}

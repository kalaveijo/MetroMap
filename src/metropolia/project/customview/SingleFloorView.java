package metropolia.project.customview;

import metropolia.project.metromap.R;
import metropolia.project.metromap.R.raw;
import metropolia.project.utility.AnimationThread;
import metropolia.project.utility.MetroMapEvent;
import metropolia.project.utility.MetroMapSurfaceView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
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
	private final boolean DEBUG = true; // enables debug data to this view

	private Paint mPaint;
	private Picture map;
	private GestureDetector mDetector;
	private AnimationThread aThread;
	private Context context;
	private OnTouchListener touchListener;
	private int currentFloor;

	public SingleFloorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		currentFloor = 1;
		initBall(1);
	}

	public SingleFloorView(Context context, AttributeSet attrs, int floorNumber) {
		super(context, attrs);
		this.context = context;
		currentFloor = floorNumber;
		initBall(floorNumber);
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

						// go down
						if (velocityY > 0) {
							if (currentFloor == 0) {
								changeFloor(4);
							} else {
								changeFloor(currentFloor - 1);
							}
						} else if (velocityY < 0) { // go up
							if (currentFloor == 4) {
								changeFloor(1);
							} else {
								changeFloor(currentFloor + 1);
							}
						}

						invalidate();
						return true;
					}

				});

	}

	/*
	 * Does drawing to canvas, called from animation thread
	 */
	public void doDraw(Canvas canvas, MetroMapEvent e) {
		canvas.drawColor(Color.WHITE);
		if (DEBUG) {
			canvas.drawText(String.valueOf("ms: " + e.getTime()), 20, 20,
					mPaint);
			canvas.drawText(String.valueOf("Floor: " + currentFloor), 20, 40,
					mPaint);
		}
		canvas.drawPicture(map);
	}

	/*
	 * called once in each animation cycle
	 */
	public void tick() {

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
			switch (floornumber) {

			case 0:
				svg = SVGParser.getSVGFromResource(getResources(),
						R.raw.android);
				map = svg.getPicture();
				return true;

			case 1:
				svg = SVGParser.getSVGFromResource(getResources(),
						R.raw.android);
				map = svg.getPicture();
				return true;

			case 2:
				svg = SVGParser.getSVGFromResource(getResources(),
						R.raw.android);
				map = svg.getPicture();
				return true;

			case 3:
				svg = SVGParser.getSVGFromResource(getResources(),
						R.raw.android);
				map = svg.getPicture();
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
}

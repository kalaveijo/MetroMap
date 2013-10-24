package metropolia.project.metromap;

import java.util.ArrayList;

import metropolia.project.utility.MetroMapSurfaceView;
import metropolia.project.utility.MetroMapEvent;
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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;

/*
 * Handles painting of images, animating and touch listeners
 */
public class FloorMapView extends MetroMapSurfaceView implements OnTouchListener,
		SurfaceHolder.Callback {

	private final int TOLERANCE = 50; //used to check if user has touched close enough to floor maps
	private final boolean DEBUG = true; // enables debug data to this view
	
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

	//Initializes animation thread and canvas paints
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
			
					//check if user wants to select correct floor
					public boolean onSingleTapConfirmed(MotionEvent e) {
						alMMEvent.clear();
						MetroMapEvent mme = new MetroMapEvent((int) e.getX(), (int) e.getY());
						alMMEvent.add(mme);
						checkIfMapPress(mme);
						return true;
					}
					
					/*
					 * (non-Javadoc)
					 * Used to get motion from flings and assigning new target locations for floors
					 * @see android.view.GestureDetector.SimpleOnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
					 */
					public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
						if(velocityY > 0){
							
							for(Floor f : floor){
								f.setTarget(new Point(f.getLocation().x, (f.getLocation().y + 80)));
							}
							
						}else if(velocityY < 0){
							
							for(Floor f : floor){
								f.setTarget(new Point(f.getLocation().x, (f.getLocation().y - 80)));
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
		
		if(DEBUG){
			canvas.drawText(String.valueOf("ms: " + e.getTime()), 20, 20, mPaint);
			canvas.drawText(String.valueOf("x: " + e.getLocation().x), 20, 40, mPaint);
			canvas.drawText(String.valueOf("y: " + e.getLocation().y), 20, 60, mPaint);
		}
		
		for(Floor f : floor){
			canvas.drawBitmap(f.getPicture(), f.getLocation().x, f.getLocation().y, mPaint);
		}	
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		for(Floor f : floor){
			canvas.drawBitmap(f.getPicture(),f.getLocation().x, f.getLocation().y, mPaint);
		}	
	}

	/*
	 * called once in each animation cycle
	 */
	public void tick() {
		for(Floor f : floor){
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
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
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
	public void addFloorMaps(){
		
		for(int i = 0; i < 4; i++){
			floor[i] = new Floor(new Point(100, (450-(150*i))));
		}
		
		floor[3].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor3);
		floor[3].picture = Bitmap.createScaledBitmap(floor[3].picture, 150, 150, true);
		
		floor[2].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor2);
		floor[2].picture = Bitmap.createScaledBitmap(floor[2].picture, 150, 150, true);
		
		floor[1].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor1);
		floor[1].picture = Bitmap.createScaledBitmap(floor[1].picture, 150, 150, true);
		
		floor[0].picture = BitmapFactory.decodeResource(getResources(),
				R.drawable.floor0);
		floor[0].picture = Bitmap.createScaledBitmap(floor[0].picture, 150, 150, true);
	}
	
	//called by animation thread to get happened events
	public ArrayList<MetroMapEvent> getEvents(){
		return this.alMMEvent;
	}
	
	/*
	 * called by animation thread to check if user has pressed any floor icons
	 */
	public void checkIfMapPress(MetroMapEvent e){
		for(Floor f : floor){

			if(Math.abs(e.getLocation().x - f.getLocation().x) < TOLERANCE ){
				if(Math.abs(e.getLocation().y - f.getLocation().y) < TOLERANCE ){
					MainActivity ma = (MainActivity) context;
					ma.changeFragment(new SingleFloor());
				}
			}
		}
	}
	
}

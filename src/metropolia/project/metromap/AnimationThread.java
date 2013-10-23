package metropolia.project.metromap;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class AnimationThread extends Thread {
	
	private FloorMapView cv;
	private SurfaceHolder mHolder;
	private boolean mRun = false;
	private long startTime, lastTime = 0, sleepTime;
	private int i = 0;
	private long perioid = 20;

	public AnimationThread(SurfaceHolder sHolder, FloorMapView cv) {
		this.cv = cv;
		mHolder = sHolder;
	}

	public void setRunning(boolean run) {
		mRun = run;
	}

	public void run() {
		Canvas mCanvas = null;
		while (mRun) {
			mCanvas = mHolder.lockCanvas(); // Start editing the pixels in the
											// surface. The returned Canvas can
											// be used to draw into the
											// surface's bitmap
			if (mCanvas != null) {
				
				startTime = System.currentTimeMillis();

				cv.tick();			
				
				cv.doDraw(mCanvas, lastTime);
				mHolder.unlockCanvasAndPost(mCanvas); 
				lastTime = System.currentTimeMillis() - startTime;
				sleepTime = perioid - lastTime;
				
				if(sleepTime <= 0){
					sleepTime = 5;
				}
														// Finish editing pixels
														// in the surface. After
														// this call, the
														// surface's current
														// pixels will be shown
														// on the screen
			}
			try {
				Thread.sleep(lastTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

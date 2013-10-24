package metropolia.project.utility;

import metropolia.project.metromap.Floor;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

/*
 * Utility Class to bind all customviews under same editable superclass
 */
public class MetroMapSurfaceView extends SurfaceView {

	public MetroMapSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void doDraw(Canvas canvas, Long time) {

	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	public void tick(){
		
	}
	
}

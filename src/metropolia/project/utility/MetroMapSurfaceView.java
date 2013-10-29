package metropolia.project.utility;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/*
 * Utility Class to bind all customviews under same editable superclass
 */
public class MetroMapSurfaceView extends SurfaceView {

	public MetroMapSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void doDraw(Canvas canvas, ArrayList<MetroMapEvent> event) {

	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void tick() {

	}

	public ArrayList<MetroMapEvent> getEvents() {
		return new ArrayList<MetroMapEvent>();
	}

}

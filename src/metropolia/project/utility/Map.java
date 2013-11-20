package metropolia.project.utility;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.drawable.PictureDrawable;

public class Map {

	private Bitmap map;
	private Point position;
	private Point target;
	private ArrayList<MetroMapDrawable> list;
	private int floorNumber = 0;
	public final int SPEED = 1;

	public Map(Picture map, int floorNumber) {
		this.map = pictureDrawable2Bitmap(map);
		position = new Point(0, 0);
		target = position;
		list = new ArrayList<MetroMapDrawable>();
		this.floorNumber = floorNumber;
	}

	public void setTarget(Point target) {
		this.target = target;
	}

	public void draw(Canvas c) {
		Paint paint = new Paint();
		c.drawBitmap(map, position.x, position.y, paint);
		for (MetroMapDrawable mmd : list) {
			mmd.draw(c);
		}
	}

	// called by tick method
	public void move() {
		// when we need to go up
		if (position.y > target.y) {

			if (position.y - target.y > 60) {
				position.y = position.y - SPEED - 6;
			} else if (position.y - target.y > 20) {
				position.y = position.y - SPEED - 3;
			} else {
				position.y = position.y - SPEED;
			}

			// when we need to go down
		} else if (position.y < target.y) {

			if (target.y - position.y > 60) {
				position.y = position.y + SPEED + 6;
			} else if (target.y - position.y > 20) {
				position.y = position.y + SPEED + 3;
			} else {
				position.y = position.y + SPEED;
			}

		}
	}

	public void AddMetroMapDrawableToList(MetroMapDrawable o) {
		this.list.add(o);
	}

	public void RemoveMetroMapDrawableFromList(MetroMapDrawable o) {
		this.list.remove(o);
	}

	// Convert Picture to Bitmap
	private static Bitmap pictureDrawable2Bitmap(Picture picture) {
		PictureDrawable pd = new PictureDrawable(picture);
		Bitmap bitmap = Bitmap.createBitmap(pd.getIntrinsicWidth(),
				pd.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawPicture(pd.getPicture());
		return bitmap;
	}

	public Point getPosition() {
		return this.position;
	}
}

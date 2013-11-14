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
	private ArrayList<MetroMapDrawable> list;
	private int floorNumber = 0;

	public Map(Picture map, int floorNumber) {
		this.map = pictureDrawable2Bitmap(map);
		position = new Point(0, 0);
		list = new ArrayList<MetroMapDrawable>();
		this.floorNumber = floorNumber;
	}

	public void draw(Canvas c) {
		Paint paint = new Paint();
		c.drawBitmap(map, position.x, position.y, paint);
		for (MetroMapDrawable mmd : list) {
			mmd.draw(c);
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

}

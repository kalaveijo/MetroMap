package metropolia.project.DAL;

import java.util.ArrayList;

import metropolia.project.utility.RoomManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/*
 * Androidin oma navgivation drawer on niin paska että teen oman surfaceviewiin
 * yst, terv TP
 */
public class NavigationDrawer {

	private ArrayList<NavigationDrawerItem> navItems;
	private int posX = -NavigationDrawerItem.buttonSizeX;
	private int posY = 0;
	private int navListItemPadding = 5;
	private Point backgroundLocation = new Point(
			-NavigationDrawerItem.buttonSizeX, 0);
	private Point backgroundTarget = backgroundLocation;

	public NavigationDrawer(RoomManager roomManager) {
		// toDo creation of navitemList
		ArrayList<SingleRoom> roomList = roomManager.getRoomList();
		navItems = new ArrayList<NavigationDrawerItem>();

		for (SingleRoom sr : roomList) {
			navItems.add(new NavigationDrawerItem(sr, new Point(posX, posY)));
			posY = posY + NavigationDrawerItem.buttonSizeY + navListItemPadding;
		}
	}

	// collective sets movement target for all NavigationDrawerItems
	public void setTarget(boolean inXDirection, int value) {

		// when moving left/right
		if (inXDirection) {
			// when moving right
			if (value > 0) {
				value = NavigationDrawerItem.buttonSizeX;
			}
			// when moving left
			if (value < 0) {
				value = -NavigationDrawerItem.buttonSizeX;
			}
			backgroundTarget = new Point(backgroundLocation.x + value,
					backgroundLocation.y);
			for (NavigationDrawerItem navi : navItems) {
				Point loc = navi.getLocation();
				navi.setTargetLocation(new Point(loc.x + value, loc.y));
			}
			// when moving up/down
		} else {

			for (NavigationDrawerItem navi : navItems) {
				Point loc = navi.getLocation();
				navi.setTargetLocation(new Point(loc.x, loc.y + value));
			}
		}
	}

	public void draw(Canvas canvas) {

		// draw grey backgrounds for items
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		canvas.drawRect(new Rect(backgroundLocation.x, 0, backgroundLocation.x
				+ NavigationDrawerItem.buttonSizeX, 900), paint);

		// draw items
		for (NavigationDrawerItem navi : navItems) {
			navi.draw(canvas);
		}
	}

	public void move() {
		moveBackground();
		for (NavigationDrawerItem naviDrawItem : navItems) {
			naviDrawItem.move();
		}
	}

	public void moveBackground() {
		if (backgroundLocation.x != backgroundTarget.x) {
			// when moving right
			if (backgroundLocation.x < backgroundTarget.x) {
				backgroundLocation.x = backgroundLocation.x
						+ NavigationDrawerItem.speed;
			}
			// when moving left
			if (backgroundLocation.x > backgroundTarget.x) {
				backgroundLocation.x = backgroundLocation.x
						- NavigationDrawerItem.speed;
			}
		}
	}
}

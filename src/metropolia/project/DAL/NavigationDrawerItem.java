package metropolia.project.DAL;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/*
 * item object for NavigationDrawer
 */
public class NavigationDrawerItem {

	private Point location;
	private Point targetLocation;

	public Point getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Point targetLocation) {
		
		//checks if we are moving right
		if(targetLocation.x > location.x){
			if(targetLocation.x > 0){
				targetLocation.x = 0;
			}
		}
		
		//checks if we are moving left
		if(targetLocation.x < location.x){
			if(targetLocation.x < -buttonSizeX){
				targetLocation.x = -buttonSizeX;
			}
		}
		
		
		this.targetLocation = targetLocation;
	}

	private SingleRoom room;
	private String text;
	public static int buttonSizeX = 140;
	public static int buttonSizeY = 60;
	public static int speed = 10;

	public NavigationDrawerItem(SingleRoom singleRoom, Point location) {
		this.room = singleRoom;
		this.text = room.getRoomName();
		this.location = location;
		this.targetLocation = location;
	}

	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawRect(new Rect(location.x, location.y, location.x
				+ buttonSizeX, location.y + buttonSizeY), paint);
		paint.setColor(Color.WHITE);
		canvas.drawText(text, location.x + (buttonSizeX) / 4, location.y
				+ (buttonSizeY) / 2, paint);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	// used to move at targeted position
	// practically this is used to bring out navDraw from left
	public void move() {
		
		//check if buttons are out of boundaries and correct
		
		
		
		// move in x direction
		if (location.x != targetLocation.x) {
			// when moving right
			if (location.x < targetLocation.x) {
				location.x = location.x + speed;
			}
			// when moving left
			if (location.x > targetLocation.x) {
				location.x = location.x - speed;
			}
		} else {
			if(location.x < -buttonSizeX){
				location.x = -buttonSizeX;
			}
			
			if(location.x > 0){
				location.x = 0;
			}
		}
		// move in y direction
		if (location.y != targetLocation.y) {

			// when moving down
			if (location.y < targetLocation.y) {
				location.y = location.y + speed;
			}
			// when moving up
			if (location.y > targetLocation.y) {
				location.y = location.y - speed;
			}
		}
	}

	public SingleRoom getRoom() {
		return room;
	}

	public void setRoom(SingleRoom room) {
		this.room = room;
	}
}

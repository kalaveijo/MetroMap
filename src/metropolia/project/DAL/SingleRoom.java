package metropolia.project.DAL;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/*
 * Holds data for single room
 */
public class SingleRoom {

	private int pictureId;
	private Point location;
	private Point targetLocation;
	private Paint paintColor;
	private String roomName;
	private String status;
	private int capacity;
	private boolean availibility = false;;
	private ArrayList<String> equipment;
	public static int sizeX = 40, sizeY = 15;
	private int SPEED = 1;
	private int floor;

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.location = new Point(0, 0);
		this.targetLocation = this.location;
		this.paintColor = new Paint();
		this.paintColor.setColor(Color.WHITE);

		setEquipment(new ArrayList<String>());
	}

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity, Point location, int floor) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.location = location;
		this.targetLocation = this.location;
		this.paintColor = new Paint();
		this.paintColor.setColor(Color.WHITE);
		this.setFloor(floor);
		this.equipment = new ArrayList<String>();

		setEquipment(new ArrayList<String>());
	}

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity, ArrayList<String> equipment) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.setEquipment(equipment);
		this.location = new Point(0, 0);
		this.targetLocation = this.location;
		this.paintColor = new Paint();
		this.paintColor.setColor(Color.WHITE);
	}

	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawRect(new Rect(location.x, location.y - sizeY, location.x
				+ sizeX, location.y), paintColor);
		paint.setColor(Color.BLACK);
		canvas.drawText(roomName, location.x, location.y, paint);
	}

	public void move() {
		if (location.y > targetLocation.y) {

			if (location.y - targetLocation.y > 60) {
				location.y = location.y - SPEED - 6;
			} else if (location.y - targetLocation.y > 20) {
				location.y = location.y - SPEED - 3;
			} else {
				location.y = location.y - SPEED;
			}

			// when we need to go down
		} else if (location.y < targetLocation.y) {

			if (targetLocation.y - location.y > 60) {
				location.y = location.y + SPEED + 6;
			} else if (targetLocation.y - location.y > 20) {
				location.y = location.y + SPEED + 3;
			} else {
				location.y = location.y + SPEED;
			}

		}
	}

	public Point getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Point targetLocation) {
		this.targetLocation = targetLocation;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailibility() {
		return availibility;
	}

	public void setAvailibility(boolean availibility) {
		this.availibility = availibility;
	}

	public ArrayList<String> getEquipment() {
		return equipment;
	}

	public void setEquipment(ArrayList<String> equipment) {
		this.equipment = equipment;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Paint getPaintColor() {
		return paintColor;
	}

	public void setPaintColor(Paint paintColor) {
		this.paintColor = paintColor;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void highlightRoom() {

		if (paintColor.getColor() != Color.RED) {
			this.paintColor.setColor(Color.RED);
		} else {
			this.paintColor.setColor(Color.WHITE);
		}

	}

}

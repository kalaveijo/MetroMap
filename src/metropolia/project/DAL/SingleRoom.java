package metropolia.project.DAL;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/*
 * Holds data for single room
 */
public class SingleRoom {

	private int pictureId;
	private Point location;
	private Paint paintColor;
	private String roomName;
	private String status;
	private int capacity;
	private boolean availibility = false;;
	private ArrayList<String> equipment;
	public static int sizeX = 40, sizeY = 40;

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.location = new Point(0, 0);
		this.paintColor = new Paint();

		setEquipment(new ArrayList<String>());
	}
	
	public SingleRoom(int pictureId, String roomName, String status,
			int capacity, Point location) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.location = location;
		this.paintColor = new Paint();

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
		this.paintColor = new Paint();
	}

	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawRect(new Rect(location.x, location.y, location.x+sizeX, location.y+sizeY), paint);
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

}

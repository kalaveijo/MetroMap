package metropolia.project.DAL;

import java.util.ArrayList;

/*
 * Holds data for single room
 */
public class SingleRoom {

	private int pictureId;
	private String roomName;
	private String status;
	private int capacity;
	private boolean availibility = false;;
	private ArrayList<String> equipment;

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);

		setEquipment(new ArrayList<String>());
	}

	public SingleRoom(int pictureId, String roomName, String status,
			int capacity, ArrayList<String> equipment) {

		this.setPictureId(pictureId);
		this.setRoomName(roomName);
		this.setStatus(status);
		this.setCapacity(capacity);
		this.setEquipment(equipment);
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

}

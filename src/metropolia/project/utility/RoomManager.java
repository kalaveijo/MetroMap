package metropolia.project.utility;

import java.util.ArrayList;

import metropolia.project.DAL.SingleRoom;
import android.graphics.Canvas;

/*
 * Hold all rooms 
 */
public class RoomManager {

	private ArrayList<SingleRoom> roomList;

	public RoomManager() {
		this.roomList = new ArrayList<SingleRoom>();
	}

	public void addRoom(SingleRoom sr) {
		this.roomList.add(sr);
	}

	// returns null if index is out of boundaries
	public SingleRoom findRoomByIndex(int i) {
		if (i < roomList.size()) {
			return roomList.get(i);
		} else {
			return null;
		}
	}

	// returns null if no match
	public SingleRoom findRoomByName(String name) {
		for (SingleRoom sr : roomList) {
			if (sr.getRoomName() == name) {
				return sr;
			}
		}
		return null;
	}

	// draws all rooms into map
	public void draw(Canvas canvas) {
		for (SingleRoom sr : roomList) {
			sr.draw(canvas);
		}
	}
}

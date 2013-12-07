package metropolia.project.utility;

import java.util.ArrayList;

import metropolia.project.DAL.SingleRoom;
import metropolia.project.metromap.R;
import android.graphics.Canvas;
import android.graphics.Point;

/*
 * Hold all rooms 
 */
public class RoomManager {

	private ArrayList<SingleRoom> roomList;

	public ArrayList<SingleRoom> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<SingleRoom> roomList) {
		this.roomList = roomList;
	}

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
			if (sr.getRoomName().equals(name)) {
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

	// finds rooms for correct floor
	public ArrayList<SingleRoom> findRoomsForSingleFloor(int floorNumber) {
		ArrayList<SingleRoom> roomList = new ArrayList<SingleRoom>();
		for (SingleRoom sr : this.roomList) {
			if (sr.getFloor() == floorNumber) {
				roomList.add(sr);
			}
		}
		return roomList;
	}

	public void resetList() {
		roomList.clear();
	}

	/*
	 * Should fetch rooms dynamically, not going to do that to this project
	 */
	public void populateRoomList() {

		resetList();

		// floor3
		addRoom(new SingleRoom(R.drawable.rausku, "B302", "Reserved", 30,
				new Point(580, 135), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B310", "Reserved", 30,
				new Point(470, 175), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B311A", "Reserved", 30,
				new Point(470, 255), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B311B", "Reserved", 30,
				new Point(470, 360), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B303", "Reserved", 30,
				new Point(580, 255), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B304", "Reserved", 30,
				new Point(575, 360), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B305", "Reserved", 30,
				new Point(630, 335), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B306", "Reserved", 30,
				new Point(630, 365), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B307", "Reserved", 30,
				new Point(630, 395), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B308", "Reserved", 30,
				new Point(630, 425), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B315", "Reserved", 30,
				new Point(450, 460), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B316", "Reserved", 30,
				new Point(420, 500), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B317", "Reserved", 30,
				new Point(420, 540), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B318", "Reserved", 30,
				new Point(435, 585), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B320", "Reserved", 30,
				new Point(435, 625), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B321", "Reserved", 30,
				new Point(435, 665), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B309", "Reserved", 30,
				new Point(580, 500), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B327", "Reserved", 30,
				new Point(620, 790), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B332", "Reserved", 30,
				new Point(455, 940), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B333", "Reserved", 30,
				new Point(410, 940), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B334", "Reserved", 30,
				new Point(360, 940), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B335", "Reserved", 30,
				new Point(300, 940), 3));
		addRoom(new SingleRoom(R.drawable.rausku, "B336", "Reserved", 30,
				new Point(210, 940), 3));

		// FLOOR2
		addRoom(new SingleRoom(R.drawable.rausku, "B202", "Reserved", 30,
				new Point(315, 60), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B208", "Reserved", 30,
				new Point(220, 79), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B209", "Reserved", 30,
				new Point(220, 105), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B210", "Reserved", 30,
				new Point(220, 125), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B203", "Reserved", 30,
				new Point(315, 125), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B124", "Reserved", 30,
				new Point(220, 180), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B204", "Reserved", 30,
				new Point(315, 180), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B205", "Reserved", 30,
				new Point(315, 240), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B220", "Reserved", 30,
				new Point(218, 280), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B221", "Reserved", 30,
				new Point(218, 340), 2));

		addRoom(new SingleRoom(R.drawable.rausku, "B223", "Reserved", 30,
				new Point(225, 370), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B222", "Reserved", 30,
				new Point(260, 370), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B224", "Reserved", 30,
				new Point(187, 370), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B225", "Reserved", 30,
				new Point(140, 370), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B226", "Reserved", 30,
				new Point(120, 350), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B227", "Reserved", 30,
				new Point(90, 370), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B234", "Reserved", 30,
				new Point(17, 360), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B235", "Reserved", 30,
				new Point(17, 390), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B236", "Reserved", 30,
				new Point(17, 410), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B237", "Reserved", 30,
				new Point(17, 440), 2));

		addRoom(new SingleRoom(R.drawable.rausku, "B240", "Reserved", 30,
				new Point(37, 490), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B241", "Reserved", 30,
				new Point(57, 510), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B242", "Reserved", 30,
				new Point(135, 490), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B243", "Reserved", 30,
				new Point(178, 490), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B244", "Reserved", 30,
				new Point(225, 490), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B214", "Reserved", 30,
				new Point(225, 445), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "B213", "Reserved", 30,
				new Point(330, 445), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.107", "Reserved", 30,
				new Point(250, 540), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.108", "Reserved", 30,
				new Point(295, 540), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.109", "Reserved", 30,
				new Point(250, 600), 2));

		addRoom(new SingleRoom(R.drawable.rausku, "2.110", "Reserved", 30,
				new Point(295, 570), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.117", "Reserved", 30,
				new Point(295, 600), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.118", "Reserved", 30,
				new Point(300, 607), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.119", "Reserved", 30,
				new Point(300, 618), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.124", "Reserved", 30,
				new Point(210, 635), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.123", "Reserved", 30,
				new Point(220, 670), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.127", "Reserved", 30,
				new Point(293, 664), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.128", "Reserved", 30,
				new Point(293, 684), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.130", "Reserved", 30,
				new Point(360, 670), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.131", "Reserved", 30,
				new Point(395, 670), 2));

		addRoom(new SingleRoom(R.drawable.rausku, "2.132", "Reserved", 30,
				new Point(450, 670), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.111", "Reserved", 30,
				new Point(420, 635), 2));
		addRoom(new SingleRoom(R.drawable.rausku, "2.134", "Reserved", 30,
				new Point(435, 625), 2));

		// floor1

		addRoom(new SingleRoom(R.drawable.rausku, "B120", "Reserved", 30,
				new Point(180, 107), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B121", "Reserved", 30,
				new Point(180, 124), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B118", "Reserved", 30,
				new Point(210, 124), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B112", "Reserved", 30,
				new Point(250, 107), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B109", "Reserved", 30,
				new Point(286, 107), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B111", "Reserved", 30,
				new Point(255, 127), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B115", "Reserved", 30,
				new Point(278, 170), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B113", "Reserved", 30,
				new Point(278, 215), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B116", "Reserved", 30,
				new Point(280, 260), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B124", "Reserved", 30,
				new Point(200, 245), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B138", "Reserved", 30,
				new Point(80, 365), 1));

		addRoom(new SingleRoom(R.drawable.rausku, "B139", "Reserved", 30,
				new Point(17, 400), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B147", "Reserved", 30,
				new Point(88, 470), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B146", "Reserved", 30,
				new Point(120, 470), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B145", "Reserved", 30,
				new Point(143, 485), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B144", "Reserved", 30,
				new Point(165, 470), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B143", "Reserved", 30,
				new Point(195, 470), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B128", "Reserved", 30,
				new Point(280, 378), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B127", "Reserved", 30,
				new Point(280, 410), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "B131", "Reserved", 30,
				new Point(280, 450), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.127", "Reserved", 30,
				new Point(235, 700), 1));

		addRoom(new SingleRoom(R.drawable.rausku, "1.129", "Reserved", 30,
				new Point(235, 745), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.130", "Reserved", 30,
				new Point(235, 775), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.141", "Reserved", 30,
				new Point(235, 800), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.143", "Reserved", 30,
				new Point(235, 835), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.145", "Reserved", 30,
				new Point(235, 865), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.146", "Reserved", 30,
				new Point(230, 890), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.447", "Reserved", 30,
				new Point(255, 910), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.148", "Reserved", 30,
				new Point(285, 890), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.150", "Reserved", 30,
				new Point(325, 890), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.125", "Reserved", 30,
				new Point(370, 890), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.123", "Reserved", 30,
				new Point(420, 890), 1));

		addRoom(new SingleRoom(R.drawable.rausku, "1.149", "Reserved", 30,
				new Point(325, 850), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.124", "Reserved", 30,
				new Point(372, 850), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.122", "Reserved", 30,
				new Point(440, 910), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.121", "Reserved", 30,
				new Point(470, 890), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.120", "Reserved", 30,
				new Point(470, 860), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.118", "Reserved", 30,
				new Point(470, 840), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.117", "Reserved", 30,
				new Point(470, 800), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.111", "Reserved", 30,
				new Point(470, 765), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.112", "Reserved", 30,
				new Point(470, 739), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.109", "Reserved", 30,
				new Point(470, 706), 1));

		addRoom(new SingleRoom(R.drawable.rausku, "1.213", "Reserved", 30,
				new Point(485, 675), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.108", "Reserved", 30,
				new Point(413, 736), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.107", "Reserved", 30,
				new Point(413, 749), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.110", "Reserved", 30,
				new Point(413, 762), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.111", "Reserved", 30,
				new Point(413, 775), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.115", "Reserved", 30,
				new Point(413, 813), 1));

		addRoom(new SingleRoom(R.drawable.rausku, "1.128", "Reserved", 30,
				new Point(296, 726), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.132", "Reserved", 30,
				new Point(296, 738), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.133", "Reserved", 30,
				new Point(296, 749), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.138", "Reserved", 30,
				new Point(296, 763), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.137", "Reserved", 30,
				new Point(296, 776), 1));
		addRoom(new SingleRoom(R.drawable.rausku, "1.142", "Reserved", 30,
				new Point(296, 815), 1));

		// floo0

		addRoom(new SingleRoom(R.drawable.rausku, "0.158", "Reserved", 30,
				new Point(165, 400), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.155", "Reserved", 30,
				new Point(147, 430), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.132", "Reserved", 30,
				new Point(155, 494), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.133", "Reserved", 30,
				new Point(155, 540), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.134", "Reserved", 30,
				new Point(155, 635), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.143", "Reserved", 30,
				new Point(155, 780), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.148", "Reserved", 30,
				new Point(155, 850), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.135", "Reserved", 30,
				new Point(258, 594), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.137", "Reserved", 30,
				new Point(258, 616), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.138", "Reserved", 30,
				new Point(258, 636), 0));

		addRoom(new SingleRoom(R.drawable.rausku, "0.139", "Reserved", 30,
				new Point(258, 656), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.145", "Reserved", 30,
				new Point(258, 719), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.146A", "Reserved", 30,
				new Point(253, 739), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.146B", "Reserved", 30,
				new Point(253, 759), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.159", "Reserved", 30,
				new Point(230, 494), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.152", "Reserved", 30,
				new Point(283, 820), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.162", "Reserved", 30,
				new Point(267, 494), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.170", "Reserved", 30,
				new Point(307, 494), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.172", "Reserved", 30,
				new Point(345, 400), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.177", "Reserved", 30,
				new Point(400, 494), 0));

		addRoom(new SingleRoom(R.drawable.rausku, "0.101", "Reserved", 30,
				new Point(457, 574), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.112", "Reserved", 30,
				new Point(457, 593), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.113", "Reserved", 30,
				new Point(457, 613), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.116", "Reserved", 30,
				new Point(457, 638), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.115", "Reserved", 30,
				new Point(457, 690), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.121A", "Reserved", 30,
				new Point(454, 740), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.121B", "Reserved", 30,
				new Point(454, 763), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.129", "Reserved", 30,
				new Point(429, 813), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.128", "Reserved", 30,
				new Point(434, 850), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.125", "Reserved", 30,
				new Point(500, 860), 0));

		addRoom(new SingleRoom(R.drawable.rausku, "0.119", "Reserved", 30,
				new Point(544, 740), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.117", "Reserved", 30,
				new Point(544, 613), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.108", "Reserved", 30,
				new Point(544, 530), 0));
		addRoom(new SingleRoom(R.drawable.rausku, "0.185", "Reserved", 30,
				new Point(554, 462), 0));

		/*
		 * addRoom(new SingleRoom(R.drawable.rausku, "Big Dry", "Reserved",
		 * 30)); addRoom(new SingleRoom(R.drawable.nav_temp_button, "Ocean",
		 * "Reserved", 30));
		 */

	}
}

package metropolia.project.metromap;

import java.util.ArrayList;

import metropolia.project.DAL.SingleRoom;
import metropolia.project.utility.MetroMapFragment;
import metropolia.project.utility.RoomManager;
import metropolia.project.utility.WifiScanner;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {

	private int targetFloor = 0;
	private SingleRoom targetRoom;
	private WifiScanner client;
	private Thread t;
	private MetroMapFragment mmf;
	private MetroMapFragment navBar;
	private RoomManager roomManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		roomManager = new RoomManager();
		populateRoomList();
		targetRoom = roomManager.findRoomByIndex(0);
		// start wifiscanner
		// Context ct = getApplicationContext();
		// client = new WifiScanner(uiHandler, ct);
		// t = new Thread(client);
		// t.start();

		// start fragments
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Splash fragment = new Splash();
		Navigationbar nav = new Navigationbar();

		// passing reference to navbarfragment
		navBar = nav;
		Object[] o = new Object[1];
		o[0] = this;
		nav.giveParameters(o);

		SearchBar search = new SearchBar();
		
		fragmentTransaction.add(R.id.search, search);
		fragmentTransaction.add(R.id.main, fragment);
		fragmentTransaction.add(R.id.navbar, nav);

		fragmentTransaction.commit();
		changeFragment(new FloorMap());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Changes fragment
	public boolean changeFragment(MetroMapFragment f) {
		try {

			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.main, f);
			fragmentTransaction.commit();
			mmf = f;
			Object[] parameters = new Object[1]; // passing parameters and
													// bypassing constructor
			parameters[0] = this;
			f.giveParameters(parameters); // declared in MetroMapFragment

			return true;
		} catch (Exception e) {
			Log.d("Changing Fragment", e.getMessage());
			return false;
		}// catch
	}// changeFragment

	public void setTargetFloor(int i) {
		this.targetFloor = i;
	}

	public int getTargetFloor() {
		return this.targetFloor;
	}

	private Handler uiHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				// wifidata comes here
			}
		}
	};

	// handles 'back' button presses
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			changeFragment(new FloorMap());
		}
		return true;
	}

	/*
	 * Should fetch rooms dynamically, not going to do that to this project
	 */
	public void populateRoomList() {

		//floor3
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B302",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B310",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B311",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B303",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B304",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B305",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B306",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B307",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B308",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B315",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B316",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B317",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B318",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B320",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B321",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B309",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B327",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B332",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B333",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B334",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B335",
				"Reserved", 30, new Point(0,0),3));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B336",
				"Reserved", 30, new Point(0,0),3));
		
		//FLOOR2
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B202",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B208",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B209",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B210",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B203",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B124",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B204",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B205",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B220",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B221",
				"Reserved", 30, new Point(0,0),2));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B223",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B222",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B224",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B225",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B226",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B227",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B234",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B235",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B236",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B237",
				"Reserved", 30, new Point(0,0),2));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B240",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B241",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B242",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B243",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B244",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B214",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B213",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.107",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.108",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.109",
				"Reserved", 30, new Point(0,0),2));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.110",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.117",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.118",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.119",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.124",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.123",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.127",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.128",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.130",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.131",
				"Reserved", 30, new Point(0,0),2));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.132",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.111",
				"Reserved", 30, new Point(0,0),2));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "2.134",
				"Reserved", 30, new Point(0,0),2));
		
		//floor1
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B120",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B121",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B118",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B112",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B109",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B111",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B115",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B116",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B124",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B138",
				"Reserved", 30, new Point(0,0),1));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B139",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B147",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B146",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B145",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B144",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B143",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B128",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B127",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "B131",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.127",
				"Reserved", 30, new Point(0,0),1));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.129",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.130",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.141",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.145",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.146",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.447",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.148",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.150",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.125",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.123",
				"Reserved", 30, new Point(0,0),1));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.149",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.124",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.122",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.121",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.120",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.118",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.117",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.111",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.112",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.109",
				"Reserved", 30, new Point(0,0),1));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.213",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.108",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.107",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.110",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.111",
				"Reserved", 30, new Point(0,0),1));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "1.115",
				"Reserved", 30, new Point(0,0),1));
		
		//floo0
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.158",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.155",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.132",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.133",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.134",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.143",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.148",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.135",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.137",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.138",
				"Reserved", 30, new Point(0,0),0));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.139",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.145",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.146A",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.146B",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.159",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.152",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.162",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.170",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.172",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.177",
				"Reserved", 30, new Point(0,0),0));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.101",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.112",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.113",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.116",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.115",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.121A",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.121B",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.129",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.128",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.125",
				"Reserved", 30, new Point(0,0),0));
		
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.119",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.117",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.108",
				"Reserved", 30, new Point(0,0),0));
		roomManager.addRoom(new SingleRoom(R.drawable.rausku, "0.185",
				"Reserved", 30, new Point(0,0),0));
		
		
		/*roomManager.addRoom(new SingleRoom(R.drawable.rausku, "Big Dry",
				"Reserved", 30));
		roomManager.addRoom(new SingleRoom(R.drawable.nav_temp_button, "Ocean",
				"Reserved", 30));*/

	}

	public SingleRoom getTargetRoom() {
		return targetRoom;
	}

	public void setTargetRoom(SingleRoom targetRoom) {
		this.targetRoom = targetRoom;
	}

	public RoomManager getRoomManager() {
		return this.roomManager;
	}

}

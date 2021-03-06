package metropolia.project.metromap;

import metropolia.project.DAL.SingleRoom;
import metropolia.project.utility.MetroMapFragment;
import metropolia.project.utility.RoomManager;
import metropolia.project.utility.WifiScanner;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
	private SingleRoom roomShouldBeHighlighted = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		roomManager = new RoomManager();
		roomManager.populateRoomList();
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

	public SingleRoom getTargetRoom() {
		return targetRoom;
	}

	public void setTargetRoom(SingleRoom targetRoom) {
		this.targetRoom = targetRoom;
	}

	public RoomManager getRoomManager() {
		return this.roomManager;
	}

	public SingleRoom getRoomShouldBeHighlighted() {
		return roomShouldBeHighlighted;
	}

	public void setRoomShouldBeHighlighted(SingleRoom roomShouldBeHighlighted) {
		this.roomShouldBeHighlighted = roomShouldBeHighlighted;
	}

}

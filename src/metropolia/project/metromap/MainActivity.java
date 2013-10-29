package metropolia.project.metromap;

import metropolia.project.utility.MetroMapFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private int targetFloor = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		Splash fragment = new Splash();
		fragmentTransaction.add(R.id.activity_vg, fragment);
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
			fragmentTransaction.replace(R.id.activity_vg, f);
			fragmentTransaction.commit();
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
}

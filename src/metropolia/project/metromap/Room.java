package metropolia.project.metromap;

import metropolia.project.utility.MetroMapFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Room extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(
				R.layout.room_fragment, container, false);
		return myView;
	}

}

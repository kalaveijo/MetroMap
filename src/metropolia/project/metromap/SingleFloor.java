package metropolia.project.metromap;

import metropolia.project.metromap.R;
import metropolia.project.metromap.R.layout;
import metropolia.project.utility.MetroMapFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleFloor extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(R.layout.singlefloor_fragment, container, false);
		return myView;
	}
	
}

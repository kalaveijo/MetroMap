package metropolia.project.metromap;

import metropolia.project.utility.MetroMapFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Navigationbar extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		RelativeLayout myView = (RelativeLayout) inflater.inflate(
				R.layout.navigationbar_fragment, container, false);
		return myView;
	}

}

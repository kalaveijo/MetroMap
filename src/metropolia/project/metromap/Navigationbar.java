package metropolia.project.metromap;

import metropolia.project.utility.MetroMapFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Navigationbar extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(
				R.layout.navigationbar_fragment, container, false);
		return myView;
	}

	// listeners for buttons
	public void initializeListeners() {
		ImageButton buttonFloors = (ImageButton) getActivity().findViewById(
				R.id.button_floors);
		buttonFloors.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				ma.changeFragment(new FloorMap());
			}
		});

		ImageButton buttonLocate = (ImageButton) getActivity().findViewById(
				R.id.button_locate);
		buttonLocate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				ma.changeFragment(new SingleFloor());
			}
		});

		ImageButton buttonRoom = (ImageButton) getActivity().findViewById(
				R.id.button_rooms);
		buttonRoom.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				((InputMethodManager) getActivity().getSystemService(
						Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
						InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		});

	}

	// after views have been set
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initializeListeners();
	}
}

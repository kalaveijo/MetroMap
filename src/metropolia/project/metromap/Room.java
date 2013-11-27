package metropolia.project.metromap;

import metropolia.project.utility.MetroMapFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Room extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(
				R.layout.room_fragment, container, false);
		return myView;
	}

	// after views have been set
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setCorrectRoom();
	}

	private void setCorrectRoom() {
		MainActivity ma = (MainActivity) getActivity();
		TextView roomName = (TextView) ma.findViewById(R.id.roomtext);
		ImageView roomimage = (ImageView) ma.findViewById(R.id.roomimage);
		Button buttonEquipment = (Button) ma
				.findViewById(R.id.button_equipment);
		Button buttonCapacity = (Button) ma.findViewById(R.id.button_capacity);
		Button buttonAvailibility = (Button) ma
				.findViewById(R.id.button_availability);

	}

}
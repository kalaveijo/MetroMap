package metropolia.project.metromap;

import metropolia.project.DAL.SingleRoom;
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

	// fetches data from
	private void setCorrectRoom() {
		MainActivity ma = (MainActivity) getActivity();
		TextView roomName = (TextView) ma.findViewById(R.id.roomtext);
		ImageView roomImage = (ImageView) ma.findViewById(R.id.roomimage);
		Button showMap = (Button) ma.findViewById(R.id.button_show_map);

		// fetch correct roomdata
		SingleRoom sr = ma.getTargetRoom();

		roomName.setText(sr.getRoomName());
		roomImage.setImageResource(R.drawable.room_picture_real);

		showMap.setText("Show on Map");

		showMap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				SingleRoom sr = ma.getTargetRoom();
				ma.setRoomShouldBeHighlighted(sr);
				ma.setTargetFloor(sr.getFloor());
				ma.changeFragment(new SingleFloor());
			}
		});

	}
}

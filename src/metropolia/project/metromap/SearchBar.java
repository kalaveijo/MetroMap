package metropolia.project.metromap;

import metropolia.project.utility.RoomManager;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import metropolia.project.DAL.SingleRoom;

public class SearchBar extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(
				R.layout.search_fragment, container, false);
		return myView;
	}
	
	public void initializeListeners() {
		
		
		ImageButton buttonFloors = (ImageButton) getActivity().findViewById(
				R.id.button_search);
		buttonFloors.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText text = (EditText) getActivity().findViewById(R.id.edittext_search);
				doSearch(text.getText().toString());
			}
		});
		
	}
	
	private void doSearch(String query){
		MainActivity ma = (MainActivity) getActivity();
		RoomManager rm = (RoomManager) ma.getRoomManager();
		
		SingleRoom sr = rm.findRoomByName(query);
		if(sr != null){
			ma.setTargetRoom(sr);
			ma.changeFragment(new Room());
		}
	}
	
	// after views have been set
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			initializeListeners();
		}
}

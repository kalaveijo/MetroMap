package metropolia.project.metromap;

import metropolia.project.utility.RoomManager;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import metropolia.project.DAL.SingleRoom;
import android.view.KeyEvent;

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
				//close keyboard
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
			    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
			}
		});
		
		EditText editText = (EditText) getActivity().findViewById(R.id.edittext_search);
		
		editText.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		        if (actionId == EditorInfo.IME_ACTION_SEND) {
		        	EditText text = (EditText) getActivity().findViewById(R.id.edittext_search);
					doSearch(text.getText().toString());
					//close keyboard
					InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
				    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

		            handled = true;
		        }
		        return handled;
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

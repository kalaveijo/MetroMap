package metropolia.project.metromap;

import android.app.Fragment;

public class MetroMapFragment extends Fragment implements FragmentParameterPasser {

	private Object[] parameters;
	
	@Override
	public boolean giveParameters(Object[] o) {
		this.parameters = o;
		return true;
	}

}

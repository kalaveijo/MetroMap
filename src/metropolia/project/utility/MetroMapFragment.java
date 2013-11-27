package metropolia.project.utility;

import android.app.Fragment;

/*
 * utlity superclass for constructor bypassing
 */
public class MetroMapFragment extends Fragment implements
		FragmentParameterPasser {

	protected Object[] parameters;

	@Override
	public boolean giveParameters(Object[] o) {
		this.parameters = o;
		return true;
	}

}

package metropolia.project.metromap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Splash extends MetroMapFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedState) {
		LinearLayout myView = (LinearLayout) inflater.inflate(
				R.layout.splash_fragment, container, false);
		return myView;
	}

	/*
	 * loops for 2 secs without doing anything interesting used to show
	 * splashscreen before main program starts
	 */
	public void wasteTime() {
		boolean running = true;
		long startTime = System.currentTimeMillis();
		while (running) {

			if (System.currentTimeMillis() - startTime > 4000) {
				running = false;
			}

		}
	}
}

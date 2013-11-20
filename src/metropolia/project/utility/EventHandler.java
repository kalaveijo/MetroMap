package metropolia.project.utility;

import java.util.ArrayList;

/*
 * Resolves events given to and from different thread
 * Should be able to handle:
 * - Pause
 * - eventType definitions
 * - object transfer
 */
public class EventHandler {

	public final static int TYPE_WAIT = 0;
	public final static int TYPE_PASS_TIME = 1;
	public final static int TYPE_PASS_POINT = 2;
	private AnimationThread t;
	ArrayList<MetroMapEvent> alMME;

	public EventHandler(AnimationThread t, ArrayList<MetroMapEvent> e) {
		this.t = t;
		this.alMME = e;
	}

	public EventHandler(ArrayList<MetroMapEvent> e) {
		this.alMME = e;
	}

	/*
	 * Returns event with correct type Returns null if no events match
	 */
	public MetroMapEvent resolveEvent(int type) {

		MetroMapEvent mmEvent = null;

		if (alMME != null) {
			for (MetroMapEvent event : alMME) {
				switch (type) {
				case TYPE_WAIT:
					if (event.getType() == type)
						mmEvent = event;
					break;

				case TYPE_PASS_TIME:
					if (event.getType() == type)
						mmEvent = event;
					break;

				case TYPE_PASS_POINT:
					if (event.getType() == type)
						mmEvent = event;
					break;
				}
			}
		}
		return mmEvent;
	}
}

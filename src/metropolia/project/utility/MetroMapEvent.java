package metropolia.project.utility;

import android.graphics.Point;

/*
 * Generic data object used to pass event data to animation thread
 */
public class MetroMapEvent {

	private Point location = new Point(0, 0);
	private long time = 0;
	private int type;

	public MetroMapEvent(int type, int x, int y) {
		this.location = new Point(x, y);
		this.type = type;
	}

	public MetroMapEvent(int type, long time) {
		this.time = time;
		this.type = type;
	}

	public MetroMapEvent(int type) {
		this.type = type;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return this.time;
	}

	public int getType() {
		return this.type;
	}

	public Point getLocation() {
		return this.location;
	}

}

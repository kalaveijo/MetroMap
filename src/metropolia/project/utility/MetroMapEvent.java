package metropolia.project.utility;

import android.graphics.Point;

/*
 * Generic data object used to pass event data to animation thread
 */
public class MetroMapEvent {

	private Point location = new Point(0,0);
	private long time = 0;
	
	public MetroMapEvent(int x, int y){
		this.location = new Point(x,y);
	}
	
	public MetroMapEvent(long time){
		this.time = time;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return this.time;
	}
	
	public Point getLocation(){
		return this.location;
	}
	
}

package metropolia.project.metromap;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Floor {

	public final int SPEED = 1; //speed which moves floor towards target
	
	public Bitmap picture;
	private Point location;
	private Point target;
	
	public Floor(Point loc){
		this.location = loc;
		this.target = loc;
	}
	
	
	// called by tick() to move each floor object
	public void move(){
		if(target.y > location.y){
			location.y = location.y + SPEED;
		}else if(target.y < location.y){
			location.y = location.y - SPEED;
		}
	}
	
	/*
	 * Getters/Setters
	 */
	
	public void setLocation(Point newLoc){
		this.location = newLoc;
	}
	
	public Point getLocation(){
		return this.location;
	}
	
	public void setTarget(Point newLoc){
		this.target = newLoc;
	}
	
	public Point getTarget(){
		return this.target;
	}
	
	public Bitmap getPicture(){
		return this.picture;
	}
	
}

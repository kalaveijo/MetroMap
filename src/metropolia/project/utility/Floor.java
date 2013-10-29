package metropolia.project.utility;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Floor {

	public final int SPEED = 1; // speed which moves floor towards target

	public Bitmap picture;
	private Point location;
	private Point target;
	private int floorNumber;

	public Floor(Point loc, int floorNumber) {
		this.location = loc;
		this.target = loc;
		this.floorNumber = floorNumber;
	}

	// called by tick() to move each floor object
	public void move() {
		if (target.y > location.y) {

			if (target.y - location.y > 60) {
				location.y = location.y + SPEED + 6;
			} else if (target.y - location.y > 20) {
				location.y = location.y + SPEED + 3;
			} else {
				location.y = location.y + SPEED;
			}

		} else if (target.y < location.y) {
			if (location.y - target.y > 60) {
				location.y = location.y - SPEED - 6;
			} else if (location.y - target.y > 20) {
				location.y = location.y - SPEED - 3;
			} else {
				location.y = location.y - SPEED;
			}
		}
	}

	/*
	 * Getters/Setters
	 */

	public void setLocation(Point newLoc) {
		this.location = newLoc;
	}

	public Point getLocation() {
		return this.location;
	}

	public void setTarget(Point newLoc) {
		this.target = newLoc;
	}

	public Point getTarget() {
		return this.target;
	}

	public Bitmap getPicture() {
		return this.picture;
	}

	public int getFloorNumber() {
		return this.floorNumber;
	}
}

package asteroids.participants;

import static asteroids.Constants.SIZE;

import java.awt.Shape;
import java.awt.geom.Path2D;

import asteroids.Constants;
import asteroids.Controller;
import asteroids.Participant;
import asteroids.ParticipantCountdownTimer;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.ShipDestroyer;

public class ShipBullet extends Participant implements AsteroidDestroyer {

	private Shape outline;
	private Ship ship;
	private Controller controller;
    
	public ShipBullet(double x, double y, double velocity, double direction, double size){
		
		//this.controller = controller;
		ship = new Ship(SIZE / 2, SIZE / 2, -Math.PI / 2, controller);
		setPosition(ship.getXNose(), ship.getYNose());
        //setRotation(this.getRotation());
        setVelocity(Constants.BULLET_SPEED, 0);
        

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(1, 1);
        poly.lineTo(-1, 1);
        poly.lineTo(-1, -1);
        poly.lineTo(1, -1);
        poly.closePath();
        outline = poly;
        
        // Schedule an acceleration in two seconds
        new ParticipantCountdownTimer(this, "move", 2000);
		
	}
	@Override
	protected Shape getOutline() {
		// TODO Auto-generated method stub
		return outline;
	}

	@Override
	public void collidedWith(Participant p) {
		 if (p instanceof ShipDestroyer)
	        {
	            // Expire the bullet
	            Participant.expire(this);
	        }	
	}

}

package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersGame.State;
import Space_Invaders.SpaceInvadersObj;
import Space_Invaders.core.Sound;

import java.awt.geom.Rectangle2D;

public class ShipShot extends SpaceInvadersObj {

    public boolean hit;
    public long hitTime;
//    public Sound shootSound;
    public ShipShot(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
        collider = new Rectangle2D.Double(x, y, 6, 10);
        loadFrames("ship_shot.png", "ship_shot_destroyed.png");
//        shootSound = new Sound("/sounds/shoot.wav");
//        visible = false;
//        shootSound.loop();
    }

    @Override
    public void updatePlaying() {
        if (!visible) {
            return;
        }
        if (hit) {
            frame = frames[1];
            if (System.currentTimeMillis() - hitTime > 100) {
                visible = false;
            }
            return;
        }

        y -= 5;

        AlienShot alienShot =  game.checkCollision(this, AlienShot.class);
        if (alienShot != null && !alienShot.hit) {
            alienShot.hit();
            visible = false;
            return;
        }

        Shield shield =  game.checkCollision(this, Shield.class);
        if (shield != null && !shield.hit) {
            shield.hit();
            visible = false;
            return;
        }

        Alien alien = game.checkCollision(this, Alien.class);
        if (alien != null && !alien.hit) {
            alien.hit();
            visible = false;
            return;
        }

        Saucer saucer = game.checkCollision(this, Saucer.class);
        if (saucer != null) {
            saucer.hit();
            visible = false;
            return;
        }

        if (y < 25) {
            hit();
        }
    }

    public Boolean canShoot() {
        return !visible;
    }

    public void shoot(Double x, Double y) {

        this.x = x;
        this.y = y;
        visible = true;
        hit = false;
        frame = frames[0];
//        shootSound.play();
       }

    // broadcast messages

    @Override
    public void stateChanged() {
        if (game.state != State.PLAYING) {
            visible = false;
        }
    }

    public void hit() {
        hit = true;
        hitTime = System.currentTimeMillis();
    }

}

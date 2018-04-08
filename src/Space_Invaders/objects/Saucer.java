package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersObj;

import java.awt.geom.Rectangle2D;

public class Saucer extends SpaceInvadersObj {
    
    private double vx;
    
    private boolean showScore;
    private long showStartTime;
    private long showTime;
    
    private boolean hit;
    private long hitTime;
    private long showScoreTime;

    public Saucer(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
        vx = 1;
        collider = new Rectangle2D.Double(x, y, 24, 12);
        loadFrames("saucer.png", "saucer_destroyed_0.png", "saucer_destroyed_1.png");
        x = -50;
        reset();
    }

    @Override
    public void updatePlaying() {
        if (!visible && System.currentTimeMillis() - showStartTime > showTime) {
            visible = true;
        }
        
        if (!visible) {
            return;
        }
        
        if (hit) {
            if ((System.currentTimeMillis() - showScoreTime > 1000) && showScore) {
                reset();
                visible = false;
                if (vx < 0) {
                    x = -50;
                }
                else {
                    x = 360;
                }
                vx = vx * -1;
            }
            else if (!showScore && System.currentTimeMillis() - hitTime > 100) {
                showScore = true;
                showScoreTime = System.currentTimeMillis();
                frame = frames[2];
                game.addScore(200);
            }
            else if (!showScore) {
                frame = frames[1];
            }
            return;
        }
        
        x += vx;
        if ((x > 360 && vx > 0) || (x < -50 && vx < 0) ) {
            vx = vx * -1;
            reset();
            visible = false;
        }
    }
    
    // broadcast messages

    @Override
    public void stateChanged() {
        if (game.state != SpaceInvadersGame.State.READY) {
            vx = 1;
            x = -50;
            reset();
        }
        else if (game.state != SpaceInvadersGame.State.PLAYING && game.state != SpaceInvadersGame.State.HIT) {
            visible = false;
        }
    }    

    public void reset() {
        y = 25;
        hit = false;
        //visible = true;
        frame = frames[0];
        showStartTime = System.currentTimeMillis();
        showTime = (int) (10000 + 10000 * Math.random());
        showScore = false;
    }

    public void hit() {
        hit = true;
        hitTime = System.currentTimeMillis();
    }
    
}

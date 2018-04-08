package Space_Invaders.objects;


import Space_Invaders.core.Keyboard;
import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersGame.State;
import Space_Invaders.SpaceInvadersObj;
import Space_Invaders.core.Sound;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Ship extends SpaceInvadersObj {

    public ShipShot shipShot;
    public boolean hit;
    public long hitTime;
    public Sound shootSound;


    public Ship(SpaceInvadersGame game, ShipShot shipShot) {
        super(game);
        this.shipShot = shipShot;

    }

    @Override
    public void init() {
        x = 360 / 2 - 13;
        y = 290;
        collider = new Rectangle2D.Double(x, y, 26, 16);
        loadFrames("falcon.png", "ship_destroyed_0.png", "ship_destroyed_1.png");
        shootSound = new Sound("/sounds/shoot.wav");

    }

    @Override
    public void updatePlaying() {
        if (!visible) {
            return;
        }

        if (Keyboard.keyPressed[KeyEvent.VK_LEFT]) {
            x -= 4;
        } else if (Keyboard.keyPressed[KeyEvent.VK_RIGHT]) {
            x += 4;
        }
        // limit x movement
        x = x < 10 ? 10 : x;
        x = x > 324 ? 324 : x;

        if (Keyboard.keyPressed[KeyEvent.VK_SPACE] && shipShot.canShoot()) {
            shipShot.shoot(x + 10, y);
           shootSound.play();
        }
    }


    @Override
    public void updateClear() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    if (System.currentTimeMillis() - waitTime < 1500) {
                        break yield;
                    }
                    game.nextGame();
                    break yield;
            }
        }
    }

    @Override
    public void updateHit() {

        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    frame = frames[1 + ((int) (System.nanoTime() * 0.00000001) % 2)];
                    if (System.currentTimeMillis() - waitTime < 1000) {
                        break yield;
                    }
                    visible = false;
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 2;
                case 2:
                    if (System.currentTimeMillis() - waitTime < 1500) {
                        break yield;
                    }
                    frame = frames[0];
                    game.lives--;
                    if (game.lives == 0) {
                        game.setState(State.GAME_OVER);
                    } else {
                        game.setState(State.PLAYING);
                        visible = true;
                    }
                    break yield;
            }
        }
    }

    // broadcast messages

    @Override
    public void stateChanged() {
        if (game.state == State.TITLE) {
            x = 360 / 2 - 13;
        } else if (game.state == State.READY) {
            visible = true;
        } else if (game.state == State.HIT || game.state == State.CLEAR) {
            instructionPointer = 0;
        } else if (game.state == SpaceInvadersGame.State.GAME_OVER) {
            visible = false;
        }
    }

    public void hit() {
        hit = true;
        hitTime = System.currentTimeMillis();
    }

}

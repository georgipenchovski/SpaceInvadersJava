package Space_Invaders;

import Space_Invaders.core.Obj;

public class SpaceInvadersObj extends Obj<SpaceInvadersGame> {
    
    protected int instructionPointer;
    protected long waitTime;
    
    public SpaceInvadersObj(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void update() {
        switch (game.state) {
            case INITIALIZING: updateInitializing(); break;
            case OL_PRESENTS: updateOLPresents(); break;
            case TITLE: updateTitle(); break;
            case READY: updateReady(); break;
            case PLAYING: updatePlaying(); break;
            case HIT: updateHit(); break;
            case CLEAR: updateClear(); break;
            case GAME_OVER: updateGameOver(); break;
        }
    }

    public void updateInitializing() {
    }

    public void updateOLPresents() {
    }

    public void updateTitle() {
    }

    public void updateReady() {
    }

    public void updatePlaying() {
    }

    public void updateHit() {
    }

    public void updateClear() {
    }

    public void updateGameOver() {
    }
    
    // broadcast messages
    
    public void stateChanged() {
    }
    
}

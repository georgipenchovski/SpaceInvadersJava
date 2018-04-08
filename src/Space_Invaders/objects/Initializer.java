package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersObj;

public class Initializer extends SpaceInvadersObj {
    
    public Initializer(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
    }

    @Override
    public void updateInitializing() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    // game.setState(State.TITLE);
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    if (System.currentTimeMillis() - waitTime < 3000) {
                        break yield;
                    }
                    game.setState(SpaceInvadersGame.State.OL_PRESENTS);
                    break yield;
            }
        }
    }

    // broadcast messages
    
    @Override
    public void stateChanged() {
    }
    
}

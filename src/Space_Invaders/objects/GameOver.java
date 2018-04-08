package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersObj;

import java.awt.Graphics2D;

public class GameOver extends SpaceInvadersObj {
    
    private int gameOverTextIndex;
    
    public GameOver(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
    }
    
    @Override
    public void updateGameOver() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    gameOverTextIndex = 0;
                    instructionPointer = 1;
                case 1:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 2;
                case 2:
                    if (System.currentTimeMillis() - waitTime < 200) {
                        break yield;
                    }
                    if (gameOverTextIndex < 9) {
                        gameOverTextIndex++;
                        instructionPointer = 1;
                        break yield;
                    }
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 3;
                case 3:
                    if (System.currentTimeMillis() - waitTime < 3000) {
                        break yield;
                    }
                    game.updateHiscore();
                    game.clearScore();
                    game.setState(SpaceInvadersGame.State.TITLE);
                    break yield;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        g.scale(2, 2);
        String gameOverText = "GAME OVER".substring(0, gameOverTextIndex);
        game.drawText(g, gameOverText, 44, 60);
    }

    
    // broadcast messages
    
    @Override
    public void stateChanged() {
        visible = game.state == SpaceInvadersGame.State.GAME_OVER;
        if (visible) {
            instructionPointer = 0;
        }
    }
        
}

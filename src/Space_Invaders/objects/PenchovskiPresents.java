package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersGame.State;
import Space_Invaders.SpaceInvadersObj;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PenchovskiPresents extends SpaceInvadersObj {
    
    private BufferedImage image;
    private int currentLine = 21;
    
    public PenchovskiPresents(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
        image = new BufferedImage(262, 21, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 262, 21);
        g.scale(1.66, 1.66);
        game.drawText(g, "PENCHOVSKI BROS.", 0, 0);
    }
    
    @Override
    public void updateOLPresents() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 1;
                case 1:
                    if (System.currentTimeMillis() - waitTime < 2) {
                        break yield;
                    }
                    if (currentLine >= 0) {
                        currentLine--;
                        instructionPointer = 0;
                        break yield;
                    }
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 2;
                case 2:
                    if (System.currentTimeMillis() - waitTime < 3000) {
                        break yield;
                    }
                    visible = false;
                    waitTime = System.currentTimeMillis();
                    instructionPointer = 3;
                case 3:
                    if (System.currentTimeMillis() - waitTime < 1000) {
                        break yield;
                    }
                    game.setState(State.TITLE);
                    break yield;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        g.drawImage(image, 48, 140, 262, 22, null);
        g.drawImage(image, 48, 0, 310, 140 + currentLine, 0, currentLine, 262, currentLine + 1, null);
    }

    
    // broadcast messages
    
    @Override
    public void stateChanged() {
        visible = game.state == State.OL_PRESENTS;
    }
    
        
}

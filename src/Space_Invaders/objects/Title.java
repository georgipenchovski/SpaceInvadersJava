package Space_Invaders.objects;


import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.SpaceInvadersObj;
import Space_Invaders.core.Keyboard;
import Space_Invaders.core.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Title extends SpaceInvadersObj {

    private BufferedImage image;
    private int currentLine = 21;
    private boolean pushSpaceToStartVisible;
    private Sound themeSong;

    public Title(SpaceInvadersGame game) {
        super(game);
    }

    @Override
    public void init() {
        image = new BufferedImage(282, 22, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
//        Background
//        g.setBackground(Color.yellow);
//        g.setColor(Color.yellow);
//        g.fillRect(0, 0, 282, 22);
//        g.setBackground(Color.yellow);
        g.scale(2, 2);
        game.drawText(g, "SPACE INVADERS", 0, 0);

        themeSong = new Sound("/sounds/Star_Wars_Theme.wav");
        themeSong.loop();
    }

    @Override
    public void updateTitle() {
        yield:
        while (true) {
            switch (instructionPointer) {
                case 0:
                    pushSpaceToStartVisible = ((int) (System.nanoTime() * 0.00000001) % 2) == 0;
                    if (Keyboard.keyPressed[KeyEvent.VK_SPACE]) {
                        game.startGame();
                        themeSong.stop();
                    }
                    break yield;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!visible) {
            return;
        }
        g.drawImage(image, 40, 120, 282, 22, null);
        if (pushSpaceToStartVisible) {
            game.drawText(g, "PUSH SPACE TO START", 83, 170);
        }
        game.drawText(g, "PROGRAMMED BY PETKO && GEORGI 2018", 10, 280);
        game.drawText(g, "-STAR WARS- EDITION 1.0", 10, 295);
    }


    // broadcast messages

    @Override
    public void stateChanged() {
        visible = false;
        if (game.state == SpaceInvadersGame.State.TITLE) {
            game.lives = 3;
            visible = true;
        }
    }


}

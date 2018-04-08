package main;

import Space_Invaders.SpaceInvadersGame;
import Space_Invaders.core.Display;
import Space_Invaders.core.Game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Game game = new SpaceInvadersGame();
                Display view = new Display(game);
                JFrame frame = new JFrame();
                frame.setTitle("Space Invaders");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(view);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                view.requestFocus();
                view.start();
            }
        });
    }
    
}

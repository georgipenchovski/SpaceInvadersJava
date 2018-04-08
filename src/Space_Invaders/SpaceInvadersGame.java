package Space_Invaders;

import Space_Invaders.objects.Alien;
import Space_Invaders.objects.PenchovskiPresents;
import Space_Invaders.objects.Ship;
import Space_Invaders.objects.ShipShot;
import Space_Invaders.core.Game;
import Space_Invaders.objects.AlienShot;
import Space_Invaders.objects.GameOver;
import Space_Invaders.objects.HUD;
import Space_Invaders.objects.Initializer;
import Space_Invaders.objects.Saucer;
import Space_Invaders.objects.Shield;
import Space_Invaders.objects.Title;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class SpaceInvadersGame extends Game {
    
    public static Dimension screenSize = new Dimension(360, 330);
    public static Point2D screenScale = new Point2D.Double(2, 2);

    public static enum State { INITIALIZING, OL_PRESENTS, TITLE, READY, PLAYING, HIT, CLEAR, GAME_OVER }
    public State state = State.INITIALIZING;
    
    public double enemiesShootingProbability;
    public int enemiesCount;
    public long startGameTime;
    public int lives;
    public int score;
    public int hiscore;
    
    public SpaceInvadersGame() {
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.state != state) {
            this.state = state;
            broadcastMessage("stateChanged");
        }
    }
    
    public void addScore(int point) {
        score += point;
    }

    public void clearScore() {
        score = 0;
    }
    
    public void updateHiscore() {
        if (score > hiscore) {
            hiscore = score;
        }
    }
    
    @Override
    public void createAllObjs() {
        objs.add(new Initializer(this));
        objs.add(new PenchovskiPresents(this));
        objs.add(new HUD(this));
        
        // shields
        for (int id = 0; id < 4; id++) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 3; col++) {
                    if (col == 1 && row == 3) {
                        continue;
                    }
                    Shield shield = new Shield(this, id, col, row);
                    objs.add(shield);
                }
            }
        }
        
        List<AlienShot> alienShots = new ArrayList<AlienShot>();
        int[] alienTypes = { 0, 1, 2, 2, 3, 3 };
        int[] alienPoints = { 0, 30, 30, 20, 20, 10 };
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 11; col++) {
                AlienShot alienShot = new AlienShot(this);
                Alien alien = new Alien(this, col, row, alienTypes[row], alienShot, alienPoints[row]);
                objs.add(alien);
                alienShots.add(alienShot);
            }
        }
        
        ShipShot shipShot = new ShipShot(this);
        objs.add(new Ship(this, shipShot));
        objs.add(shipShot);
        
        for (AlienShot alienShot : alienShots) {
            objs.add(alienShot);
        }

        objs.add(new Saucer(this));
        objs.add(new Title(this));
        objs.add(new GameOver(this));
    }

    @Override
    public Dimension getScreenSize() {
        return screenSize;
    }

    @Override
    public Point2D getScreenScale() {
        return screenScale;
    }
    
    // ---
    
    public String getScore() {
        String scoreStr = "0000000" + score;
        scoreStr = scoreStr.substring(scoreStr.length() - 7, scoreStr.length());
        return scoreStr;
    }

    public String getHiscore() {
        String hiscoreStr = "0000000" + hiscore;
        hiscoreStr = hiscoreStr.substring(hiscoreStr.length() - 7, hiscoreStr.length());
        return hiscoreStr;
    }
    
    // ---
    
    public void startGame() {
        enemiesShootingProbability = 0.001;
        enemiesCount = 11 * 5;
        startGameTime = System.currentTimeMillis();
        setState(State.READY);
    }

    public void gameCleared() {
        setState(State.CLEAR);
    }

    public void nextGame() {
        enemiesShootingProbability += 0.0005;
        enemiesCount = 11 * 5;
        startGameTime = System.currentTimeMillis();
        setState(State.READY);
    }
    
}

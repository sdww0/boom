package game.thread;

import game.basement.Location;
import game.element.Bomb;
import game.element.Boom;
import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gui.Board;
import game.image.ImageReader;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 控制炸弹爆炸runnable
 *
 * @author njdnhh
 */
public class BombControlThread implements Runnable{
    private Bomb bomb;
    private Location location;

    @Override
    public void run() {
        boolean explode = false;
        try {
            for(int n =0;n<200;n++) {
                for (Location location1 : GameData.bombExplodedLocation) {
                    if (location1.getX() == location.getX() &&
                            location1.getY() == location.getY()) {
                        GameData.bombExplodedLocation.remove(location1);
                        explode();
                        explode = true;
                    }
                }
                Thread.sleep(GameConstant.BOMB_SECONDS * 5);
            }
        } catch (InterruptedException ignore) {
        }
        if(!explode) {
            explode();
        }
    }

    public BombControlThread(Bomb bomb,Location location){
        this.bomb = bomb;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    private void explode(){
        GameData.getBoard().getSquare()[location.getX()][location.getY()].removeAllElement();
        for (Player player : GameData.players) {
            player.getBombs().removeIf(bomb -> this.bomb == bomb);
            player.getBombs().add(null);
        }
        /*
         * n
         * 0上
         * 1下
         * 2左
         * 3右
         */
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location);
        GameData.getBoard().getSquare()[location.getX()]
                [location.getY()].setElement(new Boom());
        for (int n = 0; n < 4; n++) {
            int xChange = 0, yChange = 0;
            switch (n) {
                case 0:
                    yChange = -1;
                    break;
                case 1:
                    yChange = 1;
                    break;
                case 2:
                    xChange = -1;
                    break;
                case 3:
                    xChange = 1;
                    break;
                default:
                    break;
            }
            /*
             * 如果是炸弹
             * 则连爆
             */
            boolean isBomb = GameData.getBoard().getSquare()[location.getX() + xChange]
                    [location.getY() + yChange].getElement() == 2;
            if (isBomb) {
                GameData.bombExplodedLocation.add(new Location(location.getX() + xChange,location.getY() + yChange));
                continue;
            }
            boolean isValid = GameData.getBoard().getSquare()[location.getX() + xChange]
                    [location.getY() + yChange].getElement() == 0;
            if (isValid) {
                locations.add(new Location(location.getX() + xChange
                        , location.getY() + yChange));
                GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].setElement(new Boom());
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Location location1 : locations) {
            GameData.getBoard().getSquare()[location1.getX()][location1.getY()].removeAllElement();
        }
    }

}

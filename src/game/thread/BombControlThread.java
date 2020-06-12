package game.thread;

import game.basement.Location;
import game.element.Bomb;
import game.element.Boom;
import game.element.Item;
import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.music.MusicPlayer;

import java.util.ArrayList;

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
            for(int n =0;n<50;n++) {
                for (Location location1 : GameData.bombExplodedLocation) {
                    if (location1.getX() == location.getX() &&
                            location1.getY() == location.getY()) {
                        GameData.bombExplodedLocation.remove(location1);
                        explode();
                        explode = true;
                    }
                }
                Thread.sleep(GameConstant.BOMB_SECONDS * 20);
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
        MusicPlayer.Play(MusicPlayer.BOOM);
        for (int n = 0; n < 4; n++) {
            for(int i = 0;i<bomb.getRadius();i++) {
                int xChange = 0, yChange = 0;
                switch (n) {
                    case 0:
                        yChange = -1*(i+1);
                        break;
                    case 1:
                        yChange = (i + 1);
                        break;
                    case 2:
                        xChange = -1*(i+1);
                        break;
                    case 3:
                        xChange = (i + 1);
                        break;
                    default:
                        break;
                }
                /*
                 * 限制位置
                 */
                if (location.getX() + xChange <= -1 || location.getX() + xChange >= GameConstant.SQUARE_AMOUNT
                        || location.getY() + yChange <= -1 || location.getY() + yChange >= GameConstant.SQUARE_AMOUNT) {
                    continue;
                }
                /*
                 * 如果是炸弹
                 * 则连爆
                 */

                boolean isBomb = GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType() == 2;
                if (isBomb) {
                    GameData.bombExplodedLocation.add(new Location(location.getX() + xChange, location.getY() + yChange));
                    break;
                }

                boolean isPlayer = GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType() == 3;
                if (isPlayer) {
                    MusicPlayer.Play(MusicPlayer.HURT);
                    Player player = (Player) GameData.getBoard().getSquare()[location.getX() + xChange]
                            [location.getY() + yChange].getElement();
                    player.setLife(player.getLife() - 1);
                    break;
                }

                boolean canBreak = GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType() == 1;
                if (canBreak) {
                    locations.add(new Location(location.getX() + xChange
                            , location.getY() + yChange));
                    GameData.getBoard().getSquare()[location.getX() + xChange]
                            [location.getY() + yChange].setItem(getRandomItem());
                    try{
                        Thread.sleep(30);
                    }catch (Exception whoCares){

                    }
                    GameData.getBoard().getSquare()[location.getX() + xChange]
                            [location.getY() + yChange].setElement(new Boom());
                    break;
                }

                if(GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType() == -1){
                    break;
                }


                boolean isValid = GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType() == 0||
                        GameData.getBoard().getSquare()[location.getX() + xChange]
                                [location.getY() + yChange].getElementType() == 4;
                if (isValid) {
                    locations.add(new Location(location.getX() + xChange
                            , location.getY() + yChange));
                    GameData.getBoard().getSquare()[location.getX() + xChange]
                            [location.getY() + yChange].setElement(new Boom());
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Location location1 : locations) {
            GameData.getBoard().getSquare()[location1.getX()][location1.getY()].removeAllElement();
            if(GameData.getBoard().getSquare()[location1.getX()][location1.getY()].getItem()!=null){

                GameData.getBoard().getSquare()[location1.getX()][location1.getY()].getItem().repaint();
            }
        }
    }

    private Item getRandomItem(){
        switch((int)(Math.random()*10)){
            case 0:
            case 1:return new Item(Item.CURE);
            case 2:
            case 3:return new Item(Item.BOMB_AMOUNT);
            case 4:
            case 5:return new Item(Item.BOMB_AREA);
            case 6:
            case 7:
            case 8:
            case 9:
            default:return null;
        }
    }

}

package game.thread;

import game.basement.Location;
import game.element.*;
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
    private Player player;

    @Override
    public void run() {
        boolean explode = false;
        try {
            /*
            检测自己所处位置是否被标记为设定炸弹爆炸位置
             */
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

    public BombControlThread(Bomb bomb,Location location,Player player){
        this.bomb = bomb;
        this.location = location;
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    private void explode(){
        GameData.getBoard().getSquare()[location.getX()][location.getY()].removeAllElement();

            player.getBombs().removeIf(bomb -> this.bomb == bomb);
            player.getBombs().add(null);
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
                ElementType elementType = GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getElementType();
                /*
                 如果该位置有物品，则物品消失
                 */
                if( GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].getItem()!=null){
                    GameData.getBoard().getSquare()[location.getX() + xChange]
                            [location.getY() + yChange].setItem(null);
                }

                boolean canBreak = true;
                switch(elementType){
                    case BOMB:
                        GameData.bombExplodedLocation.add(new Location(location.getX() + xChange, location.getY() + yChange));
                        break;
                    case PLAYER:
                        MusicPlayer.Play(MusicPlayer.HURT);
                        Player player = (Player) GameData.getBoard().getSquare()[location.getX() + xChange]
                                [location.getY() + yChange].getPlayer();
                        player.setLife(player.getLife() - 1);
                        break;
                    case BREAKABLE_WALL:
                        locations.add(new Location(location.getX() + xChange
                                , location.getY() + yChange));
                        GameData.getBoard().getSquare()[location.getX() + xChange]
                                [location.getY() + yChange].setItem(getRandomItem());
                        try{
                            Thread.sleep(30);
                        }catch (Exception ignored){
                            //whoCares
                        }
                        GameData.getBoard().getSquare()[location.getX() + xChange]
                                [location.getY() + yChange].setElement(new Boom());
                        break;
                    case UNBREAKABLE_WALL:break;
                    default:
                        canBreak = false;
                }
                if(canBreak){
                    break;
                }

                locations.add(new Location(location.getX() + xChange
                        , location.getY() + yChange));
                GameData.getBoard().getSquare()[location.getX() + xChange]
                        [location.getY() + yChange].setElement(new Boom());

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

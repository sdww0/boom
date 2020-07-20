package game.gui;

import game.basement.Location;
import game.basement.TrueLocation;
import game.element.ElementType;
import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gamedata.Judge;
import game.image.ImageReader;
import game.music.MusicPlayer;
import game.thread.BombControlThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;

/**
 * swing游戏框架
 * @author njdnhh
 */
public class GameFrame extends JFrame {
    private Board board;
    private JLayeredPane layeredPane;
    /**
     * 防止多个线程运行移动动画
     */
    private volatile boolean keyPressedPrevent = true;
    private volatile boolean keyTypePrevent = true;
    public volatile HashSet<Integer> check ;

    public GameFrame(Board board) {
        check = new HashSet<>();
        layeredPane = new JLayeredPane();

        setTitle("!Boom!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 700);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu music = new Menu("Music");
        MenuItem start = new MenuItem("start");
        MenuItem stop = new MenuItem("stop");
        MenuItem lower = new MenuItem("lower Volume ");
        MenuItem higher = new MenuItem("higher Volume ");
        lower.addActionListener(e -> MusicPlayer.lowerVolume());
        higher.addActionListener(e -> MusicPlayer.higherVolume());
        start.addActionListener(e -> MusicPlayer.playBackGroundMusic());
        stop.addActionListener(e -> MusicPlayer.stopBackGroundMusic());
        menuBar.add(menu);
        menu.add(music);
        music.add(start);
        music.add(stop);
        music.add(lower);
        music.add(higher);
        setMenuBar(menuBar);

        this.board = board;
        layeredPane.add(this.board, JLayeredPane.MODAL_LAYER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!check.contains(e.getKeyCode())){
                    check.add(e.getKeyCode());
                }



           /*     if (keyPressedPrevent) {
             //       keyPressedPrevent = false;
                    GameData.getGameExecutePool().submit(() -> {
                        super.keyPressed(e);
                    /*
                    玩家1WASD移动
                    /
                        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                            checkPlayer1();
                            checkPlayer2();
                            checkBomb();
                            if(!check.contains(e.getKeyCode())){
                                sendTask(e.getKeyCode(), true);
                                check.add(e.getKeyCode());
                            }

                        }
                    /*
                     玩家2方向键移动
                     /
                        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            checkPlayer1();
                            checkPlayer2();
                            checkBomb();
                            if(!check.contains(e.getKeyCode())){
                                sendTask(e.getKeyCode(), false);
                                check.add(e.getKeyCode());
                            }
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            checkPlayer1();
                            checkPlayer2();
                            checkBomb();
                            if(!check.contains(e.getKeyCode())){
                                placeBomb(true);
                                check.add(e.getKeyCode());
                            }
                        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            checkPlayer1();
                            checkPlayer2();
                            checkBomb();
                            if(!check.contains(e.getKeyCode())){
                                placeBomb(false);
                                check.add(e.getKeyCode());
                            }
                        }

                 //       keyPressedPrevent = true;
                    });
             //   }*/
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                check.remove(e.getKeyCode());

            }

        });


        requestFocus();
        setLayeredPane(layeredPane);
    }

    public void init() {
        layeredPane.add(GameData.getRightMenu(), JLayeredPane.MODAL_LAYER);
    }

    public void sendTask(int keyCode, boolean isPlayer1){
        if(isPlayer1){
            if(keyCode==KeyEvent.VK_SPACE){
                placeBomb(true);
                return;
            }
            sendTaskToPlayer1(keyCode);
        }else{
            if(keyCode==KeyEvent.VK_ENTER){
                placeBomb(false);
                return;
            }
            sendTaskToPlayer2(keyCode);
        }
    }

    private synchronized void sendTaskToPlayer1(int keyCode){
        movePosition(keyCode,true);
    }

    private synchronized void sendTaskToPlayer2(int keyCode){
        movePosition(keyCode,false);
    }

    /**
     * 移动
     *
     * @param keyCode
     * @param isPlayer1
     */
    private void movePosition(int keyCode, boolean isPlayer1) {
        Player player = null;
        if (isPlayer1) {
            player = GameData.player1;
        } else {
            if (GameData.player2 == null) {
                return;
            }
            player = GameData.player2;
        }
        int xChange = 0, yChange = 0;
        /*
        根据keyCode移动位置
         */
        int maxAccess = 16;
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            yChange = -1*player.getSpeed() ;
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][1]);
            if (player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE >= maxAccess &&
                    player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE <= GameConstant.SQUARE_SIZE - maxAccess) {
                return;
            } else {
                player.getPlayerLocation().setX(Math.round(player.getPlayerLocation().getX() / (float) GameConstant.SQUARE_SIZE) * GameConstant.SQUARE_SIZE);
            }
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            yChange = player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][0]);
            if (player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE >= maxAccess &&
                    player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE <= GameConstant.SQUARE_SIZE - maxAccess) {
                return;
            } else {
                player.getPlayerLocation().setX(Math.round(player.getPlayerLocation().getX() / (float) GameConstant.SQUARE_SIZE) * GameConstant.SQUARE_SIZE);
            }
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            xChange = -1 * player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][3]);
            if (player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE >= maxAccess &&
                    player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE <= GameConstant.SQUARE_SIZE - maxAccess) {
                return;
            } else {
                player.getPlayerLocation().setY(Math.round(player.getPlayerLocation().getY() / (float) GameConstant.SQUARE_SIZE) * GameConstant.SQUARE_SIZE);
            }
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            xChange = player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][2]);
            if (player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE >= maxAccess &&
                    player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE <= GameConstant.SQUARE_SIZE - maxAccess) {
                return;
            } else {
                player.getPlayerLocation().setY(Math.round(player.getPlayerLocation().getY() / (float) GameConstant.SQUARE_SIZE) * GameConstant.SQUARE_SIZE);
            }
        } else {
            return;
        }
        /*
         * 限制位置
         */

        player.setPlayerLocation(new TrueLocation(player.getPlayerLocation().getX() + xChange, player.getPlayerLocation().getY() + yChange));


        /*
            老版
         */
       /* int canMoveX = 0;
        int canMoveY = 0;
        if (xChange > 0 || yChange > 0) {
            canMoveX = (int) (Math.ceil((player.getPlayerLocation().getX() + xChange) / (double) GameConstant.SQUARE_SIZE));
            canMoveY = (int) (Math.ceil((player.getPlayerLocation().getY() + yChange) / (double) GameConstant.SQUARE_SIZE));
        } else {
            canMoveX = (int) (Math.floor((player.getPlayerLocation().getX() + xChange) / (double) GameConstant.SQUARE_SIZE));
            canMoveY = (int) (Math.floor((player.getPlayerLocation().getY() + yChange) / (double) GameConstant.SQUARE_SIZE));
        }
        if (canMoveX <= -1 || canMoveX >= GameConstant.SQUARE_AMOUNT || canMoveY <= -1 || canMoveY >= GameConstant.SQUARE_AMOUNT) {
            return;
        }
        ElementType elementType1 = board.getSquare()[canMoveX][canMoveY].getElementType();
        boolean isValid = elementType1 == ElementType.NULL || elementType1 == ElementType.PLAYER;

        int finalX = Math.round((player.getPlayerLocation().getX() + xChange) / (float) GameConstant.SQUARE_SIZE);
        int finalY = Math.round((player.getPlayerLocation().getY() + yChange) / (float) GameConstant.SQUARE_SIZE);

        ElementType elementType = board.getSquare()[finalX][finalY].getElementType();

        if (isValid) {
            if (board.getSquare()[finalX][finalY].getItem() != null) {
                reactPickUpItem(new Location(finalX, finalY),player);
            }
            switch (elementType) {
                case PLAYER:
                    player.moveXAndYPosition(xChange, yChange);
                break;
                case NULL:
                    player.moveXAndYPosition(xChange, yChange);
                    board.getSquare()[Math.round(player.getPlayerLocation().getX() / (float) GameConstant.SQUARE_SIZE)]
                            [Math.round(player.getPlayerLocation().getY() / (float) GameConstant.SQUARE_SIZE)].setPlayer(null);
                    board.getSquare()[finalX][finalY].setPlayer(player);
                    break;
                default:
                    throw new IllegalArgumentException("error, location:GameFrame");
            }

        }else{
            if(xChange!=0){
                xChange/=Math.abs(xChange);
                if((player.getSpeed()+player.getPlayerLocation().getX()%GameConstant.SQUARE_SIZE)>=GameConstant.SQUARE_SIZE||
                        (player.getPlayerLocation().getX()%GameConstant.SQUARE_SIZE-player.getSpeed())<=0){
                    player.setPlayerLocation(Location.changeToTrueLocation(new Location(canMoveX-xChange,canMoveY)));
                    board.getSquare()[player.getLastLocation().getX()][player.getLastLocation().getY()].setPlayer(null);
                    board.getSquare()[player.getVirtualLocation().getX()][player.getVirtualLocation().getY()].setPlayer(player);
                }
            }
            if(yChange!=0){
                yChange/=Math.abs(yChange);
                if((player.getSpeed()+player.getPlayerLocation().getY()%GameConstant.SQUARE_SIZE)>=GameConstant.SQUARE_SIZE||
                        (player.getPlayerLocation().getY()%GameConstant.SQUARE_SIZE-player.getSpeed())<=0){
                    player.setPlayerLocation(Location.changeToTrueLocation(new Location(canMoveX,canMoveY-yChange)));
                    board.getSquare()[player.getLastLocation().getX()][player.getLastLocation().getY()].setPlayer(null);
                    board.getSquare()[player.getVirtualLocation().getX()][player.getVirtualLocation().getY()].setPlayer(player);
                }
            }
        }*/
    }

    private synchronized void placeBomb(boolean isPlayer1) {
        Player player = null;
        if (isPlayer1) {
            player = GameData.player1;
        } else {
            if (GameData.player2 == null) {
                return;
            }
            player = GameData.player2;
        }
        if (board.getSquare()[player.getVirtualLocation().getX()]
                [player.getVirtualLocation().getY()].getElement() != null) {
            return;
        }
        boolean enoughBombs = false;
        int place = 0;
        for (int n = 0; n < player.getBombs().size(); n++) {
            if (player.getBombs().get(n) == null) {
                place = n;
                enoughBombs = true;
                break;
            }
        }

        if (enoughBombs) {
            MusicPlayer.Play(MusicPlayer.PLACE);
            player.getBombs().remove(place);
            player.getBombs().add(place, player.getBomb());
            board.getSquare()[player.getVirtualLocation().getX()][player.getVirtualLocation().getY()].setElement(player.getBomb());

            GameData.getBombControlThreadPool().submit(new BombControlThread(player.getBomb(),
                    new Location(player.getVirtualLocation().getX(), player.getVirtualLocation().getY()),
                    player));
        }

    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}

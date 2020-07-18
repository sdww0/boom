package game.gui;

import game.basement.Location;
import game.element.ElementType;
import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;
import game.music.MusicPlayer;
import game.thread.BombControlThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * swing游戏框架
 * @author njdnhh
 */
public class GameFrame extends JFrame {
    private Board board;
    private JLayeredPane layeredPane;

    public GameFrame(Board board) {

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

                GameData.getGameExecutePool().submit(() -> {
                    super.keyPressed(e);
                    /*
                    玩家1WASD移动
                    */
                    if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                        movePosition(e.getKeyCode(), true);
                    }
                    /*
                     玩家2方向键移动
                     */
                    else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        movePosition(e.getKeyCode(), false);

                    }
                    /*
                    玩家1/玩家2放置炸弹
                     */
                    else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        placeBomb(true);
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        placeBomb(false);
                    }
                });

            }
        });

        requestFocus();
        setLayeredPane(layeredPane);
    }

    private void reactPickUpItem(Location location) {
        MusicPlayer.Play(MusicPlayer.PICKUP);
        switch (GameData.getBoard().getSquare()[location.getX()][location.getY()].getItem().getType()) {
            case 1:
                GameData.player1.getBombs().add(null);
                break;
            case 2:
                GameData.player1.getBomb().setRadius(GameData.player1.getBomb().getRadius() + 1);
                break;
            case 3:
                GameData.player1.setLife(GameData.player1.getLife() + 1);
                break;
            default:
        }
        GameData.getBoard().getSquare()[location.getX()][location.getY()].setItem(null);
    }

    public void init() {
        layeredPane.add(GameData.getRightMenu(), JLayeredPane.MODAL_LAYER);
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

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            yChange = -1 * player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][1]);
            if (player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE != 0) {
                return;
            }
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            yChange = player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][0]);
            if (player.getPlayerLocation().getX() % GameConstant.SQUARE_SIZE != 0) {
                return;
            }
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            xChange = -1 * player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][3]);
            if (player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE != 0) {
                return;
            }
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            xChange = player.getSpeed();
            player.setCurrentImageIcon(ImageReader.ALL_PLAYER[player.getWhichPlayer() - 1][2]);
            if (player.getPlayerLocation().getY() % GameConstant.SQUARE_SIZE != 0) {
                return;
            }
        } else {
            return;
        }
        /*
         * 限制位置
         */
        if (player.getPlayerLocation().getX() + xChange <= -1 || player.getPlayerLocation().getX() + xChange >= GameConstant.BOARD_SIZE
                || player.getPlayerLocation().getY() + yChange <= -1 || player.getPlayerLocation().getY() + yChange >= GameConstant.BOARD_SIZE) {
            return;
        }
        /*
        是否是可以移动到目标位置
         */
        int canMoveX = 0;
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
                reactPickUpItem(new Location(finalX, finalY));
            }
            switch (elementType) {
                case NULL:
                    board.getSquare()[Math.round(player.getPlayerLocation().getX() / (float) GameConstant.SQUARE_SIZE)]
                            [Math.round(player.getPlayerLocation().getY() / (float) GameConstant.SQUARE_SIZE)].setPlayer(null);
                    board.getSquare()[finalX][finalY].setPlayer(player);
                    break;
                case PLAYER:
                    break;
                default:
                    throw new IllegalArgumentException("error, location:GameFrame");
            }
            player.moveXAndYPosition(xChange, yChange);
        }
    }

    private void placeBomb(boolean isPlayer1) {
        Player player = null;
        if (isPlayer1) {
            player = GameData.player1;
        } else {
            if (GameData.player2 == null) {
                return;
            }
            player = GameData.player2;
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
            board.getSquare()[player.getPlayerLocation().changeToVirtualLocation().getX()]
                    [player.getPlayerLocation().changeToVirtualLocation().getY()].setElement(player.getBomb());
            GameData.getBombControlThreadPool().submit(new BombControlThread(player.getBomb(),
                    new Location(player.getPlayerLocation().changeToVirtualLocation().getX(), player.getPlayerLocation().changeToVirtualLocation().getY()),
                    player));
        }

    }


    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}

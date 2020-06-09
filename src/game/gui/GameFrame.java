package game.gui;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;
import game.music.MusicPlayer;
import game.thread.BombControlThread;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * swing游戏框架
 * @author njdnhh
 */
public class GameFrame extends JFrame {


    public GameFrame(Board board){

        setTitle("!Boom!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900,700);

        add(board);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                /*
                 *WASD移动
                 */
                if(e.getKeyCode()==KeyEvent.VK_W||e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_D){
                    int xChange = 0, yChange = 0;

                    if(e.getKeyCode()==KeyEvent.VK_W){
                        if(GameData.players.get(0).getPlayerLocation().getY()!=0) {
                            yChange = -1;
                        }
                        GameData.players.get(0).setCurrentImageIcon(ImageReader.PLAYER1[1]);
                    }else if(e.getKeyCode()==KeyEvent.VK_S){
                        if(GameData.players.get(0).getPlayerLocation().getY()!= GameConstant.SQUARE_AMOUNT-1) {
                            yChange = 1;
                        }
                        GameData.players.get(0).setCurrentImageIcon(ImageReader.PLAYER1[0]);
                    }else if(e.getKeyCode()==KeyEvent.VK_A){
                        if(GameData.players.get(0).getPlayerLocation().getX()!=0) {
                            xChange = -1;
                        }
                        GameData.players.get(0).setCurrentImageIcon(ImageReader.PLAYER1[3]);
                    }else if(e.getKeyCode()==KeyEvent.VK_D){
                        if(GameData.players.get(0).getPlayerLocation().getX()!=GameConstant.SQUARE_AMOUNT-1) {
                            xChange = 1;
                        }
                        GameData.players.get(0).setCurrentImageIcon(ImageReader.PLAYER1[2]);
                    }
                    /*
                     * 限制位置
                     */
                    if(GameData.players.get(0).getPlayerLocation().getX() + xChange==-1||GameData.players.get(0).getPlayerLocation().getX() + xChange==GameConstant.SQUARE_AMOUNT
                            ||GameData.players.get(0).getPlayerLocation().getY() + yChange==-1||GameData.players.get(0).getPlayerLocation().getY() + yChange==GameConstant.SQUARE_AMOUNT){
                        return;
                    }
                    
                    boolean isValid = (xChange!=0||yChange!=0)&&
                            board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                            [GameData.players.get(0).getPlayerLocation().getY() + yChange].getElementType()==0;

                    if(isValid) {

                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX()]
                                [GameData.players.get(0).getPlayerLocation().getY()].removeAllElement();
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].setElement(GameData.players.get(0));
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX()]
                                [GameData.players.get(0).getPlayerLocation().getY()].repaint();
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].repaint();
                        GameData.players.get(0).setPlayerLocation(new Location(
                                GameData.players.get(0).getPlayerLocation().getX() + xChange,
                                GameData.players.get(0).getPlayerLocation().getY() + yChange));
                    }else{
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX()]
                                [GameData.players.get(0).getPlayerLocation().getY()].repaint();
                    }
                }
                /*
                 *上下左右放置炸弹
                 */
                else if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_RIGHT){
                    int xChange = 0, yChange = 0;

                    if(GameData.players.get(0).getPlayerLocation().getY()!=0&&e.getKeyCode()==KeyEvent.VK_UP){
                        yChange = -1;
                    }else if(GameData.players.get(0).getPlayerLocation().getY()!= GameConstant.SQUARE_AMOUNT-1
                            &&e.getKeyCode()==KeyEvent.VK_DOWN){
                        yChange = 1;
                    }else if(GameData.players.get(0).getPlayerLocation().getX()!=0&&e.getKeyCode()==KeyEvent.VK_LEFT){
                        xChange = -1;
                    }else if(GameData.players.get(0).getPlayerLocation().getX()!=GameConstant.SQUARE_AMOUNT-1
                            &&e.getKeyCode()==KeyEvent.VK_RIGHT){
                        xChange = 1;
                    }
                    /*
                     * 限制位置
                     */
                    if(GameData.players.get(0).getPlayerLocation().getX() + xChange==-1||GameData.players.get(0).getPlayerLocation().getX() + xChange==GameConstant.SQUARE_AMOUNT
                            ||GameData.players.get(0).getPlayerLocation().getY() + yChange==-1||GameData.players.get(0).getPlayerLocation().getY() + yChange==GameConstant.SQUARE_AMOUNT){
                        return;
                    }
                    boolean isValid = (xChange!=0||yChange!=0)&&
                            board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                    [GameData.players.get(0).getPlayerLocation().getY() + yChange].getElementType()==0;

                    boolean enoughBombs = false;
                    int place = 0;
                    for(int n =0;n<GameData.players.get(0).getBombs().size();n++){
                        if (GameData.players.get(0).getBombs().get(n) == null) {
                            place = n;
                            enoughBombs = true;
                            break;
                        }
                    }

                    if(isValid&&enoughBombs) {
                        MusicPlayer.Play(MusicPlayer.PLACE);
                        GameData.players.get(0).getBombs().remove(place);
                        GameData.players.get(0).getBombs().add(place,GameData.players.get(0).getBomb());
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].setElement(GameData.players.get(0).getBomb());

                        GameData.getBombControlPool().submit(new BombControlThread(GameData.players.get(0).getBomb(),
                                new Location(GameData.players.get(0).getPlayerLocation().getX() + xChange
                                        ,GameData.players.get(0).getPlayerLocation().getY() + yChange)));
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].repaint();

                    }
                }
            }
        });

        requestFocus();

    }


}

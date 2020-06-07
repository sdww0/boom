package game.gui;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                if(e.getKeyCode()==KeyEvent.VK_W||e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_D){
                    int xChange = 0, yChange = 0;

                    if(GameData.players.get(0).getPlayerLocation().getY()!=0&&e.getKeyCode()==KeyEvent.VK_W){
                        yChange = -1;
                    }else if(GameData.players.get(0).getPlayerLocation().getY()!= GameConstant.SQUARE_AMOUNT-1
                            &&e.getKeyCode()==KeyEvent.VK_S){
                        yChange = 1;
                    }else if(GameData.players.get(0).getPlayerLocation().getX()!=0&&e.getKeyCode()==KeyEvent.VK_A){
                        xChange = -1;
                    }else if(GameData.players.get(0).getPlayerLocation().getX()!=GameConstant.SQUARE_AMOUNT-1
                            &&e.getKeyCode()==KeyEvent.VK_D){
                        xChange = 1;
                    }

                    boolean isValid = (xChange!=0||yChange!=0)&&
                            board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                            [GameData.players.get(0).getPlayerLocation().getY() + yChange].getElement()==0;
                    if(isValid) {

                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX()]
                                [GameData.players.get(0).getPlayerLocation().getY()].removeAllElement();
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].setPlayer(GameData.players.get(0));
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX()]
                                [GameData.players.get(0).getPlayerLocation().getY()].repaint();
                        board.getSquare()[GameData.players.get(0).getPlayerLocation().getX() + xChange]
                                [GameData.players.get(0).getPlayerLocation().getY() + yChange].repaint();
                        GameData.players.get(0).setPlayerLocation(new Location(
                                GameData.players.get(0).getPlayerLocation().getX() + xChange,
                                GameData.players.get(0).getPlayerLocation().getY() + yChange));
                    }
                }



            }
        });
        requestFocus();

    }


}

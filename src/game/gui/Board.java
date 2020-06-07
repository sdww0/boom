package game.gui;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;

/**
 * swing游戏面板
 * @author njdnhh
 */
public class Board extends JComponent {
    private Square[][] square;

    public Board(){
        setLayout(null);
        setSize(GameConstant.BOARD_SIZE,GameConstant.BOARD_SIZE);

        square = new Square[GameConstant.SQUARE_AMOUNT][GameConstant.SQUARE_AMOUNT];
        for(int n =0;n<GameConstant.SQUARE_AMOUNT;n++){
            for(int i =0;i<GameConstant.SQUARE_AMOUNT;i++){
                square[n][i] = new Square(new Location(n,i), GameConstant.BOARD_COLOR1);
                square[n][i].setLocation(n * GameConstant.SQUARE_SIZE, i * GameConstant.SQUARE_SIZE);
                add(square[n][i]);
            }
        }
    }

    public Square[][] getSquare() {
        return square;
    }

    public void setSquare(Square[][] square) {
        this.square = square;
    }
}

package game.gui;

import game.basement.Location;
import game.element.Walls;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;
import java.util.Arrays;

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

        initElement();

    }

    private void initElement(){

        for(int x = 0;x<GameConstant.SQUARE_AMOUNT;x++){
            for(int y = 0;y<GameConstant.SQUARE_AMOUNT;y++){
                switch(GameData.getMap()[x][y]){
                    case 0:break;
                    case 1:square[x][y].setElement(new Walls(true));break;
                    case 2:square[x][y].setElement(new Walls(false));break;
                    default:throw new IllegalArgumentException("wrong map");
                }
            }
        }

    }


    public Square[][] getSquare() {
        return square;
    }

    public void setSquare(Square[][] square) {
        this.square = square;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square[] squares : square) {
            for (Square value : squares) {
                stringBuilder.append(value.toString()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

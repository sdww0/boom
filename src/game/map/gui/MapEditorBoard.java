package game.map.gui;

import game.basement.Location;
import game.element.Walls;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gui.Square;

import javax.swing.*;

/**
 * 地图编辑面板
 * @author njdnhh
 */
public class MapEditorBoard extends JComponent {
    private MapEditorSquare[][] square;

    public MapEditorBoard(){
        setLayout(null);
        setSize(GameConstant.BOARD_SIZE,GameConstant.BOARD_SIZE);

        square = new MapEditorSquare[GameConstant.SQUARE_AMOUNT][GameConstant.SQUARE_AMOUNT];
        for(int n =0;n<GameConstant.SQUARE_AMOUNT;n++){
            for(int i =0;i<GameConstant.SQUARE_AMOUNT;i++){
                square[n][i] = new MapEditorSquare(new Location(n,i), GameConstant.BOARD_COLOR1);
                square[n][i].setLocation(n * GameConstant.SQUARE_SIZE, i * GameConstant.SQUARE_SIZE);
                add(square[n][i]);
            }
        }

    }
    public MapEditorSquare[][] getSquare() {
        return square;
    }
}

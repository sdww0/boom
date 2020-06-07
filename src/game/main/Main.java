package game.main;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;
import game.element.Walls;
import game.gamedata.GameData;
import game.gui.Board;
import game.gui.GameFrame;

/**
 * 主程序
 * @author njdnhh
 */
public class Main {


    public static void main(String[] args){

        Board board = new Board();
        GameFrame mainFrame = new GameFrame(board);
        Player player1 = new Player(new Location(0,0),new Bomb(1,1,1),100);
        GameData.players.add(player1);
        board.getSquare()[0][0].setPlayer(player1);
        board.getSquare()[1][1].setBomb(new Bomb(1,1,1));
        board.getSquare()[1][2].setWalls(new Walls(false));
        mainFrame.add(board);
        mainFrame.setVisible(true);


    }




}

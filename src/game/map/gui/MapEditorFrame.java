package game.map.gui;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gui.Board;
import game.image.ImageReader;
import game.map.MapEditor;
import game.thread.BombControlThread;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * 地图编辑框架
 * @author njdnhh
 */
public class MapEditorFrame extends JFrame {


    public MapEditorFrame(MapEditorBoard board){

        setTitle("!Boom!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900,700);
        add(board);
        requestFocus();

    }


}

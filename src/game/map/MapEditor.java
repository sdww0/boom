package game.map;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;
import game.gamedata.GameData;
import game.gui.GameFrame;
import game.map.gui.MapEditorFrame;
import game.map.mapio.MapOutPut;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 地图编辑，输出地图
 * @author njdnhh
 */
public class MapEditor {


    public static void main(String[] args){
        MapData.init();
        MapEditorFrame mainFrame = new MapEditorFrame(MapData.getBoard());
        MenuBar menuBar = new MenuBar();
        Menu canBreak = new Menu("Break?");
        Menu output = new Menu("Output Map");
        MenuItem outputItem = new MenuItem("confirm");
        MenuItem canBreak1 = new MenuItem("Yes");
        MenuItem canBreak2 = new MenuItem("No");
        MenuItem clear = new MenuItem("Clear");
        canBreak1.addActionListener(e -> MapData.canBreak = true);
        canBreak2.addActionListener(e -> MapData.canBreak = false);
        clear.addActionListener(e->MapData.clear = !MapData.clear);
        outputItem.addActionListener(e -> MapOutPut.createMapFile());
        canBreak.add(canBreak1);
        canBreak.add(canBreak2);
        canBreak.add(clear);
        output.add(outputItem);
        menuBar.add(canBreak);
        menuBar.add(output);
        mainFrame.add(MapData.getBoard());
        mainFrame.setMenuBar(menuBar);
        mainFrame.setVisible(true);

    }



}

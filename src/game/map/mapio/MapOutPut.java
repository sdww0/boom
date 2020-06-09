package game.map.mapio;

import game.gamedata.GameConstant;
import game.map.MapData;

import javax.swing.*;
import java.io.*;

/**
 * 输出地图
 * @author njdnhh
 */
public class MapOutPut {

    public static void createMapFile(){

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        OutputStream out = null;
        try {
             out = new FileOutputStream(f);


        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for(int n =0;n< GameConstant.SQUARE_AMOUNT;n++){
            str.append("{");
            for(int i =0;i< GameConstant.SQUARE_AMOUNT;i++){
                if(i==0){
                    str.append(MapData.getMap()[i][n]);
                }else {
                    str.append(","+MapData.getMap()[i][n]);
                }
            }
            str.append("}");
            if(n!=GameConstant.SQUARE_AMOUNT-1){
                str.append(",");
            }
            str.append("\n");
        }
        str.append("}\n");

        byte[] b = str.substring(0).getBytes();
        out.write(b);
        out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

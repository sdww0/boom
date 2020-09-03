package game.gui.escmenu;

import game.gui.Board;
import game.music.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class EscFrame extends JFrame {
    private JLayeredPane layeredPane;
    /**
     * 按钮列表
     * 暂定
     *  升高背景音乐音量大小
     *  降低背景音乐音量大小
     *  升高音效音量大小
     *  降低音效音量大小
     *  开启背景音乐
     *  关闭背景音乐
     *  开启音效
     *  关闭音效
     *
     *  返回游戏
     *  返回主菜单
     */
    private JButton[] buttonList;

    public EscFrame() {
        layeredPane = new JLayeredPane();
        buttonList = new JButton[10];
        setAllButton();

        setTitle("!Boom!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300, 600);

        setLayeredPane(layeredPane);
    }

    private void setAllButton(){
        buttonList[0] = new JButton("升高背景音乐音量大小");
        buttonList[1] = new JButton("降低背景音乐音量大小");
        buttonList[2] = new JButton("升高音效音量大小");
        buttonList[3] = new JButton("降低音效音量大小");
        buttonList[4] = new JButton("开启背景音乐");
        buttonList[5] = new JButton("关闭背景音乐");
        buttonList[6] = new JButton("开启音效");
        buttonList[7] = new JButton("关闭音效");
        buttonList[8] = new JButton("返回游戏");
        buttonList[9] = new JButton("返回主菜单");

        for(int n = 0;n<10;n++){
            buttonList[n].setBounds(50,50*(n+1),170,25);
            layeredPane.add(buttonList[n],JLayeredPane.POPUP_LAYER);
        }

        buttonList[0].addActionListener(e -> MusicPlayer.higherBackGroundMusicVolume());
        buttonList[1].addActionListener(e -> MusicPlayer.lowerBackGroundMusicVolume());
        buttonList[2].addActionListener(e -> MusicPlayer.higherShortMusicVolume());
        buttonList[3].addActionListener(e -> MusicPlayer.lowerShortMusicVolume());
        buttonList[4].addActionListener(e -> MusicPlayer.playBackGroundMusic(MusicPlayer.PLAYING_BACK_GROUND_MUSIC));
        buttonList[5].addActionListener(e -> MusicPlayer.stopBackGroundMusic(MusicPlayer.PLAYING_BACK_GROUND_MUSIC));
        buttonList[6].addActionListener(e -> MusicPlayer.playShortMusic());
        buttonList[7].addActionListener(e -> MusicPlayer.stopShortMusic());


    }


    public static void main(String[] args){
        new EscFrame().setVisible(true);

    }

}

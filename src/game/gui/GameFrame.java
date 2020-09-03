package game.gui;

import game.gamedata.GameData;
import game.music.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * swing游戏框架
 * @author njdnhh
 */
public class GameFrame extends JFrame {
    private JLayeredPane layeredPane;
    /**
     * 存储按键
     */
    public volatile HashSet<Integer> check ;

    public GameFrame(Board board) {
        check = new HashSet<>();
        layeredPane = new JLayeredPane();

        setTitle("!Boom!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 700);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu music = new Menu("Music");
        MenuItem start = new MenuItem("start");
        MenuItem stop = new MenuItem("stop");
        MenuItem lower = new MenuItem("lower Volume ");
        MenuItem higher = new MenuItem("higher Volume ");
        lower.addActionListener(e -> MusicPlayer.lowerBackGroundMusicVolume());
        higher.addActionListener(e -> MusicPlayer.higherBackGroundMusicVolume());
        start.addActionListener(e -> MusicPlayer.playBackGroundMusic(MusicPlayer.PLAYING_BACK_GROUND_MUSIC));
        stop.addActionListener(e -> MusicPlayer.stopBackGroundMusic(MusicPlayer.PLAYING_BACK_GROUND_MUSIC));
        menuBar.add(menu);
        menu.add(music);
        music.add(start);
        music.add(stop);
        music.add(lower);
        music.add(higher);
        setMenuBar(menuBar);

        layeredPane.add(board, JLayeredPane.MODAL_LAYER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(!check.contains(e.getKeyCode())){
                    check.add(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                check.remove(e.getKeyCode());
            }

        });

        requestFocus();
        setLayeredPane(layeredPane);
    }

    public void init() {
        layeredPane.add(GameData.getRightMenu(), JLayeredPane.MODAL_LAYER);
    }

    public void sendTask(int keyCode, boolean isPlayer1){
        if(isPlayer1){
            sendTaskToPlayer1(keyCode);
        }else{
            sendTaskToPlayer2(keyCode);
        }
    }

    private synchronized void sendTaskToPlayer1(int keyCode){
        if(GameData.player1==null){
            return;
        }
        if(keyCode==KeyEvent.VK_SPACE){
            GameData.player1.placeBomb();
            return;
        }
        GameData.player1.movePosition(keyCode);
    }

    private synchronized void sendTaskToPlayer2(int keyCode){
        if(GameData.player2==null){
            return;
        }
        if(keyCode==KeyEvent.VK_ENTER){
            GameData.player2.placeBomb();
            return;
        }
        GameData.player2.movePosition(keyCode);
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}

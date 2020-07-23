package game.gui;

import game.basement.Location;
import game.element.*;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * swing面板上组成原件
 * @author njdnhh
 */
public class Square extends JPanel {
    /**
     * 棋盘上含有什么元素（炸弹/墙壁）
     */
    private Object element;
    private Item item;
    /**
     * 如果无玩家在此方格上则为null
     * 如有则为该玩家
     */
    private volatile LinkedList<Player> players;
    public volatile boolean canSetElement;

    private Location squareLocation;
    private Color color;

    public Square(Location squareLocation, Color color) {
        players = new LinkedList<>();
        this.squareLocation = squareLocation;
        this.color = color;
        this.element = null;
        setLayout(new GridLayout(1, 1));
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        canSetElement = true;
    }

    @Override
    protected void paintComponent(Graphics g) {//画出原件位置的图像
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth() , getHeight() );

    }

    /**
     *
     * @return 含有的元素类型
     */
    public ElementType getElementType(){

        if(item!=null){
            return ElementType.ITEM;
        }
        if(this.element==null){
            if(players.size()>0){
                return ElementType.PLAYER;
            }
            return ElementType.NULL;
        }
        String n = this.element.getClass().getName();
        if(n.equals(GameConstant.BOOM_FILE_LOCATION)){
            return ElementType.BOOM;
        }else if(n.equals(GameConstant.BOMB_FILE_LOCATION)){
            return ElementType.BOMB;
        }else if(n.equals(GameConstant.WALLS_FILE_LOCATION)){
            Walls walls = (Walls)(element);
            if(walls.isCanBreak()){
                return ElementType.BREAKABLE_WALL;
            }else {
                return ElementType.UNBREAKABLE_WALL;
            }
        }else{
            throw new IllegalArgumentException("error Location:Square");
        }

    }

    /**
     * 防止多线程导致的数量不统一
     * @param isGetElement 是否是获得元素如果是直接返回元素
     * @param element 如果获得元素，传进来是null如果不是则设置
     * @return 返回元素
     */
    public synchronized Object getOrSetElement(boolean isGetElement,Object element){

        if(isGetElement){
            return this.element;
        }else{
            setElement(element);
            return null;
        }
    }


    /**
     * 不推荐使用，线程不安全
     * @return 含有的element
     */
    @Deprecated
    public Object getElement(){
        return element;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item) {
        if(item==null){
            if(this.item!=null){
                remove(this.item);
                this.item = null;
                repaint();
            }
        }else {
            if (this.item != null) {
                remove(this.item);
            }
            this.item = item;
            add(item);
            item.repaint();
        }

    }

    @Deprecated
    public void removeAllElement(){
        if(this.element!=null){
            remove((JComponent)this.element);
            this.element = null;
            repaint();
        }
    }

    /**
     *getter and setter
     */
    @Deprecated
    public synchronized void setElement(Object element) {
        removeAllElement();
        this.element = element;
        if(element==null){
            return;
        }
        add((JComponent) element);
        repaint();
    }

    public Location getSquareLocation() {
        return squareLocation;
    }

    public void setSquareLocation(Location squareLocation) {
        this.squareLocation = squareLocation;
    }

    public LinkedList<Player> getPlayer() {
        return players;
    }

    public void setPlayer(Player player) {
        if(player!=null){
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if(player!=null){
            players.remove(player);
        }
    }

    public void hurtPlayers(){
        for (Player value : players) {
            value.hurt();
        }
    }


    @Override
    public String toString() {
        if(players.size()!=0){
            return "1";
        }else {
            return "0";
        }

    }
}

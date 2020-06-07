package game.basement;

import java.awt.*;

/**
 * 墙壁的类
 * 含：
 * 是否可被破坏
 * 位置
 * 图片
 * @author njdnhh
 */
public class Walls {

    private boolean unBreak;
    private Location location;
    private Image image;

    public Walls(boolean unBreak, Location location, Image image) {
        this.unBreak = unBreak;
        this.location = location;
        this.image = image;
    }

    public Walls(boolean unBreak, Location location) {
        this.unBreak = unBreak;
        this.location = location;
    }

    public Walls(boolean unBreak) {
        this.unBreak = unBreak;
    }

    public boolean isUnBreak() {
        return unBreak;
    }

    public void setUnBreak(boolean unBreak) {
        this.unBreak = unBreak;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

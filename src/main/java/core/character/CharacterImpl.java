package core.character;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.terrain.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.util.List;
import com.google.gson.annotations.Expose;


public class CharacterImpl implements Character {

    private static final Image img = new ImageIcon("texture\\character\\character.png").getImage();
    private final int width = 16, height = 27;
    private final Rectangle hitBox;
    private final int indexGamepad;
    private final InetAddress address;
    private long updateHp = System.currentTimeMillis();
    @Expose
    private boolean showHp = false;
    private long showHpTime = System.currentTimeMillis();
    @Expose
    private float hp = 0.5f;
    private int[] anim = {0, 1, 2, 1};
    @Expose
    private int frame = 2;
    @Expose
    private boolean mirror = false;
    private int movingH = 0;
    private int movingV = 0;
    @Expose
    private int x = 150, y = 0;

    public CharacterImpl(int indexGamepad) {
        this.indexGamepad = indexGamepad;
        this.address = null;
        this.hitBox = new Rectangle(x, y, width, height);
    }

    public CharacterImpl(InetAddress address) {
        this.indexGamepad = -1;
        this.address = address;
        this.hitBox = new Rectangle(x, y, width, height);
    }

    public int getIndexGamepad() {
        return indexGamepad;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public long getUpdateHp() {
        return updateHp;
    }

    public boolean isShowHp() {
        return showHp;
    }

    public long getShowHpTime() {
        return showHpTime;
    }

    public int getFrame() {
        return frame;
    }

    public boolean isMirror() {
        return mirror;
    }

    public int getMovingH() {
        return movingH;
    }

    public int getMovingV() {
        return movingV;
    }

    @Override
    public void movingVertical(int speed) {
        movingV = speed;
        isStop();
    }

    @Override
    public void movingHorizontally(int speed) {
        movingH = speed;
        mirror = speed >= 0;
        isStop();
    }

    @Override
    public void isStop() {
        if (movingH == 0 && movingV == 0) {
            frame = 2;
        }
    }


    @Override
    public void stopMove() {
        movingH = 0;
        movingV = 0;
        frame = 2;
    }

    @Override
    public boolean cross(Point point) {
        return hitBox.contains(point);
    }

    @Override
    public boolean canGo(int dx, int dy, List<Tile> tiles) {
        for (Tile p : tiles)
            if (p.getBounds().intersects(x + dx, y + dy, width, height)) {
                return false;
            }
        return true;
    }

    @Override
    public void collide(int dx, int dy, List<Tile> tiles) {
        for (Tile p : tiles) {
            if (p.getBounds().intersects(x + dx, y + dy, width, height)) {
                if (dx != 0)
                    p.collisionH(this);
                if (dy != 0)
                    p.collisionV(this);
            }
        }
    }

    @Override
    public void tick(List<Tile> tiles) {
        if (hp < 1) {
            if (System.currentTimeMillis() - updateHp >= 1000) {
                hp += 0.01;
                updateHp = System.currentTimeMillis();
                showHpTime = System.currentTimeMillis();
                showHp = true;
            }
            if (hp > 1) {
                hp = 1;
            }
        }
        if (movingH != 0 || movingV != 0) {// animacja ruchu
            frame++;
            while (frame >= anim.length)
                frame -= anim.length;
        }
        // przesuniecie w poziomie
        for (int i = 0; i < Math.abs(movingH); ++i) {
            collide((int) Math.signum(movingH), 0, tiles);
            x += (int) Math.signum(movingH);
        }

        // przesuniecie w pionie
        for (int i = 0; i < Math.abs(movingV); ++i) {
            collide(0, -(int) Math.signum(movingV), tiles);
            y -= (int) Math.signum(movingV);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (showHp) {
            g.setColor(Color.RED);
            g.fillRect(x - 5, y - 10, (int) ((width + 10) * hp), 5);
            if (System.currentTimeMillis() - showHpTime >= 2000){
                showHp = false;
            }
        }
        g.drawImage(img, x + (mirror ? width : 0), y, x + (mirror ? 0 : width), y + height,
                anim[frame] * width, 0, anim[frame] * width + width, height, null);
    }

    @Override
    public String toJson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this);
    }

    @Override
    public void updateData(String jsonString) {
        Gson json = new Gson();
        Character character = json.fromJson(jsonString, CharacterImpl.class);
        x = character.getX();
        y = character.getY();
        hp = character.getHp();
        frame = character.getFrame();
        showHp = character.isShowHp();
        mirror = character.isMirror();
    }
}

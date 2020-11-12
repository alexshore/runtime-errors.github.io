package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eng.auber.AuberGame;
import com.badlogic.gdx.math.Rectangle;

public class AuberSystems extends Rectangle {

    boolean working;
    SpriteBatch batch;
    public Texture systemImg;
    int currentRoom;

    public AuberSystems(int x, int y, int room) {
        this.x = x;
        this.y = y;
        this.working = true;
        this.batch = new SpriteBatch();
        this.systemImg = new Texture("game_assets/system_working.png");
        this.currentRoom = room;
    }

    public boolean system_sabotage(){
        working = false;
        this.systemImg = new Texture("game_assets/system_working.png");
        return !working;
    }

}

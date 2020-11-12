package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eng.auber.AuberGame;
import com.badlogic.gdx.math.Rectangle;

public class AuberSystems extends Rectangle {

    boolean working;
    SpriteBatch batch;
    Texture sprite;

    public AuberSystems(int x, int y) {
        this.x = x;
        this.y = y;
        this.working = true;
        this.batch = new SpriteBatch();
        this.sprite = new Texture("game_assets/system_working.png");
    }

}

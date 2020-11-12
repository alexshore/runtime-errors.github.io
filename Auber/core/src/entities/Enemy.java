package entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Enemy {
    private int x,y;
    private boolean capture;
    Texture txtEnemy;
    public Enemy() {
        Random rd = new Random();
        this.x = rd.nextInt(1000) + 1;
        this.y = rd.nextInt(1000) + 1;
        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
    }
    void beenCaptured(){
        this.capture = true;
        x = 500;
        y = 500;
    }
    boolean isCaptured(){
        return this.capture;
    }

}

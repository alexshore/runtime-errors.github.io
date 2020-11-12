package entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Enemy {
    public int x,y;
    private boolean capture;
    public Texture txtEnemy;
    Random rd;

    public Enemy() {
        rd = new Random();
        this.x = rd.nextInt(1000) + 1;
        this.y = rd.nextInt(1000) + 1;
        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
    }
    public void beenCaptured(){
        this.capture = true;
        this.rd = new Random();
        this.x = rd.nextInt(200) + 500;
        this.y = rd.nextInt(200) + 500;
    }
    public boolean isCaptured(){
        return this.capture;
    }

}

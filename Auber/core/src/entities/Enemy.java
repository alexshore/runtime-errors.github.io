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
        this.x = rd.nextInt(975) + 1;//975 so they print in boundaries
        this.y = rd.nextInt(975) + 1;
        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
    }
    public void beenCaptured(){
        this.capture = true;
        this.rd = new Random();
        this.x = rd.nextInt(200) + 300;
        this.y = rd.nextInt(200) + 300;
    }
    public boolean isCaptured(){
        return this.capture;
    }
    public void randomMove(){

    };

}

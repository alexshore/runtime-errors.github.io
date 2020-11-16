package entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    public int x,y;
    private boolean capture;
    public Texture txtEnemy;
    public Room currentRoom;
    Random rd;

    public Enemy() {
        rd = new Random();
        this.x = 500;
        this.y = 500;

        while (x > 225 && y> 225 && x <750 && y<750 ){
            //while statement makes sure no enemies spawn in the brig
            this.x = rd.nextInt(975) + 1;//975 so they print in boundaries
            this.y = rd.nextInt(975) + 1;
        }

        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
    }
    public void beenCaptured(){
        //moves enemy to random space in the brig and sets their status to captured
        this.capture = true;
        this.rd = new Random();
        this.x = rd.nextInt(200) + 325;
        this.y = rd.nextInt(200) + 325;
    }
    public boolean isCaptured(){
        //returns if enemy is captured
        return this.capture;
    }

    public boolean allCaptured(ArrayList<Enemy> enemies){
        //returns true if all enemies have been captured
        int count = 0;
        for(Enemy e: enemies){
            if (e.isCaptured()){
                count ++;
            }
        }
        return enemies.size() == count;
    }

    public void randomMove(){
        //for random Movement of enemies
    };


}

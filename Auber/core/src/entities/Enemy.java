package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import returns.values;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private int x,y, ability;
    private boolean capture;
    private Texture txtEnemy;
    private Random rd;

    public Enemy() {
        rd = new Random();
        this.x = 500;
        this.y = 500;
        while (x > 225 && y> 225 && x <750 && y<750 ){
            //while statement makes sure no enemies spawn in the brig
            this.setX(rd.nextInt(975) + 1);
            this.setY(rd.nextInt(975) + 1);
        }
        this.setAbility();
        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void  setY(int y){
        this.y = y;
    }

    public Texture getTexture(){
        return this.txtEnemy;
    }

    public void beenCaptured(){
        //moves enemy to random space in the brig and sets their status to captured
        this.capture = true;
        this.rd = new Random();
        this.setX(this.rd.nextInt(200) + 325);
        this.setY(this.rd.nextInt(200) + 325);
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

    private void setAbility(){
        this.rd = new Random();
        ability = this.rd.nextInt(2);
        // 0 = invisibility
        // 1 = super speed
        // 2 = slow player
    }

    public int getAbility(){
        return this.ability;
    }

    public values getDest(float x, float y, String room_id, Array<Room> Rooms) {
        float destX = 0; float destY = 0;

        for (Room Room: Rooms) {
            if (Room.identifier == room_id) {
                Room current_room = Room;
            }
        }



        return new values(destX, destY);
    }

}

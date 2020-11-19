package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private int x, y, ability;
    private boolean capture;
    private final Texture txtEnemy;
    private Random rd;
    public boolean hasDest;
    public float destX, destY;
    public Room current_room;

    public Enemy() {
        rd = new Random();
        this.x = 500;
        this.y = 500;
        while (!(x > 251 && y > 76 && x < 740 && y < 225) ) {
            //while statement makes sure no enemies spawn in the brig
            this.setX(rd.nextInt(450) + 250);
            this.setY(rd.nextInt(150) + 75);
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

    public void setY(int y){
        this.y = y;
    }

    public Texture getTexture(){
        return this.txtEnemy;
    }

    public Room findRoom(Array<Room> Rooms) {
        Room room_out = null;

        for (Room Room: Rooms) {
            if (!(Room.identifier.equals("outer") || Room.identifier.equals("inner"))) {
                if (this.x >= Room.lower_x_collision && this.y >= Room.lower_y_collision &&
                    this.x <= Room.upper_x_collision && this.y <= Room.upper_y_collision) {
                    room_out = Room;
                }
            }
        }
        return room_out;
    }

    public void beenCaptured() {
        //moves enemy to random space in the brig and sets their status to captured
        this.capture = true;
        this.rd = new Random();
        this.setX(this.rd.nextInt(200) + 325);
        this.setY(this.rd.nextInt(200) + 325);
    }

    public boolean isCaptured() {
        //returns if enemy is captured
        return this.capture;
    }

    public boolean allCaptured(ArrayList<Enemy> enemies) {
        //returns true if all enemies have been captured
        int count = 0;
        for (Enemy e: enemies) {
            if (e.isCaptured()) {
                count ++;
            }
        }
        return enemies.size() == count;
    }

    private void setAbility() {
        this.rd = new Random();
        ability = this.rd.nextInt(2);
        // 0 = invisibility
        // 1 = super speed
        // 2 = damage
    }


    public int getAbility(){
        return this.ability;
    }

    public void getDest(float x, float y, String room_id, Array<Room> Rooms) {
        for (AuberSystems System: current_room.Systems) {
            if (!System.currently_assigned && System.working) {
                System.currently_assigned = true;
                this.hasDest = true;
                this.destX = System.getX() + 1;
                this.destY = System.getY() + 1;
                return;
            }
        }
        Door door_dest = current_room.Doors.random();
        while (door_dest.upper_room == "outer" || door_dest.lower_room == "inner" ||
                door_dest.lower_room == "outer" || door_dest.upper_room == "inner") {
            door_dest = current_room.Doors.random();
        }
        if (door_dest != null) {
            this.hasDest = true;
            if (door_dest.lower_room.equals(current_room.identifier)) {
                this.destX = door_dest.lower_x + 1;
                this.destY = door_dest.lower_y + 1;
            }
            if (door_dest.upper_room.equals(current_room.identifier)) {
                this.destX = door_dest.upper_x + 1;
                this.destY = door_dest.upper_y + 1;
            }
        }

    }

}

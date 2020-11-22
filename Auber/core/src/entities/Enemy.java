package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private int enemy_w = 25, enemy_h = 25;
    private int x, y, ability;
    private boolean capture;
    private Texture txtEnemy;
    private Random rd;
    public boolean hasDest;
    public float destX, destY;
    public Room current_room;
    public int cooldown, abilitytime;
    public int speed = 1;
    boolean abilityUsed;
    public Bomb healthBomb;
    public int sysDelay;


    public Enemy() {
        /*
        Instantiates Enemy object
         */

        rd = new Random();
        this.x = 500;
        this.y = 500;
        this.hasDest = false;
        this.sysDelay = 0;
        while (!(x > 251 && y > 76 && x < 740 && y < 225) ) {
            //while statement makes sure no enemies spawn in the brig
            this.setX(rd.nextInt(450) + 250);
            this.setY(rd.nextInt(150) + 75);
        }
        this.setAbility();
        if (this.getAbility() == 3){
            healthBomb = new Bomb("engine_room");
        }
        this.capture = false;
        this.txtEnemy = new Texture("game_assets/enemy.png");
        this.cooldown = -1;
        this.abilitytime = -1;
        this.abilityUsed = false;
    }
    public boolean sysCooldownRunning() {
        /*
        Returns boolean for is the cooldown system is running or not
         */
        if(this.sysDelay >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updateSysCooldown() {
        /*
        Updates the system sabotage delay
         */
        this.sysDelay++;
        if(this.sysDelay > 500) {
            this.sysDelay = -1;
        }
    }


    public Bomb returnHealthBomb(){
        /*
        Allows other classes to access the Bomb variable for the enemy
        :return healthBomb: returns an instance of Bomb object
        :return None: if the ability doesn't exist with an enemy returns None
         */
        if(getAbility() ==3){
            return healthBomb;
        }
        else{
            return null;
        }
    }
    public int getX(){
        /*
        Returns x coordinate of enemy
         */
        return this.x;
    }

    public int getY(){
        /*
        returns y coordinate of enemy
         */
        return this.y;
    }

    public void setX(int x){
        /*
        sets X variable of enemy
        :param x: integer x variable
         */
        this.x = x;
    }

    public void setY(int y){
        /*
        sets y variable of enemy
        :param y: integer y variable
         */
        this.y = y;
    }

    public Texture getTexture(){
        /*
        returns Enemy texure
         */
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
        /*
        Sets ability of enemy object
         */
        this.rd = new Random();
        ability = this.rd.nextInt(3) +1;
        // 1 = invisibility
        // 2 = super speed
        // 3 = damage
    }

    public int tryAbility(Room player){
        /*
        gives ability for the enemy and checks if player is in same room if this is the case and the cooldown is over
        it returns the corresponding int for the GameScreen class to interpret
        :param player: Room object containing where the current player is
        :return: returns the ability of the player, returns -1 if this isn't possible
         */
        if (this.capture){
            return -1;
        }
        if(cooldown == -1 && abilitytime == -1) {
            if (sameRoom(player)) {
                abilitytime = 0;
                if (this.getAbility() == 1) {
                    return 1;
                } else if (this.getAbility() == 2) {
                    return 2;
                } else if (this.getAbility() == 3) {
                    System.out.println("2");
                    this.healthBomb.currroom = player.identifier;
                    this.healthBomb.setXY(this.x,this.y);
                    this.healthBomb.active = true;
                    return 3;
                }
            }
        }
        else if(abilitytime > -1 && abilitytime < 240){//when t=ability time us less than 4 sec
            abilitytime ++;
            if(getAbility() == 3) {
                if (abilitytime == 120 && player.identifier.equals(this.healthBomb.currroom)) {
                    this.healthBomb.explode = true;
                    this.healthBomb.active = false;
                }
                else if (abilitytime == 121 && getAbility() == 3) {
                    this.healthBomb.blast = true;
                }
                if(abilitytime == 122){
                    this.healthBomb.explode = false;
                }
            }
            return getAbility();
        }
        else if (abilitytime == 240){ //~ 4 seconds then abiility ends
            abilitytime = 1000;
            cooldown = 0;
            if (this.getAbility() ==3) {
                this.healthBomb.explode = false;
                this.healthBomb.active = false;
                this.healthBomb.blast = false;

            }
            return this.ability;
        }
        else if(cooldown > -1 && cooldown <1200){//count up cooldown
            cooldown ++;
        }
        else if (cooldown == 1200 && abilitytime == 1000 ){//once cooldown and ability end
            abilitytime = -1;
            cooldown = -1;
        }
        return -1;
    }

    private boolean sameRoom(Room player){
        /*
        returns true if player in same room as enemy
        :param player: Room object containing where the current player is
        :return: returns true or false based on .equals
         */
        return current_room.identifier.equals(player.identifier);
    }

    public int getAbility(){
        /*
        returns player ability
         */
        return this.ability;
    }

    public void enterDoor(Door Door, Array<Room> Rooms) {
        if (Door.lower_room.equals(current_room.identifier)) {
            if (Door.direction.equals("h")) {
                this.x = Door.upper_x + 10;
                this.y = Door.upper_y;
            } else if (Door.direction.equals("v")) {
                this.x = Door.upper_x;
                this.y = Door.upper_y + 10;
            }
            for (Room Room: Rooms) {
                if (Door.upper_room.equals(Room.identifier)) {
                    current_room = Room;
                }
            }
        } else if (Door.upper_room.equals(current_room.identifier)) {
            if (Door.direction.equals("h")) {
                this.x = Door.upper_x - (10 + enemy_w);
                this.y = Door.upper_y;
            } else if (Door.direction.equals("v")) {
                this.x = Door.upper_x;
                this.y = Door.upper_y - (10 + enemy_h);
            }
            for (Room Room: Rooms) {
                if (Door.lower_room.equals(Room.identifier)) {
                    current_room = Room;
                }
            }
        }
    }

    public void getDest() {
        /*
        Gets destination for the enemy to go to next
         */
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
        while (true) {
            if (door_dest.upper_room.equals("outer") || door_dest.lower_room.equals("inner") || door_dest.lower_room.equals("outer") || door_dest.upper_room.equals("inner")) {
                door_dest = current_room.Doors.random();
            } else {
                break;
            }
        }
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
    public void breakSys(){
        /*
        after system break has occurred called to allow enemy to find new destination
         */
        this.hasDest = false;
    }
}

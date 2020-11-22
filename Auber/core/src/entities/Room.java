package entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Room extends Rectangle {

    public Array<Room> Neighbours = new Array<>();
    public Array<Door> Doors = new Array<>();
    public Array<AuberSystems> Systems = new Array<>();
    public boolean corridor;
    public String identifier;
    public int outer_width;
    public int lower_x_collision, lower_y_collision, upper_x_collision, upper_y_collision;
    public int i_lower_x_collision, i_lower_y_collision, i_upper_x_collision, i_upper_y_collision;
    public int width, height;

    public Room(int x, int y, int width, int height, String id, boolean corridor, int outer_width) {
        /*
        Initialises room object in particular for corridors to allow collision detection
        :param : x: lowest x coordinate of the corridor
        :param : y: lowest y coordinate of the corridor
        :param : height: takes how long the corridor is
        :param : id: String takes the name of this continuous corridor
        :param : corridor:takes boolean value for if this
        :param : outer width: takes how many pixels wide in int the corridor is
         */
        this.width = width;
        this.height = height;
        this.lower_x_collision = x;
        this.upper_x_collision = x + width;
        this.lower_y_collision = y;
        this.upper_y_collision = y + height;
        this.identifier = id;

        this.corridor = corridor;
        this.outer_width = outer_width;
        if (corridor) {
            this.i_lower_x_collision = lower_x_collision + outer_width;
            this.i_upper_x_collision = upper_x_collision - outer_width;
            this.i_lower_y_collision = lower_y_collision + outer_width;
            this.i_upper_y_collision = upper_y_collision - outer_width;
        }
    }

    public Room(int x, int y, int width, int height, String id) {
        /*
        Initialises room object in particular for rooms to allow collision detection
        :param : x: lowest x coordinate of the room
        :param : y: lowest y coordinate of the room
        :param : height: takes height of room pixels as int
        :param : id: String takes the name of this room
         */
        this.width = width;
        this.height = height;
        this.lower_x_collision = x;
        this.upper_x_collision = x + width;
        this.lower_y_collision = y;
        this.upper_y_collision = y + height;
        this.identifier = id;

    }

    @Override
    public float getX() {
        /*
        :return lower_x_collision: returns the lowest x position of the corridor
         */
        return lower_x_collision;
    }

    @Override
    public float getY() {
        /*
        :return lower_y_collision: returns the lowest x position of the corridor
         */
        return lower_y_collision;
    }
}



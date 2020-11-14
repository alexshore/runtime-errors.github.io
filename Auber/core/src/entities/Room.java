package entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Room extends Rectangle {

    public Array<Room> Neighbours = new Array<>();
    public Array<Door> Doors = new Array<>();
    public boolean currently_occupied;
    public boolean corridor;
    public String identifier;
    public int outer_width;
    public int lower_x_collision, lower_y_collision, upper_x_collision, upper_y_collision;
    public int i_lower_x_collision, i_lower_y_collision, i_upper_x_collision, i_upper_y_collision;

    public Room(int x, int y, int width, int height, String identifier, boolean corridor, int outer_width) {

        //to detect collision between defined walls
        this.identifier = identifier;
        this.corridor = corridor;
        this.outer_width = outer_width;
        this.lower_x_collision = x;
        this.upper_x_collision = x + width;
        this.lower_y_collision = y;
        this.upper_y_collision = y + height;
        if (corridor) {
            this.i_lower_x_collision = lower_x_collision + outer_width;
            this.i_upper_x_collision = upper_x_collision - outer_width;
            this.i_lower_y_collision = lower_y_collision + outer_width;
            this.i_upper_y_collision = upper_y_collision - outer_width;
        }
        this.currently_occupied = false;
    }

    public Room(int x, int y, int width, int height, String identifier) {
        this.lower_x_collision = x;
        this.upper_x_collision = x + width;
        this.lower_y_collision = y;
        this.upper_y_collision = y + height;
        this.currently_occupied = false;
    }


}

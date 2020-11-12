package entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Room extends Rectangle {

    public Array<Room> Neighbours = new Array<>();
    public boolean currently_occupied;
    public String identifier;
    public int lower_x_collision, lower_y_collision, upper_x_collision, upper_y_collision;
    public int i_lower_x_collision, i_lower_y_collision, i_upper_x_collision, i_upper_y_collision;

    public Room(int x, int y, int width, int height, String identifier, boolean corridor, int outer_width) {

        //to detect collision between defined walls
        this.identifier = identifier;
        lower_x_collision = x;
        upper_x_collision = x + width;
        lower_y_collision = y;
        upper_y_collision = y + height;
        if (corridor) {
            i_lower_x_collision = lower_x_collision + outer_width;
            i_upper_x_collision = upper_x_collision - outer_width;
            i_lower_y_collision = lower_y_collision + outer_width;
            i_upper_y_collision = upper_y_collision - outer_width;
        }
        currently_occupied = false;
    }

    public Room(int x, int y, int width, int height, String identifier) {
        lower_x_collision = x;
        upper_x_collision = x + width;
        lower_y_collision = y;
        upper_y_collision = y + height;
        currently_occupied = false;
    }


}

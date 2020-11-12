package entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Room extends Rectangle {

    public Array<Room> Neighbours = new Array<>();

    public Room(int x, int y, int width, int height, boolean corridor, int outer_width) {
        int lower_x_collision = x;
        int upper_x_collision = x + width;
        int lower_y_collision = y;
        int upper_y_collision = y + height;
        if (corridor) {
            int i_lower_x_collision = lower_x_collision + outer_width;
            int i_upper_x_collision = upper_x_collision - outer_width;
            int i_lower_y_collision = lower_y_collision + outer_width;
            int i_upper_y_collision = upper_y_collision - outer_width;
        }
        boolean currently_occupied = false;
    }

    public Room(int x, int y, int width, int height) {
        int lower_x_collision = x;
        int upper_x_collision = x + width;
        int lower_y_collision = y;
        int upper_y_collision = y + height;
        boolean currently_occupied = false;
    }
}

package entities;

public class Door {

    public int lower_x, lower_y, upper_x, upper_y;
    public final int door_detection_w = 35;
    public final int door_detection_d = 20;
    public String lower_room, upper_room;
    public String direction;

    public Door(String direction, int x, int y, String lower_room, String upper_room) {
        this.lower_x = x;
        this.lower_y = y;
        this.lower_room = lower_room;
        this.upper_room = upper_room;
        this.direction = direction;
        if (this.direction.equals("h")) {
            this.upper_x = lower_x + door_detection_d;
            this.upper_y = y;
        } else if (this.direction.equals("v")) {
            this.upper_x = x;
            this.upper_y = lower_y + door_detection_d;
        }

    }


    public boolean playerDetected(float x, float y) {

        if (this.direction.equals("h")) {
            return this.lower_x <= x + 12 && this.lower_y <= y + 12 &&
                    this.upper_x + door_detection_d >= x + 12 &&
                    this.upper_y + door_detection_w >= y + 12;
        } else if (this.direction.equals("v")) {
            return this.lower_x <= x + 12 && this.lower_y <= y + 12 &&
                    this.upper_x + door_detection_w >= x + 12 &&
                    this.upper_y + door_detection_d >= y + 12;
        } else {
            return false;
        }
    }
}

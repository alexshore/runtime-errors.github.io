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
        if (this.direction == "h") {
            this.upper_x = lower_x + door_detection_d;
            this.upper_y = y;
        } else if (this.direction == "v") {
            this.upper_x = x;
            this.upper_y = lower_y + door_detection_d;
        }

    }


    public boolean playerDetected(int x, int y, String identifier) {

        if (this.direction == "h") {
            if (identifier == this.lower_room) {
                if (this.lower_x < x + 12 && this.lower_y < y + 12 &&
                        this.lower_x + door_detection_d > x + 12 &&
                        this.lower_y + door_detection_w > y + 12) {
                    return true;
                }
            } else if (identifier == this.upper_room) {

            }
        }



        return true;
    }
}

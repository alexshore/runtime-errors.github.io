package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class AuberSystems extends Rectangle {

    boolean working;
    SpriteBatch batch;
    public Texture systemImg;
    public int x, y;
    public String room;
    public boolean currently_assigned;

    public AuberSystems(int x, int y, String room) {
        this.x = x;
        this.y = y;
        this.room = room;
        this.working = true;
        this.currently_assigned = false;
        this.batch = new SpriteBatch();
        this.systemImg = new Texture("game_assets/system_working.png");
    }

    public void doSabotage() {
        //Called when sabotage has happened
        this.working = false;
        this.systemImg = new Texture("game_assets/system_working.png");
    }

    public boolean isWorking() {
        //returns true or false
        return this.working;
    }

    public boolean hasLost(ArrayList<AuberSystems> sys) {
        //checks if player has lost
        int count = 0;
        for (AuberSystems e: sys) {
            if (!e.isWorking()) {
                count++;
            }
        }
        return count == sys.size();
    }
    public int[] getCoord() {
        //returns coordinates of the system as int array length 2
        return new int[] {this.x, this.y};
    }

}

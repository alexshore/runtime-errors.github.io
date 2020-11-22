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

    public AuberSystems(float x, float y, String room) {
        /*Instantiates an AuberSystems object
        :param x : gives the x position of the system
        :param y : gives the y position of the system
        :param room : gives the id of the room that the system is in
        (the id relates to the Room class)
         */
        super(x, y, 40, 40);
        this.room = room;
        this.working = true;
        this.currently_assigned = false;
        this.batch = new SpriteBatch();
        this.systemImg = new Texture("game_assets/system_working.png");
    }
    public boolean enemyInSystem(Enemy enemyObject) {
        /*Checks if the given enemy sprite is overlapping the system sprite
        if not, return false
        if it is, return true and break the system
        :param enemyObject : gives the enemy object to be checked
         */
        int enemyLeftX = enemyObject.getX();
        int enemyBottomY = enemyObject.getY();
        int enemyRightX = enemyLeftX + 25;
        int enemyTopY = enemyBottomY + 25;
        if (super.contains(enemyLeftX,enemyBottomY) ||
                super.contains(enemyRightX,enemyTopY) ||
                super.contains(enemyRightX,enemyBottomY) ||
                super.contains(enemyLeftX,enemyTopY)){
            this.doSabotage();
           return true;
        }
        return false;
    }


    public void doSabotage() {
        /*breaks the system
         */
        this.working = false;
        this.systemImg = new Texture("game_assets/system_broken.png");
    }

    public boolean isWorking() {
        /*returns a boolean depending on if the system is working
         */
        return this.working;
    }

    public boolean hasLost(ArrayList<AuberSystems> sys) {
        /*checks whether all the systems have been destroyed
        :param sys : gives an ArrayList of all the system objects
        :return count : returns the number of systems destroyed
         */
        int count = 0;
        for (AuberSystems e: sys) {
            if (!e.isWorking()) {
                count++;
            }
        }
        return count == sys.size();
    }
    public float[] getCoord() {
        /*returns coordinates of the system as int array length 2
         */
        return new float[] {super.x, super.y};
    }

}

package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class AuberSystems extends Rectangle {

    boolean working;
    SpriteBatch batch;
    public Texture systemImg;
    int x,y;

    public AuberSystems(int x, int y){
        this.x = x;
        this.y = y;
        this.working = true;
        this.batch = new SpriteBatch();
        this.systemImg = new Texture("game_assets/system_working.png");
    }

    public void doSabotage(){
        //Called when sabotage has happened
        this.working = false;
        this.systemImg = new Texture("game_assets/system_working.png");
    }

    public boolean isWorking(){
        //returns true or false
        return this.working;
    }

    public boolean hasLost(ArrayList<AuberSystems> sys) {
        //checks if player has lost
        int count = 0;
        for (AuberSystems e : sys) {
            if (!e.isWorking()) {
                count++;
            }
        }
        return  count == sys.size();
    }
    public int[] getCoord(){
        //returns coordinates of the system as int array length 2
        return new int[]{this.x,this.y};
    }

}

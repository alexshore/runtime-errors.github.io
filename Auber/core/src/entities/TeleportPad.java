package entities;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class TeleportPad extends Rectangle {
    public Texture teleporterSprite;
    public TeleportPad linkedTeleporter;
    int xCoord;
    int yCoord;
    SpriteBatch batch;

    public TeleportPad(int xCoord,int yCoord) {
        // Instantiates teleporter - however, teleporter is initially not linked
        // to any other teleporter
        super(xCoord,yCoord,50,50);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.batch = new SpriteBatch();
        this.teleporterSprite = new Texture("game_assets/teleporter_pad.png");
    }

    public void setLinkedTeleporter(TeleportPad nextTeleporter) {
        // Sets the destination of this teleporter to the location of another
        // teleporter
        this.linkedTeleporter = nextTeleporter;
    }
    public boolean canTeleport(float playerX, float playerY){
        if(super.contains(playerX,playerY)) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Integer> teleport(){
        //returns an arraylist of the coords of the next teleporter.
        ArrayList<Integer> teleportCoords = new ArrayList<Integer>();
        teleportCoords.add(this.linkedTeleporter.xCoord);
        teleportCoords.add(this.linkedTeleporter.yCoord);
        return teleportCoords;
        }
}

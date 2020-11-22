package entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;

public class TeleportPad extends Rectangle {
    public Texture teleporterSprite;
    public TeleportPad linkedTeleporter;
    public String id;
    int xCoord;
    int yCoord;
    SpriteBatch batch;

    public TeleportPad(int xCoord,int yCoord, String id) {
        /*Instantiates teleporter - however, teleporter is initially not linked
        to any other teleporter
        :param xCoord : gives the x position of the teleporter on the map
        :param yCoord : gives the y position of the teleporter on the map
         */
        super(xCoord,yCoord,50,50);
        this.id = id;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.batch = new SpriteBatch();
        this.teleporterSprite = new Texture("game_assets/teleporter_pad.png");
    }

    public void setLinkedTeleporter(TeleportPad nextTeleporter) {
        /* Sets the destination of this teleporter to the location of another teleporter
        :param nextTeleporter : gives the teleporter object that the player will be teleported to
         */
        this.linkedTeleporter = nextTeleporter;
    }
    public boolean canTeleport(float playerX, float playerY){
        /* returns true if the coordinates of the centre of the player sprite
         are on the teleport pad
        :param playerX : gives player x position
        :param playerY : gives player y position
         */
        return super.contains(playerX + 12, playerY + 12);
    }

    public ArrayList<Integer> teleport(){
        /*returns an arraylist of the coordinates of the next teleporter.
        :return teleportCoords : gives the coordinates of the position that
        the player will be moved to as an ArrayList
         */
        ArrayList<Integer> teleportCoords = new ArrayList<Integer>();
        teleportCoords.add(this.linkedTeleporter.xCoord);
        teleportCoords.add(this.linkedTeleporter.yCoord);
        return teleportCoords;
        }
}

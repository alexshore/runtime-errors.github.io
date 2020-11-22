package entities;

public class Bomb {
    public String currroom;
    public boolean active,explode,blast;
    private int x,y;

    public Bomb(String roomid){
        /*
        Initialises bomb object which is part of enemy special ability
        :param : roomid: string for what room the bomb is in
         */
        this.currroom = roomid;
        active = false;
        explode = false;
    }

    public void setXY(int x, int y ){
        /*
        sets both x and y coordinate
        :param : x: x coordinate of the bomb as int
        :param : y: y coordinate of the bomb as int
         */
        this.x = x;
        this.y = y;
    }

    public int getX(){
        /*
        return: this.x: returns x value of bomb
         */
        return this.x;
    }
    public int gety(){
        /*
        returns y value of bomb
         */
        return this.y;
    }


}

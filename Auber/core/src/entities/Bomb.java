package entities;

public class Bomb {
    public String currroom;
    public boolean active,explode,blast;
    private int x,y;

    public Bomb(String roomid){
        this.currroom = roomid;
        active = false;
        explode = false;
    }

    public void setXY(int x, int y ){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int gety(){
        return this.y;
    }





    public void deactivate(){
        this.active = false;
    }

    public boolean damage(){
        return this.explode;
    }


}

package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.eng.auber.AuberGame;
import entities.AuberSystems;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import entities.Room;


public class GameScreen extends ScreenAdapter {
    public SpriteBatch batch;
    private int playerSpeed = 3;
    AuberGame game;
    boolean demoMode;
    Texture playerTexture,  backgroundTexture;
    float x = 495, y = 495;
    int player_h, player_w;

    private List<AuberSystems> System_Auber;
    public Array<Room> Rooms;
    public Room current_room;
    // rooms defined using primitive arrays
    private static final int[] x_sys = {82,210,210,82,82,275,550,700,850,883,752,840,883,500,650,350};
    private static final int[] y_sys = {600,850,125,458,275,77,208,77,77,459,265,503,876,750,875,875};
    private static final int[] room = {6,6,8,8,8,5,5,5,9,9,9,7,7,4,4,4};


    public GameScreen(AuberGame game, boolean demoMode){
        this.demoMode  = demoMode;
        this.game = game;
        this.batch = new SpriteBatch();
        this.playerTexture = new Texture("game_assets/P_temp.png");
        this.player_h = 25;
        this.player_w = 25;
        this.System_Auber = new ArrayList<AuberSystems>();
        this.backgroundTexture = new Texture("game_assets/maps/station_design.png");
        this.Rooms = new Array<>();
        for(int i = 0; i<16;i++){
            AuberSystems a = new AuberSystems(x_sys[i],y_sys[i], room[i]);
            System_Auber.add(a);
        }


        Room outer_corridor = new Room(0, 0, 1000, 1000, "outer", true, 75);
        Room inner_corridor = new Room(250, 250, 500, 500, "inner", true, 75);
        Room infirmary = new Room(250, 750, 500, 175, "infirmary");
        Room engine_room = new Room(250, 75, 500, 175, "engine_room");
        Room cargo_left = new Room(75, 500, 175, 500, "cargo_left");
        Room cargo_right = new Room(750, 500, 175, 500, "cargo_right");
        Room living_left = new Room(75, 75, 175, 500, "living_left");
        Room living_right = new Room(750, 75, 175, 500, "living_right");

        Room brig = new Room(325, 325, 350, 350, "brig");
        brig.currently_occupied = true;
        this.current_room = brig;

        outer_corridor.Neighbours.add(cargo_left, cargo_right, living_left, living_right);
        inner_corridor.Neighbours.add(cargo_left, cargo_right, living_left, living_right);
        inner_corridor.Neighbours.add(brig);
        outer_corridor.Neighbours.addAll(cargo_left, cargo_right, living_left, living_right);
        inner_corridor.Neighbours.addAll(brig, cargo_left, cargo_right, living_left, living_right);
        brig.Neighbours.add(inner_corridor);
        infirmary.Neighbours.addAll(cargo_left, cargo_right);
        engine_room.Neighbours.add(living_left, living_right);
        cargo_left.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_left);
        cargo_right.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_right);
        living_left.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_left);
        living_right.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_right);
        Rooms.addAll(outer_corridor, inner_corridor, brig, infirmary, engine_room, cargo_left, cargo_right, living_left, living_right);


    }



    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        for (Room Room: Rooms) {
            if (Room.currently_occupied) {
                current_room = Room;
                break;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += playerSpeed;
            if (x + player_w > current_room.upper_x_collision) {
                x = current_room.upper_x_collision - player_w;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= playerSpeed;
            if (x < current_room.lower_x_collision) {
                x = current_room.lower_x_collision;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += playerSpeed;
            if (y + player_h > current_room.upper_y_collision) {
                y = current_room.upper_y_collision - player_h;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= playerSpeed;
            if (y < current_room.lower_y_collision) {
                y = current_room.lower_y_collision;
            }
        }


//        Basic Player Movement Input Handler
//        if (!demoMode){
//            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//                x += playerSpeed;
//                if (x + player_w > Gdx.graphics.getWidth()) {
//                    x = Gdx.graphics.getWidth() - player_w;
//                }
//            }
//            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//                x -= playerSpeed;
//                if (x < 0) {x = 0;}
//            }
//            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//                y += playerSpeed;
//                if (y + player_h > Gdx.graphics.getHeight()) {
//                    y = Gdx.graphics.getHeight() - player_h;
//                }
//            }
//            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//                y -= playerSpeed;
//                if (y < 0) {y = 0;}
//            }
//
//            ///potential e interact subroutine
//            if(Gdx.input.isKeyPressed(Input.Keys.E)){
//
//            }
//        }
//        else{
//
//        }

        batch.begin();
        //draws map and player
        batch.draw(backgroundTexture,0,0,1000,1000);
        batch.draw(playerTexture, x, y, 25, 25);
        for (int i = 0; i<16; i++){
            batch.draw(System_Auber.get(i).systemImg,System_Auber.get(i).x,System_Auber.get(i).y,40,40);
        }
        batch.end();
        //checks to see if escape key pressed to return to main menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        batch.dispose();
    }
}

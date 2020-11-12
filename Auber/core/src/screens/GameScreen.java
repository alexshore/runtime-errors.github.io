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
    float x= 500;
    float y= 500;
    int player_h, player_w;

    private List<AuberSystems> System_Auber;
    private static final int[] x_sys_array = {325};
    private static final int[] y_sys_array = {325};
    private Array<Room> Rooms;


    public GameScreen(AuberGame game, boolean demoMode){
        this.demoMode  = demoMode;
        this.game = game;
        this.batch = new SpriteBatch();
        this.playerTexture = new Texture("game_assets/P_temp.png");
        this.player_h = 25;
        this.player_w = 25;
        this.System_Auber = new ArrayList<AuberSystems>();
        this.backgroundTexture = new Texture("game_assets/station_design.png");
        this.Rooms = new Array<>();

        //creating the systems
        for(int i = 0; i<15;i++){
            AuberSystems a = new AuberSystems(x_sys_array[0], y_sys_array[0], 1);
            System_Auber.add(a);
        }


        //defining the rooms
        Room outer_corridor = new Room(0, 0, 1000, 1000, true, 75);
        Room inner_corridor = new Room(250, 250, 500, 500, true, 75);
        Room brig = new Room(325, 325, 350, 350);
        Room infirmary = new Room(250, 750, 500, 175);
        Room engine_room = new Room(250, 75, 500, 175);
        Room cargo_left = new Room(75, 500, 175, 500);
        Room cargo_right = new Room(750, 500, 175, 500);
        Room living_left = new Room(75, 75, 175, 500);
        Room living_right = new Room(750, 75, 175, 500);
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


//        Basic Player Movement Input Handler
        if (!demoMode){
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += playerSpeed;
                if (x + player_w > Gdx.graphics.getWidth()) {
                    x = Gdx.graphics.getWidth() - player_w;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= playerSpeed;
                if (x < 0) {x = 0;}
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                y += playerSpeed;
                if (y + player_h > Gdx.graphics.getHeight()) {
                    y = Gdx.graphics.getHeight() - player_h;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                y -= playerSpeed;
                if (y < 0) {y = 0;}
            }

            ///potential e interact subroutine
            if(Gdx.input.isKeyPressed(Input.Keys.E)){

            }
        }
        else{

        }

        batch.begin();
        //draws map and player
        batch.draw(backgroundTexture,0,0,1000,1000);
        batch.draw(playerTexture, x, y, 25, 25);
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

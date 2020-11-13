package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.eng.auber.AuberGame;
import entities.AuberSystems;


import java.util.ArrayList;

import entities.Enemy;
import entities.Room;
import entities.TeleportPad;


public class GameScreen extends ScreenAdapter {
    public SpriteBatch batch;
    private int playerSpeed = 3;
    AuberGame game;
    boolean demoMode;
    Texture playerTexture,  backgroundTexture;
    float x = 255, y = 255;
    float last_x, last_y;
    int player_h, player_w;
    private boolean justTeleported = false;

    private ArrayList<AuberSystems> System_Auber;
    private ArrayList<Enemy> Enemies;
    private ArrayList<TeleportPad> teleporterList;
    public Array<Room> Rooms;
    public Room current_room;

    //values required for systems functionality - game_assets has labelled map for rooms
    private static final int[] x_sys = {82,210,210,82,82,275,550,700,850,883,752,840,883,500,650,350};
    private static final int[] y_sys = {600,850,125,458,275,77,208,77,77,459,265,503,876,750,875,875};
    private static final int[] room = {6,6,8,8,8,5,5,5,9,9,9,7,7,4,4,4};


    public GameScreen(AuberGame game, boolean demoMode){

        this.demoMode  = demoMode;//is demo mode active
        this.game = game;//passing in game
        this.batch = new SpriteBatch();

        //player texture and area
        this.playerTexture = new Texture("game_assets/player.png");
        this.player_h = 25;
        this.player_w = 25;

        //ArrayList for systems
        this.System_Auber = new ArrayList<AuberSystems>();

        //ArrayList for enemies
        this.Enemies = new ArrayList<Enemy>();

        //ArrayList for teleporters
        this.teleporterList = new ArrayList<TeleportPad>();

        //map texture
        this.backgroundTexture = new Texture("game_assets/station_design.png");

        this.Rooms = new Array<>();

        //creates 16 systems
        for(int i = 0; i<16;i++){
            AuberSystems a = new AuberSystems(x_sys[i],y_sys[i], room[i]);
            System_Auber.add(a);
        }

        //creates 8 enemies
        for(int i = 0; i<8; i++){
            Enemy newEn = new Enemy();
            Enemies.add(newEn);
        }

        //creates the teleporters
        this.teleporterList.add(new TeleportPad(500,600));
        this.teleporterList.add(new TeleportPad(100,100));
        this.teleporterList.add(new TeleportPad(100,800));
        this.teleporterList.add(new TeleportPad(800,800));
        this.teleporterList.add(new TeleportPad(800,100));

        for(int i = 0; i < teleporterList.size()-1; i++){
            teleporterList.get(i).setLinkedTeleporter(teleporterList.get(i+1));
        }
        teleporterList.get(teleporterList.size()-1).setLinkedTeleporter(teleporterList.get(0));

        //creates rooms
        Room outer_corridor = new Room(0, 0, 1000, 1000, "outer", true, 75);
        Room inner_corridor = new Room(250, 250, 500, 500, "inner", true, 75);
        Room brig = new Room(325, 325, 350, 350, "brig");
        Room infirmary = new Room(250, 750, 500, 175, "infirmary");
        Room engine_room = new Room(250, 75, 500, 175, "engine_room");
        Room cargo_left = new Room(75, 500, 175, 500, "cargo_left");
        Room cargo_right = new Room(750, 500, 175, 500, "cargo_right");
        Room living_left = new Room(75, 75, 175, 500, "living_left");
        Room living_right = new Room(750, 75, 175, 500, "living_right");

        outer_corridor.Neighbours.addAll(cargo_left, cargo_right, living_left, living_right);
        inner_corridor.Neighbours.addAll(brig, cargo_left, cargo_right, living_left, living_right);
        brig.Neighbours.add(inner_corridor);
        infirmary.Neighbours.addAll(cargo_left, cargo_right);
        engine_room.Neighbours.add(living_left, living_right);
        cargo_left.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_left);
        cargo_right.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_right);
        living_left.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_left);
        living_right.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_right);

        inner_corridor.currently_occupied = true;
        this.current_room = inner_corridor;

        Rooms.addAll(outer_corridor, inner_corridor, brig, infirmary, engine_room, cargo_left,
                        cargo_right, living_left, living_right);


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //render class for the main game screen
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //to check what room your in
        for (Room Room: Rooms) {
            if (Room.currently_occupied) {
                current_room = Room;
                break;
            }
        }


        //checks for teleport pad
        for (TeleportPad teleporterPad : teleporterList) {
            if (!justTeleported && Gdx.input.isKeyPressed(Input.Keys.E) && teleporterPad.canTeleport(x, y)) {
                ArrayList<Integer> teleportCoords = teleporterPad.teleport();
                x = teleportCoords.get(0) + 12;
                y = teleportCoords.get(1) + 12;
                justTeleported=true;
            }

        }
        for(Enemy en: Enemies){
            if (!en.isCaptured() && Gdx.input.isKeyJustPressed(Input.Keys.E) &&
                    x >= en.x -25 && x <= en.x + 25 && y>= en.y-25 && y <= en.y + 25){
                en.beenCaptured();
                break;
            }
        }


        if(!demoMode){
            last_x = x; last_y = y;
            //if statements for movement
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += playerSpeed;
//                if (x + player_w > current_room.upper_x_collision) {
//                    x = current_room.upper_x_collision - player_w;
//                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= playerSpeed;
//                if (x < current_room.lower_x_collision) {
//                    x = current_room.lower_x_collision;
//                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                y += playerSpeed;
//                if (y + player_h > current_room.upper_y_collision) {
//                    y = current_room.upper_y_collision - player_h;
//                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                y -= playerSpeed;
//                if (y < current_room.lower_y_collision) {
//                    y = current_room.lower_y_collision;
//                }
            }

            if (!(current_room.lower_x_collision <= x && x <= current_room.upper_x_collision - player_w)) {
                x = last_x;
            }
            if (!(current_room.lower_y_collision <= y && y <= current_room.upper_y_collision - player_h)) {
                y = last_y;
            }

            if (current_room.corridor) {
                if (current_room.i_lower_x_collision <= x + player_w && x <= current_room.i_upper_x_collision) {
                    if (current_room.i_lower_y_collision <= y + player_h && y <= current_room.i_upper_y_collision) {
                        x = last_x;
                    }
                }
                if (current_room.i_lower_y_collision <= y + player_h && y <= current_room.i_upper_y_collision) {
                    if (current_room.i_lower_x_collision <= x + player_w && x <= current_room.i_upper_x_collision) {
                        y = last_y;
                    }
                }



//                Rectangle oob = new Rectangle(current_room.x + current_room.outer_width,
//                                                current_room.y + current_room.outer_width,
//                                                current_room.width - (current_room.outer_width * 2),
//                                                current_room.height - (current_room.outer_width * 2));
//                if (oob.contains(x, y)) {
//                    x = last_x; y = last_y;
//                }
            }

        }
        else{
            //demo Mode movement for player
        }
        batch.begin();

        //draws map and player
        batch.draw(backgroundTexture,0,0,1000,1000);
        for(TeleportPad teleporterPad : teleporterList){
            batch.draw(teleporterPad.teleporterSprite, teleporterPad.x,teleporterPad.y);
        }

        //renders systems
        for (AuberSystems curr_sys: System_Auber){
            batch.draw(curr_sys.systemImg,curr_sys.x,curr_sys.y,40,40);
        }

        //renders player
        batch.draw(playerTexture, x, y, 25, 25);
        if (!Gdx.input.isKeyPressed(Input.Keys.E)){
            justTeleported = false;
        }

        for(Enemy en: Enemies){
            batch.draw(en.txtEnemy,en.x,en.y,25,25);
        }


        batch.end();

        // checks if the game has been won
        if(Enemies.get(0).allCaptured(Enemies)){
            game.setScreen(new WinScreen(game));
        }
        //checks to see if escape key pressed to return to main menu
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
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

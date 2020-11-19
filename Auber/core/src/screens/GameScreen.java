package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.eng.auber.AuberGame;
import entities.*;


import java.util.ArrayList;
import java.lang.Math;


public class GameScreen extends ScreenAdapter {
    private static final int playerSpeed = 3;
    AuberGame game;
    private final boolean demoMode;

    //player texture and area
    private final Texture playerTexture = new Texture("game_assets/player.png");
    private final Texture backgroundTexture;
    private final Texture standard_blankTexture;
    private final Texture innerTexture;
    private final Texture outerTexture;
    private float x = 495, y = 495;
    private final int player_h;
    private final int player_w;
    private boolean justTeleported = false;


    private final ArrayList<AuberSystems> System_Auber;
    private final ArrayList<Enemy> Enemies;
    private final ArrayList<TeleportPad> teleporterList;
    public Array<Room> Rooms;
    public Room current_room;
    public String currentDirection = "down";
    public int demoLoop = 0;
    public boolean goneThroughDoorInDemo;
    public boolean returnToBrig = false;

    //values required for systems functionality - game_assets has labelled map for rooms
    private static final int[] x_sys = {82,  210, 210, 82,  82,  275, 550, 700,
                                        850, 883, 752, 840, 883, 500, 650, 350};
    private static final int[] y_sys = {600, 850, 125, 458, 275, 77,  208, 77,
                                        77,  459, 265, 503, 876, 750, 875, 875};
//    private static final int[] room =  {6,   6,   8,   8,   8,   5,   5,   5,
//                                        9,   9,   9,   7,   7,   4,   4,   4};

    private static final String[] room = {"cargo_left", "cargo_left", "living_left",
                                          "living_left", "living_left", "engine_room",
                                          "engine_room", "engine_room", "living_right",
                                          "living_right", "living_right", "cargo_right",
                                          "cargo_right", "infirmary", "infirmary",
                                          "infirmary"};


    public GameScreen(AuberGame game, boolean demoMode){
        this.demoMode  = demoMode; //is demo mode active
        this.game = game; //passing in game

        //lighting textures
        this.standard_blankTexture = new Texture("game_assets/blackout.png");
        this.innerTexture = new Texture("game_assets/innerBlackout.png");
        this.outerTexture = new Texture("game_assets/outerBlackout.png");

        this.player_h = 25;
        this.player_w = 25;

        //ArrayList for systems
        this.System_Auber = new ArrayList<>();

        //ArrayList for enemies
        this.Enemies = new ArrayList<>();

        //ArrayList for teleport
        this.teleporterList = new ArrayList<>();

        //map texture
        this.backgroundTexture = new Texture("game_assets/station_design.png");

        this.Rooms = new Array<>();

        //creates 16 systems
        for (int i = 0; i < 16;i++) {
            AuberSystems a = new AuberSystems(x_sys[i], y_sys[i], room[i]); //, room[i]);
            System_Auber.add(a);
        }

        //creates the teleport pads
        this.teleporterList.add(new TeleportPad(500, 600, "brig"));
        this.teleporterList.add(new TeleportPad(100, 100, "living_left"));
        this.teleporterList.add(new TeleportPad(100, 800, "cargo_left"));
        this.teleporterList.add(new TeleportPad(800, 800, "cargo_right"));
        this.teleporterList.add(new TeleportPad(800, 100, "living_right"));

        for (int i = 0; i < teleporterList.size() - 1; i++) {
            teleporterList.get(i).setLinkedTeleporter(teleporterList.get(i + 1));
        }
        teleporterList.get(teleporterList.size() - 1).setLinkedTeleporter(teleporterList.get(0));

        //creates rooms
        Room outer_corridor = new Room(0,   0,   1000, 1000, "outer", true, 75);
        Room inner_corridor = new Room(250, 250, 500,  500,  "inner", true, 75);
        Room brig           = new Room(325, 325, 350,  350,  "brig");
        Room infirmary      = new Room(250, 750, 500,  175,  "infirmary");
        Room engine_room    = new Room(250, 75,  500,  175,  "engine_room");
        Room cargo_left     = new Room(75,  500, 175,  425,  "cargo_left");
        Room cargo_right    = new Room(750, 500, 175,  425,  "cargo_right");
        Room living_left    = new Room(75,  75,  175,  425,  "living_left");
        Room living_right   = new Room(750, 75,  175,  425,  "living_right");
        //defines relationships between rooms
        outer_corridor.Neighbours.addAll(cargo_left, cargo_right, living_left, living_right);
        inner_corridor.Neighbours.addAll(brig, cargo_left, cargo_right, living_left, living_right);
        brig.Neighbours.add(inner_corridor);
        infirmary.Neighbours.addAll(cargo_left, cargo_right);
        engine_room.Neighbours.add(living_left, living_right);
        cargo_left.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_left);
        cargo_right.Neighbours.add(outer_corridor, inner_corridor, infirmary, living_right);
        living_left.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_left);
        living_right.Neighbours.add(outer_corridor, inner_corridor, engine_room, cargo_right);

        //defines doors
        Array<Door> Doors = new Array<>();
        Doors.add(new Door("h", 55,  750, "outer",        "cargo_left"));
        Doors.add(new Door("h", 55,  215, "outer",        "living_left"));
        Doors.add(new Door("h", 305, 640, "inner",        "brig"));
        Doors.add(new Door("h", 305, 325, "inner",        "brig"));
        Doors.add(new Door("h", 730, 640, "inner",        "cargo_right"));
        Doors.add(new Door("h", 730, 325, "inner",        "living_right"));
        Doors.add(new Door("h", 655, 640, "brig",         "inner"));
        Doors.add(new Door("h", 655, 325, "brig",         "inner"));
        Doors.add(new Door("h", 730, 750, "infirmary",    "cargo_right"));
        Doors.add(new Door("h", 730, 215, "engine_room",  "living_right"));
        Doors.add(new Door("h", 230, 750, "cargo_left",   "infirmary"));
        Doors.add(new Door("h", 230, 640, "cargo_left",   "inner"));
        Doors.add(new Door("h", 905, 750, "cargo_right",  "outer"));
        Doors.add(new Door("v", 215, 480, "living_left",  "cargo_left"));
        Doors.add(new Door("h", 230, 325, "living_left",  "inner"));
        Doors.add(new Door("h", 230, 215, "living_left",  "engine_room"));
        Doors.add(new Door("v", 750, 480, "living_right", "cargo_right"));
        Doors.add(new Door("h", 905, 215, "living_right", "outer"));

        //sets starting room
        this.current_room = brig;

        //fills rooms array with all created rooms
        Rooms.addAll(outer_corridor, inner_corridor, brig, infirmary, engine_room, cargo_left,
                        cargo_right, living_left, living_right);

        //creates 8 enemies
        for (int i = 0; i < 8; i++) {
            Enemy newEn = new Enemy();
            newEn.findRoom(Rooms);
            Enemies.add(newEn);
        }

        //gives rooms their door associations
        for (Room Room: Rooms) {
            for (Door Door: Doors) {
                if (Door.lower_room.equals(Room.identifier) || Door.upper_room.equals(Room.identifier)) {
                    Room.Doors.add(Door);
                }
            }
        }

        for (Room Room: Rooms) {
            for (AuberSystems System: System_Auber) {
                if (System.room.equals(Room.identifier)) {
                    Room.Systems.add(System);
                }
            }
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //render class for the main game screen

        for (Enemy en: Enemies) {
            if (!en.isCaptured() && Gdx.input.isKeyJustPressed(Input.Keys.E) &&
                    x >= en.getX() - 25 && x <= en.getX() + 25 && y >= en.getY() - 25 && y <= en.getY() + 25) {
                en.beenCaptured();
                break;
            }
        }

        if (!demoMode){
            //backs up last position
            float last_x = x;
            float last_y = y;
            //if statements for movement
            Door current_door = null;
            Room new_current_room = null;
            //Door stuff and drawing of black square for room by room illumination
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                //checks for teleport pad
                for (TeleportPad teleporterPad: teleporterList) {
                    if (!justTeleported && teleporterPad.canTeleport(x, y)) {
                        ArrayList<Integer> teleportCoords = teleporterPad.teleport();
                        x = teleportCoords.get(0) + 12;
                        y = teleportCoords.get(1) + 12;
                        justTeleported = true;
                        for (Room Room : Rooms) {
                            if (teleporterPad.linkedTeleporter.id.equals(Room.identifier)) {
                                current_room = Room;
                            }
                        }
                    }
                }
                if (!justTeleported) {
                    for (Door Door: current_room.Doors) {
                        if (Door.playerDetected(x, y)) {
                            current_door = Door;
                            break;
                        }
                    }
                    if (!(current_door == null)) {
                        if (current_door.lower_room.equals(current_room.identifier)) {
                            if (current_door.direction.equals("h")) {
                                x = current_door.upper_x + 10;
                                y = current_door.upper_y;// + 0;
                            } else if (current_door.direction.equals("v")) {
                                x = current_door.upper_x;// + 0;
                                y = current_door.upper_y + 10;
                            }
                            for (Room Room: Rooms) {
                                if (current_door.upper_room.equals(Room.identifier)) {
                                    new_current_room = Room;
                                }
                            }
                        } else if (current_door.upper_room.equals(current_room.identifier)){
                            if (current_door.direction.equals("h")) {
                                x = current_door.upper_x - (10 + player_w);
                                y = current_door.upper_y;//+ 0;
                            } else if (current_door.direction.equals("v")) {
                                x = current_door.upper_x;// + 0;
                                y = current_door.upper_y - (10 + player_h);
                            }
                            for (Room Room: Rooms) {
                                if (current_door.lower_room.equals(Room.identifier)) {
                                    new_current_room = Room;
                                }
                            }
                        }
                    }
                    if (!(new_current_room == null)) {
                        current_room = new_current_room;
                    }
                }
            }
            else {
                //Movement and collision detection
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    x += playerSpeed;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    x -= playerSpeed;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    y += playerSpeed;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    y -= playerSpeed;
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
                }
            }
        }
        else{
            //demo Mode movement for player
            goneThroughDoorInDemo = false;
            float last_x = x;
            float last_y = y;
            Door current_door = null;
            Room new_current_room = null;
            //Door stuff and drawing of black square for room by room illumination
            //checks for teleport pad
            for (TeleportPad teleporterPad : teleporterList) {
                if (!justTeleported && teleporterPad.canTeleport(x, y)) {
                    ArrayList<Integer> teleportCoords = teleporterPad.teleport();
                    x = teleportCoords.get(0) + 12;
                    y = teleportCoords.get(1) + 12;
                    justTeleported = true;
                    for (Room Room : Rooms) {
                        if (teleporterPad.linkedTeleporter.id.equals(Room.identifier)) {
                                current_room = Room;

                        }
                    }
                }
            }
            if (!justTeleported) {
                for (Door Door : current_room.Doors) {
                    if (Door.playerDetected(x, y)) {
                        current_door = Door;
                        break;
                    }
                }
                if (!(current_door == null)) {
                    if (current_door.lower_room.equals(current_room.identifier)) {
                        if (current_door.direction.equals("h")) {
                            x = current_door.upper_x + 10;
                            y = current_door.upper_y;// + 0;
                            goneThroughDoorInDemo = true;
                        } else if (current_door.direction.equals("v")) {
                            x = current_door.upper_x;// + 0;
                            y = current_door.upper_y + 10;
                            goneThroughDoorInDemo = true;
                        }
                        for (Room Room : Rooms) {
                            if (current_door.upper_room.equals(Room.identifier)) {
                                new_current_room = Room;
                            }
                        }
                    } else if (current_door.upper_room.equals(current_room.identifier)) {
                        if (current_door.direction.equals("h")) {
                            x = current_door.upper_x - (10 + player_w);
                            y = current_door.upper_y;//+ 0;
                            goneThroughDoorInDemo = true;
                        } else if (current_door.direction.equals("v")) {
                            x = current_door.upper_x;// + 0;
                            y = current_door.upper_y - (10 + player_h);
                            goneThroughDoorInDemo = true;
                        }
                        for (Room Room : Rooms) {
                            if (current_door.lower_room.equals(Room.identifier)) {
                                new_current_room = Room;
                            }
                        }
                    }
                }
            }
            //Changes the direction of the player when it hits a wall
            if(!goneThroughDoorInDemo) {
                if(!returnToBrig) {
                    switch (currentDirection) {
                        case "down":
                            y -= playerSpeed;
                            break;
                        case "up":
                            y += playerSpeed;
                            break;
                        case "left":
                            x -= playerSpeed;
                            break;
                        case "right":
                            x += playerSpeed;
                            break;
                    }
                } else {
                    //Moves the player back to the brig by a set path
                    demoLoop += 1;
                    if(demoLoop < 240){
                        x -= playerSpeed;
                    } else if(demoLoop < 275){
                        y += playerSpeed;
                    } else if(demoLoop < 390){
                        x += playerSpeed;
                    } else if(demoLoop == 390){
                        returnToBrig = false;
                        demoLoop = 0;
                        int num = (int) (Math.random()*4);
                        String[] directions = {"up","down","left","right"};
                        currentDirection = directions[num];
                    }
                }
                //detects collisions with walls and changes player direction
                if (current_room.lower_y_collision > y || y < 0) {
                    y = last_y;
                    currentDirection = "right";
                }
                if (current_room.upper_y_collision < y + player_h|| y > 1000) {
                    y = last_y;
                    currentDirection = "left";
                }
                if (current_room.lower_x_collision > x || x < 0) {
                    x = last_x;
                    currentDirection = "down";
                }
                if (current_room.upper_x_collision < x + player_w || x > 1000) {
                    x = last_x;
                    currentDirection = "up";
                }
            }
            if (!(new_current_room == null)) {
                current_room = new_current_room;
            }
            //detects player is in outer corridor and starts the process of
            //returning it to the brig
            if(y > 210 && y < 220 && x > 965 && x < 975 ) {
                returnToBrig = true;
                demoLoop = 0;
            }
        }
        this.game.batch.begin();
        this.game.batch.draw(backgroundTexture, 0, 0, 1000, 1000);//draw map
        //draws player
        for (TeleportPad teleportPad: teleporterList) {
            this.game.batch.draw(teleportPad.teleporterSprite, teleportPad.x,teleportPad.y);
        }
        //renders systems
        for (AuberSystems curr_sys: System_Auber) {
            int[] xy = curr_sys.getCoord();
            this.game.batch.draw(curr_sys.systemImg, xy[0], xy[1], 40, 40);
        }
        //renders player
        this.game.batch.draw(playerTexture, x, y, 25, 25);
        if (!Gdx.input.isKeyPressed(Input.Keys.E)) {
            justTeleported = false;
        }
        for (Enemy en: Enemies) {
            this.game.batch.draw(en.getTexture(), en.getX(), en.getY(), 25, 25);
        }
        //Illumination
        for (Room Room : Rooms) {
            if (current_room != Room) {
                if(!Room.identifier.equals("inner") && !Room.identifier.equals("outer")){
                    this.game.batch.draw(this.standard_blankTexture, Room.getX(),Room.getY(), Room.width, Room.height);
                }
                else if(Room.identifier.equals("inner")){
                    this.game.batch.draw(this.innerTexture, 0,0, 1000, 1000);
                }
                else{ //if room is outer
                    this.game.batch.draw(this.outerTexture, Room.getX(),Room.getY(), Room.width, Room.height);
                }
            }
        }
        this.game.batch.end();
        // checks if the game has been won
        if (Enemies.get(0).allCaptured(Enemies)) {
            game.setScreen(new WinScreen(game));
        }
        //checks to see if escape key pressed to return to main menu
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
        }
        else if (System_Auber.get(0).hasLost(System_Auber)) {
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        playerTexture.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        this.game.batch.dispose();

    }
}

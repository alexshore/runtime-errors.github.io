package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;

public class MainMenu implements Screen {
    private static final int button_width = Math.round(Gdx.graphics.getWidth()/6), button_height = Math.round(Gdx.graphics.getHeight()/15);
    AuberGame game;

    Texture exit_start, exit_end, play_start, play_end,demo_start,demo_end, control_start, control_end,title;

    public MainMenu (AuberGame game){
        this.game = game;
        //Loads textures for buttons
        title = new Texture("menu_assets/Title.png");
        play_start = new Texture("menu_assets/play_button_highlight.png");
        play_end = new Texture("menu_assets/play_button.png");
        demo_start = new Texture("menu_assets/demo_button_highlight.png");
        demo_end = new Texture("menu_assets/demo_button.png");
        control_start = new Texture("menu_assets/control_button_highlight.png");
        control_end = new Texture("menu_assets/control_button.png");
        exit_start = new Texture("menu_assets/exit_button_highlight.png");
        exit_end = new Texture("menu_assets/exit_button.png");
        //Button textures from https://pngtree.com/freepng/buttons-games-button-illustration_5512619.html
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        int screenHeight = Gdx.graphics.getHeight();
        int play_x =500 - (button_width/2);
        int play_y = 500;
        int control_x = 500 - button_width/2;
        int control_y = 350;
        int demo_x = 500 - button_width/2;
        int demo_y = 425;
        int exit_x = 500 - button_width/2;
        int exit_y = 275;
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        // Below draws title and buttons as window opens
        game.batch.draw(title, 500 - Math.round(title.getWidth()/2), 1000 - title.getHeight());

        //Action for when player hovers over play
        if(Gdx.input.getX() < play_x + button_width && Gdx.input.getX() > play_x && screenHeight - Gdx.input.getY() < play_y + button_height && screenHeight -  Gdx.input.getY() > play_y){
            game.batch.draw(play_start, play_x, play_y, button_width,button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){ //detects click
                game.setScreen(new GameScreen(game, false));
            }
        }
        else{
            game.batch.draw(play_end, play_x, play_y, button_width,button_height);
        }


        //Action for when player hovers over demo
        if(Gdx.input.getX() < demo_x + button_width && Gdx.input.getX() > demo_x && screenHeight - Gdx.input.getY() < demo_y + button_height && screenHeight -  Gdx.input.getY() > demo_y){
            game.batch.draw(demo_start, demo_x, demo_y, button_width,button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new GameScreen(game, true));
                //passes in game and that demo mode is on
            }
        }
        else{
            game.batch.draw(demo_end, demo_x, demo_y, button_width,button_height);
        }


        //Action for when player hovers over control
        if(Gdx.input.getX() < control_x + button_width && Gdx.input.getX() > control_x && screenHeight - Gdx.input.getY() < control_y + button_height && screenHeight -  Gdx.input.getY() > control_y){
            game.batch.draw(control_start, control_x, control_y, button_width,button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new ControlScreen(game));
            }
        }
        else{
            game.batch.draw(control_end, control_x, control_y, button_width,button_height);
        }


        //Actions for hover over exit
        if(Gdx.input.getX() < exit_x + button_width && Gdx.input.getX() > exit_x && screenHeight - Gdx.input.getY() < exit_y + button_height && screenHeight -  Gdx.input.getY() > exit_y){
            game.batch.draw(exit_start, exit_x, exit_y, button_width,button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                Gdx.app.exit(); //exit program
            }
        }
        else{
            game.batch.draw(exit_end, exit_x, exit_y, button_width,button_height);
        }

        game.batch.end();
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        title.dispose();
        play_start.dispose();
        play_end.dispose();
        control_start.dispose();
        control_end.dispose();
        demo_start.dispose();
        demo_end.dispose();
        exit_start.dispose();
        exit_end.dispose();
    }
}

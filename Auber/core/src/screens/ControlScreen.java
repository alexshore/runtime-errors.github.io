package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;

public class ControlScreen implements Screen {
    private static final int button_width = Math.round(Gdx.graphics.getWidth()/6), button_height = Math.round(Gdx.graphics.getHeight()/15);
    Texture instructions, back_to_menu, back_to_menu_highlight;
    AuberGame game;

    public ControlScreen (AuberGame game){
        //loads textures
        instructions = new Texture("menu_assets/Control_Screen.png" );
        back_to_menu = new Texture("menu_assets/exit_button.png");
        back_to_menu_highlight = new Texture("menu_assets/exit_button_highlight.png");
        this.game  = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int screenHeight = Gdx.graphics.getHeight();;
        int screenWidth = Gdx.graphics.getWidth();
        instructions.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.batch.begin();
        //draws background and buttons
        game.batch.draw(instructions, 0, 0, screenWidth, screenHeight);
        //goes back to main menu if escape pressed
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
        }
        //draws exit button, highlights it, and directs back to main menu
        if(Gdx.input.getX() < 5 + button_width && Gdx.input.getX() > 5 && screenHeight - Gdx.input.getY() < 5 + button_height && screenHeight -  Gdx.input.getY() > 5){
            game.batch.draw(back_to_menu_highlight, 5, 5, button_width,button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){ //detects click
                game.setScreen(new MainMenu(game));
            }
        }
        else{
            game.batch.draw(back_to_menu, 5, 5, button_width,button_height);
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

    }

    @Override
    public void dispose() {
        instructions.dispose();
    }
}

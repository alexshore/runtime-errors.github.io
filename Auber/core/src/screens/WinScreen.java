package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;


public class WinScreen implements Screen {


    private static final int button_width = Math.round(Gdx.graphics.getWidth()>>2), button_height = Math.round(Gdx.graphics.getHeight()>>4);
    private final AuberGame game;
    private final Texture win, exit_start,exit_end, background;
    private final int exit_x,exit_y, screenHeight;

    public WinScreen(AuberGame game){
        /*
        Win Screen for when player has arrested all enemies
         */
        this.game = game;
        this.win = new Texture("win_assets/winner.png");
        this.exit_start = new Texture("menu_assets/exit_button_highlight.png");
        this.exit_end = new Texture("menu_assets/exit_button.png");
        this.background = new Texture("game_assets/station_design.png");
        this.exit_x = 500 - button_width/2;
        this.exit_y = 275;
        this.screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(this.background,0,0,1000,1000);
        game.batch.draw(this.win, (500 - (this.win.getWidth()>>1)), 700);

        if(Gdx.input.getX() < this.exit_x + button_width && Gdx.input.getX() > this.exit_x && this.screenHeight - Gdx.input.getY() < this.exit_y + button_height && this.screenHeight -  Gdx.input.getY() > this.exit_y){
            game.batch.draw(this.exit_start, this.exit_x, this.exit_y, button_width, button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new MainMenu(game));
            }
        }
        else{
            game.batch.draw(this.exit_end, this.exit_x, this.exit_y, button_width, button_height);
        }
        game.batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
        this.win.dispose();

    }
}

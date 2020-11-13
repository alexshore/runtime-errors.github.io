package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;
import org.w3c.dom.Text;


public class WinScreen implements Screen {
    private static final int button_width = Math.round(Gdx.graphics.getWidth()/6), button_height = Math.round(Gdx.graphics.getHeight()/15);
    AuberGame game;
    Texture win, exit_start,exit_end;
    int exit_x = 500 - button_width/2;
    int exit_y = 275;
    int screenHeight = Gdx.graphics.getHeight();

    public WinScreen(AuberGame game){
        this.game = game;
        this.win = new Texture("win_assets/winner.png");
        this.exit_start = new Texture("menu_assets/exit_button_highlight.png");
        this.exit_end = new Texture("menu_assets/exit_button.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(this.win,500 - this.win.getWidth()/2,700);

        if(Gdx.input.getX() < this.exit_x + this.button_width && Gdx.input.getX() > this.exit_x && this.screenHeight - Gdx.input.getY() < this.exit_y + this.button_height && this.screenHeight -  Gdx.input.getY() > this.exit_y){
            game.batch.draw(this.exit_start, this.exit_x, this.exit_y, this.button_width,this.button_height);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new MainMenu(game));
            }
        }
        else{
            game.batch.draw(this.exit_end, this.exit_x, this.exit_y, this.button_width, this.button_height);
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

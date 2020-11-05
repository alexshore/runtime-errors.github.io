package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;

public class ControlScreen implements Screen {
    Texture instructions;
    AuberGame game;

    public ControlScreen (AuberGame game){
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
        instructions = new Texture("menu_assets/Control_Screen.png" );
        instructions.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.batch.begin();
        game.batch.draw(instructions, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
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

    }
}

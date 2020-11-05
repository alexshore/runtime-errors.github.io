package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.eng.auber.AuberGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import input.processors.*;

public class GameScreen extends ScreenAdapter {
    AuberGame game;
    boolean demoMode;
    TextureAtlas textureAtlas;


    public GameScreen(AuberGame game, boolean demoMode){
        this.demoMode  = demoMode;
        this.game = game;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(
                new GameInputProcessor()
        );
    }
    @Override
    public void render(float delta) {
//        textureAtlas  = new TextureAtlas("game_assets/P_temp.png");
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
        }
        GameKeys.update();
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

        textureAtlas.dispose();
    }
}

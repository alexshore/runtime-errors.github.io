package screens;

import com.badlogic.gdx.Screen;
import com.eng.auber.AuberGame;

public class ControlScreen implements Screen {
    AuberGame game;

    public ControlScreen (AuberGame game){
        this.game  = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
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

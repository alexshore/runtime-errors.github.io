package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;

public class GameScreen implements Screen {
    Texture img;
    public static final float player_speed = 120;
    AuberGame game;

    public GameScreen(AuberGame game){
        this.game = game;//allows us to access variables in AuberGame
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        img = new Texture("game_assets/runtime_errors.png");
        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.batch.begin();
        game.batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth()/2, Gdx.graphics.getHeight()/2 - img.getHeight()/2);
        //Centres the image
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

        img.dispose();
    }
}

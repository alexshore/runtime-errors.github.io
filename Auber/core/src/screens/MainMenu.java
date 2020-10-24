package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.eng.auber.AuberGame;
import org.w3c.dom.Text;

public class MainMenu implements Screen {
    private static final int button_width = 300, button_height = 150;
    AuberGame game;

    Texture exit_start, exit_end, play_start, play_end,demo_start,demo_end, control_start, control_end;

    public MainMenu (AuberGame game){
        this.game = game;
        exit_start = new Texture("menu_assets/exit_button_highlight.png");
        exit_end = new Texture("menu_assets/exit_button.png");
        play_start = new Texture("menu_assets/play_button_highlight.png");
        play_end = new Texture("menu_assets/play_button.png");
        demo_start = new Texture("menu_assets/demo_button_highlight.png");
        demo_end = new Texture("menu_assets/demo_button.png");
        control_start = new Texture("menu_assets/control_button_highlight.png");
        control_end = new Texture("menu_assets/control_button.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(exit_start, Gdx.graphics.getWidth()/2 - exit_start.getWidth()/2, Gdx.graphics.getHeight()/2 - exit_start.getHeight()/2, button_width,button_height);

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

package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eng.auber.AuberGame;

public class GameScreen extends ScreenAdapter {
    public SpriteBatch batch;
    private int playerSpeed = 3;
    AuberGame game;
    boolean demoMode;
    Texture playerTexture,  backgroundTexture;
    float x, y;
    int player_h, player_w;

    public GameScreen(AuberGame game, boolean demoMode){
        this.demoMode  = demoMode;
        this.game = game;
        this.batch = new SpriteBatch();
        this.playerTexture = new Texture("game_assets/P_temp.png");
        this.player_h = 30;
        this.player_w = 30;
        this.backgroundTexture = new Texture("game_assets/station_design.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//        Basic Player Movement Input Handler
        if (!demoMode){
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += playerSpeed;
                if (x + player_w > Gdx.graphics.getWidth()) {
                    x = Gdx.graphics.getWidth() - player_w;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= playerSpeed;
                if (x < 0) {x = 0;}
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                y += playerSpeed;
                if (y + player_h > Gdx.graphics.getHeight()) {
                    y = Gdx.graphics.getHeight() - player_h;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                y -= playerSpeed;
                if (y < 0) {y = 0;}
            }
        }
        else{

        }

        batch.begin();
        //draws map and player
        batch.draw(backgroundTexture,0,0,1000,1000);
        batch.draw(playerTexture, x, y, 30, 30);
        batch.end();
        //checks to see if escape key pressed to return to main menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
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
        playerTexture.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        batch.dispose();
    }
}

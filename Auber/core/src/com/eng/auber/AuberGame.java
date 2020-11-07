package com.eng.auber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import input.processors.GameInputProcessor;
import input.processors.GameKeys;
import screens.MainMenu;

public class AuberGame extends Game {
	public SpriteBatch batch;
	Texture img;
	float x, y;
	int img_h, img_w;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));//displays game screen
		img = new Texture("game_assets/P_temp.png");
		img_h = img.getHeight();
		img_w = img.getWidth();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Keys.D)) {
			x++;
			if (x + img_w > Gdx.graphics.getWidth()) {
				x = Gdx.graphics.getWidth() - img_w;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			x--;
			if (x < 0) {x = 0;}
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			y++;
			if (y + img_h > Gdx.graphics.getHeight()) {
				y = Gdx.graphics.getHeight() - img_h;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			y--;
			if (y < 0) {y = 0;}
		}

		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

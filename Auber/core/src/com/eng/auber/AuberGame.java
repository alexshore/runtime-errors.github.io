package com.eng.auber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.GameScreen;

public class AuberGame extends Game {
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));//displays game screen
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

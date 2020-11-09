package com.eng.auber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< Updated upstream
import com.badlogic.gdx.Input.Keys;
=======
>>>>>>> Stashed changes
import screens.MainMenu;

public class AuberGame extends Game {
	public SpriteBatch batch;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));//displays game screen

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

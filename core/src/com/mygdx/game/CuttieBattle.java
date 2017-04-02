package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Date;

public class CuttieBattle extends ApplicationAdapter {
	static SpriteBatch batch;
	Texture img;
	static Character p1,p2;
	private Date startCountTime , currentCountTime;
	State state;
	int gameState =0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		state = new State();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		switch (state.getCurrentState()){
			case 0:
				// Home
				batch.begin();
				//System.out.println("State 0");
				state.input();
				state.drawMap();
				batch.end();
				break;
			case 1:
				// Select Character menu
				batch.begin();
				//System.out.println("State 1");
				state.input();
				state.drawMap();
				System.out.println("P1 = " +state.getP1Select()+" : P2 = "+state.getP2Select());
				batch.end();
				startCountTime = new Date();
				break;
			case 2:
				// gameplay
				batch.begin();
				//System.out.println("State 2");
				state.drawMap();
				boolean playing = true;
				switch (gameState){
					case 0:
						// count time 3 sec and Input Action
						//System.out.println("gamestate 0");
						state.input();
						currentCountTime = new Date();
						int second = currentCountTime.getSeconds() - startCountTime.getSeconds();
						switch (second){
							case 0:
								System.out.println("Second 1");
								break;
							case 1:
								System.out.println("Second 2");
								break;
							case 2:
								System.out.println("Second 3");
								break;
							default:
								gameState=1;
						}
						break;
					case 1:
						System.out.println("gamestate 1");
						p1.setDestinationPos(500,300);
						p2.setDestinationPos(500,300);
						p1.goRight();
						if(p2.goLeft() == true){
							gameState++;
						}

						break;
					default:playing = false;
				}
				p1.draw();
				p2.draw();
				batch.end();
				break;
			case 3:
				// end game
				state.gameEnd();
				break;
		}
	}


	/*

	public void setCharacter(){
		switch (state.getp1Select()){
			case 0:
				p1 = new Grizzly(100,200);
				break;
			case 1:
				p1 = new Panda(100,200);
				break;
			case 2:
				91 = new IceBear(100,200);
				break;
		}
		switch (state.getp2Select()){
			case 0:
				p2 = new Grizzly(800,200);
				break;
			case 1:
				p2 = new Panda(800,200);
				break;
			case 2:
				p2 = new IceBear(800,200);
				break;
		}
		state.setCurrentState(3);
	}
	 */

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

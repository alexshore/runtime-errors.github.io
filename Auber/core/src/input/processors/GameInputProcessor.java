package input.processors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter{

    public boolean keyDown(int k){
        /// Takes input of the key and returns true on value inputted
        if ( k == Keys.W){
            GameKeys.setKey(GameKeys.W, true);
        }
        else if ( k == Keys.A){
            GameKeys.setKey(GameKeys.A, true);
        }
        else if ( k == Keys.S){
            GameKeys.setKey(GameKeys.S, true);
        }
        else if ( k == Keys.D){
            GameKeys.setKey(GameKeys.D, true);
        }
        else if ( k == Keys.E){
            GameKeys.setKey(GameKeys.E, true);
        }
        else if ( k == Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, true);
        }
        return true;
    }
    public boolean keyUp(int k){
        /// Takes input of the key and returns true on value inputted
        if ( k == Keys.W){
            GameKeys.setKey(GameKeys.W, false);
        }
        else if ( k == Keys.A){
            GameKeys.setKey(GameKeys.A, false);
        }
        else if ( k == Keys.S){
            GameKeys.setKey(GameKeys.S, false);
        }
        else if ( k == Keys.D){
            GameKeys.setKey(GameKeys.D, false);
        }
        else if ( k == Keys.E){
            GameKeys.setKey(GameKeys.E, false);
        }
        else if ( k == Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, false);
        }
        return true;
    }
}

package input.processors;

public class GameKeys {

    ///Defines variables needed to limit keys that can be pressed and when they can and can't be pressed
    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 6;
    public static final int W = 0;
    public static final int A = 1;
    public static final int S = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int ESCAPE = 5;


    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update(){
        for( int i  = 0; i <NUM_KEYS; i++){
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int k, boolean b){
        keys[k] = b;
    }

    public static boolean isDown(int k){
        //checks if key is pressed
        return keys[k];
    }

    public static  boolean isPressed(int k){
        /// checks if key wasn't pressed beforehand before returning true
        return keys[k] && !pkeys[k];

    }
}





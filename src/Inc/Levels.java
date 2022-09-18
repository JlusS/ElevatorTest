package Inc;

import java.util.List;

public class Levels {
    private static boolean button;
    private static int currLevel;

    public static void nextLevel(int maxLevel){
        if(currLevel != maxLevel)
            setCurrLevel(currLevel+1);
    }

    public static void prevLevel(int minLevel){
        if(currLevel != minLevel)
            setCurrLevel(currLevel-1);
    }


    public static int getCurrLevel() {
        return currLevel;
    }

    public static void setCurrLevel(int currLevel) {
        Levels.currLevel = currLevel;
    }

    public static boolean isButton() {
        return button;
    }

    public static void setButton(boolean button) {
        Levels.button = button;
    }
}

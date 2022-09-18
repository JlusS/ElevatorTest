package Inc;

import java.util.List;

public class Elevator {

    private static int freeSpace = 5;
    private static boolean up;
    private static final int maxInElevator = 5;


    public static int getFreeSpace() {
        return freeSpace;
    }

    public static void setFreeSpace(int freeSpace) {
        Elevator.freeSpace = freeSpace;
    }
    public static int getMaxInElevator() {
        return maxInElevator;
    }

    public static boolean isUp() {
        return up;
    }

    public static void setUp(boolean up) {
        Elevator.up = up;
    }

}

package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    public static int rand(int min, int max){
        Random random = new Random();
        return random.nextInt(min , max+1);
    }
    public static List<Integer> passengersGenerator(int min,int max){
        List<Integer> passengersOnLevel = new ArrayList<>();
        int passQuant = rand(0,10);
        for(int i = 0; i < passQuant; i++){
            passengersOnLevel.add(rand(min,max));
        }
        return passengersOnLevel;
    }
}

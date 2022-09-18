import Generator.Generator;
import Inc.Elevator;
import Inc.Levels;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private static final int maxLevel = 20;
    private static final int minLevel = 5;
    private static List<Integer>[] peopleOnLevel;
    private static List<Integer> peopleInElevator;
    private static int floorQuant;
    private static int counter;


    public static void main(String[] args) {
        peopleInElevator = new ArrayList<Integer>();
        floorQuant = Generator.rand(minLevel,maxLevel);
        setStart();
        levelButton();
        Elevator.setUp(true);
        enterToElevator();
        show();
        logic();
    }
    public static void setStart(){
        peopleOnLevel = new ArrayList[floorQuant];
        int setIndex;
        for(int i = floorQuant; i > 0; i--){
            peopleOnLevel[i-1] = Generator.passengersGenerator(1,floorQuant);
            while (peopleOnLevel[i-1].contains(i)){
                setIndex = peopleOnLevel[i-1].indexOf(i);
                peopleOnLevel[i-1].remove(setIndex);
            }
        }
    }

    public static void enterToElevator(){
        int peopleEntered = 0;
        for(int i = 0; i < Elevator.getMaxInElevator() && peopleOnLevel[Levels.getCurrLevel()].size() > i ;i++){
            Elevator.setFreeSpace(Elevator.getMaxInElevator() - peopleInElevator.size());
            if(Levels.isButton() && Elevator.isUp() && Elevator.getFreeSpace() > 0){
                if(peopleOnLevel[Levels.getCurrLevel()].get(i)>Levels.getCurrLevel()+1){
                    peopleInElevator.add((Integer) peopleOnLevel[Levels.getCurrLevel()].get(i));
                    peopleEntered++;
                }
            } else if (!Levels.isButton() && !Elevator.isUp() && Elevator.getFreeSpace() > 0) {
                if(peopleOnLevel[Levels.getCurrLevel()].get(i)<Levels.getCurrLevel()+1){
                    peopleInElevator.add((Integer) peopleOnLevel[Levels.getCurrLevel()].get(i));
                    peopleEntered++;
                }
            } else if (peopleInElevator.isEmpty() && peopleOnLevel[Levels.getCurrLevel()].isEmpty()) {
                Levels.nextLevel(floorQuant);
            }
        }
        while(peopleEntered>0){
            peopleEntered--;
            peopleOnLevel[Levels.getCurrLevel()].remove(peopleEntered);
        }
    }

    public static void leaveAtLevel(){
        int indexOut;
        if(Levels.getCurrLevel() == 0){
            while(peopleInElevator.contains(Levels.getCurrLevel()+1)){
                indexOut = peopleInElevator.indexOf(Levels.getCurrLevel()+1);
                peopleInElevator.remove(indexOut);
            }
        } else {
            while(peopleInElevator.contains(Levels.getCurrLevel()+1)){
                indexOut = peopleInElevator.indexOf(Levels.getCurrLevel()+1);
                peopleInElevator.remove(indexOut);
                peopleOnLevel[Levels.getCurrLevel()].add(Generator.rand(1,floorQuant));
                while(peopleOnLevel[Levels.getCurrLevel()].contains(Levels.getCurrLevel()+1)){
                    indexOut = peopleOnLevel[Levels.getCurrLevel()].indexOf(Levels.getCurrLevel()+1);
                    peopleOnLevel[Levels.getCurrLevel()].remove(indexOut);
                }
            }
        }

    }

    public static void logic(){
        while(!emptyLevels() || !peopleInElevator.isEmpty()){
            System.out.println("------ Step" + (++counter) + "------");
            if(Elevator.isUp()){
                if(Levels.getCurrLevel() == floorQuant-1) {
                    Elevator.setUp(false);
                    Levels.setButton(false);
                    enterToElevator();
                }else{
                    goingUp();
                    show();
                }
            }else{
                if(Levels.getCurrLevel() == 0) {
                    Elevator.setUp(true);
                    Levels.setButton(true);
                    enterToElevator();
                }else{
                    show();
                    goingDown();
                }
            }
        }
        System.out.println("------ Elevator is waiting --------");
        show();
    }
    public static void levelButton(){
        for(int i = 0; i < peopleOnLevel[Levels.getCurrLevel()].size();i++){
            Levels.setButton(peopleOnLevel[Levels.getCurrLevel()].get(i) > Levels.getCurrLevel() + 1);
        }
    }
    public static void goingUp(){
        if(Levels.isButton()){
            Levels.nextLevel(floorQuant-1);
            leaveAtLevel();
            enterToElevator();
        }
    }

    public static void goingDown(){
        if(!Levels.isButton()){
            Levels.prevLevel(0);
            leaveAtLevel();
            enterToElevator();
        }
    }

    public static boolean emptyLevels(){
        boolean levelsIsEmpty = true;
        for(int i = floorQuant; i > 0; i--){
            if(!peopleOnLevel[i-1].isEmpty()){
                levelsIsEmpty = false;
                break;
            }
            
        }
        return levelsIsEmpty;
    }
    public static void show(){
        for(int i = floorQuant; i > 0; i--){
            if(i == Levels.getCurrLevel()+1){
                if(Elevator.isUp()){
                    System.out.println(i + " | " + peopleOnLevel[i-1]+ " | " + "^  ^" + peopleInElevator);
                }else{
                    System.out.println(i + " | " + peopleOnLevel[i-1]+ " | " + "V  V" + peopleInElevator);
                }
            }else{
                System.out.println(i + " | " + peopleOnLevel[i-1]+ " | " + "    ");
            }
        }
    }
}

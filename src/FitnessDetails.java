import java.io.*;
import java.util.*;

public class FitnessDetails {

    float seed=10;
    float[][] map;

    public FitnessDetails(int containerNum, int hostNum){
        map=new float[containerNum][hostNum];
        populateAuto(containerNum, hostNum);
        populateManual(containerNum, hostNum);
    }

    //option for user to input mapping, or skip
    public void populateManual(int containerNum, int hostNum){
        Scanner input=new Scanner(System.in);
        float val;
        int i,j,r;
        manual:
        for(i=0; i<containerNum; i++){
            for(j=0; j<hostNum; j++) {
                cls();
                System.out.println("Fitness mapping:");
                for(r=0; r<containerNum; r++){
                    System.out.println(Arrays.toString(map[r]));
                }
                while (true){
                    System.out.print("\nResource units used by host " + j + " to run container " + i + " [0=skip]: ");
                    try{
                        val = input.nextFloat();
                        if(val==0){
                            break manual;
                        }
                        map[i][j] = val;
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Please input a number.");
                    }
                }
            }
        }
    }

    //inverted pyramid shaped mapping
    private void populateAuto(int containerNum, int hostNum){
        float val0, val1, val2, val3;
        for(int i=0; i<containerNum; i++){
            for(int j=0; j<hostNum; j++) {
                val0=i-(containerNum/2);
                val1=j-(hostNum/2);
                val2=Math.abs(val0)+Math.abs(val1);
                val3=val2+seed;
                map[i][j]=val3;
            }
        }
    }

    //evaluation process of fitness function
    public float eval(Vector pos){
        int i,j;
        float result=0;
        int[] values=pos.get();
        for(i=0; i<values.length; i++){
            j=values[i];
            result += map[i][j];
        }
        return result;
    }

    public void cls(){
        try{
            if(System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch(IOException | InterruptedException ex){}
    }

}

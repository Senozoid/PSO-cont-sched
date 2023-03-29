import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.*;

public class Optimizer {

    static Scanner input;

    public static void main(String[] args){
        int i=0;
        input = new Scanner(System.in);
        System.out.println("\nThis program attempts to use PSO for container scheduling\n");
        int epochs = takeInt("Enter the number of epochs: ");
        int[] space = takeSpace();
        FitnessDetails fitness = new FitnessDetails(space[1],space[2]);
        Swarm hive = new Swarm(space,fitness);
        System.out.print("\n");
        try {
            i=search(hive,epochs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\rEpoch "+(i-1)+" : ");
        declare(hive);
    }

    private static int search(Swarm hive, int epochs) throws InterruptedException {
        int i;
        float[] parameters = takeParameters();
        for (i=0; i<epochs; i++){
            TimeUnit.MILLISECONDS.sleep(3);
            System.out.print("\rEpoch "+i+" : ");
            if(hive.update(parameters)){
                declare(hive);
            }
            else{
                System.out.print("No change");
            }
            logParticles(hive,i);
        }
        return i;
    }

    private static void declare(Swarm hive){
        System.out.print("          \n");
        System.out.println("\tGlobal best position = "+hive.getBestPos().toString());
        System.out.println("\tGlobal best evaluation = "+hive.getBestEval());
    }

    private static int[] takeSpace(){
        int[] space = new int[3];
        space[0]=takeInt("Enter the number of particles: ");
        space[1]=takeInt("Enter the number of containers: ");
        space[2]=takeInt("Enter the number of hosts: ");
        return space;
    }

    private static float[] takeParameters(){
        float[] parameters = new float[4];
        parameters[0]=takeFloat("Enter inertia: ",1);
        parameters[1]=takeFloat("Enter cognitive component: ",Float.POSITIVE_INFINITY);
        parameters[2]=takeFloat("Enter social component: ",Float.POSITIVE_INFINITY);
        parameters[3]=takeFloat("Enter limit of randomness: ",Float.POSITIVE_INFINITY);
        return parameters;
    }

    private static int takeInt(String msg) {
        int num;
        while (true) {
            System.out.print(msg);
            try{
                num = input.nextInt();
                if (num <= 0) {
                    System.out.println("This input must be a positive integer.");
                } else {
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }
        return num;
    }

    private static float takeFloat(String msg, float limit) {
        float num;
        while (true) {
            System.out.print(msg);
            try{
                num = input.nextFloat();
                if (num<=0 || num>=limit) {
                    System.out.println("Number must be positive and less than "+limit+".");
                } else {
                    break;
                }
            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }
        return num;
    }

    private static void logParticles(Swarm hive, int i){
        PrintStream console=System.out;
        System.setOut(redirect(1));
        System.out.println("EPOCH "+i+" :");
        System.out.println("Positions");
        hive.printPositions();
        System.out.println("Velocities");
        hive.printVelocities();
        System.setOut(console);
    }

    public static PrintStream redirect(int type) {
        boolean status=false;
        String filename = switch(type){
            case 0 -> "mapping.txt";
            case 1 -> "log.txt";
            default -> "undefined.txt";
        };
        File log=new File(filename);
        PrintStream logger=null;
        try {
            logger=new PrintStream(log);
            status=true;
        } catch (FileNotFoundException e) {
            System.err.println("Log file cannot be written.");
        } catch (SecurityException e) {
            System.err.println("Log write access was denied.");
        }
        if(!status) System.exit(-1);
        return logger;
    }

    public static void cls(){
        try{
            if(System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch(IOException | InterruptedException ex){}
    }

}

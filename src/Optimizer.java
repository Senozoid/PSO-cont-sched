import java.util.*;
import java.util.concurrent.*;

public class Optimizer {

    static Scanner input;

    public static void main(String[] args) throws InterruptedException {
        int i;
        input = new Scanner(System.in);
        System.out.println("\nThis program attempts to use PSO for container scheduling\n");
        int epochs = takeInt("Enter the number of epochs: ");
        int[] space = takeSpace();
        float[] parameters = takeParameters();
        FitnessDetails fitness = new FitnessDetails(space[1],space[2]);
        Swarm hive = new Swarm(space,fitness);
        System.out.print("\n");
        for (i=0; i<epochs; i++){
            TimeUnit.MILLISECONDS.sleep(20);
            System.out.print("\rEpoch "+i+" : ");
            if(hive.update(parameters)){
                declare(hive);
            }
            else{
                System.out.print("No change");
            }
        }
        System.out.print("\rEpoch "+(i-1)+" : ");
        declare(hive);
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
                    System.out.println("Number must be positive.");
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

}

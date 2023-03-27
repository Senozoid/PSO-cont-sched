import java.util.*;

public class FitnessDetails {
    boolean real;
    float[][] map;
    int[] coefficients;

    //constructor that creates fitness function from user input
    public FitnessDetails(int containerNum, int hostNum){
        Scanner input=new Scanner(System.in);
        map=new float[containerNum][hostNum];
        System.out.print("\n");
        for(int i=0; i<containerNum; i++){
            for(int j=0; j<hostNum; j++) {
                inner:
                while (true){
                    System.out.print("\nResource units used by host " + j + " to run container " + i + " : ");
                    try{
                        map[i][j] = input.nextFloat();
                        break inner;
                    }catch(InputMismatchException e){}
                }
            }
        }
        real=true;
    }

    //constructor that creates a random, linear fitness function
    public FitnessDetails(int dimension){
        Random simulator=new Random();
        int factor=1;
        coefficients=new int[dimension];
        for(int i=0; i<dimension; i++){
            while(factor>10||factor<-10) factor=simulator.nextInt();
            coefficients[i]=factor;
        }
        real=false;
    }

    //redirecting method
    public float eval(Vector pos){
        float result;
        if(real) result=trueEval(pos);
        else result=simEval(pos);
        return result;
    }

    //evaluation process if fitness function was manually created
    private float trueEval(Vector pos){
        int i,j;
        float result=0;
        int[] values=pos.get();
        for(i=0; i<values.length; i++){
            j=values[i];
            result+=map[i][j];
        }
        return result;
    }

    //evaluation process if fitness function was simulated
    private float simEval(Vector pos){
        float result=0;
        for(int i=0; i<coefficients.length; i++){
            result+=coefficients[i]*pos.getCoordinate(i);
        }
        return result;
    }
}

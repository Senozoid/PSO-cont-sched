import java.util.*;

public class Particle {

    Vector position;
    Vector velocity;
    Vector bestPos;
    float bestEval;
    final FitnessDetails fitness;
    Vector globalBestPos;

    public Particle(int dimension, int range, FitnessDetails fitness){
        position=new Vector(dimension, range, true);
        velocity=new Vector(dimension, range, true);
        bestPos=position;
        bestEval=fitness.eval(bestPos);
        this.fitness=fitness;
        //pDist=new Vector(dimension, range, false);
        //gDist=new Vector(dimension, range, false);
    }

    public Vector getPosition(){
        return position;
    }

    public Vector getBestPos(){
        return bestPos;
    }

    public float getBestEval(){
        return bestEval;
    }

    public float update(float[] parameters){
        updatePos();
        updateVel(parameters);
        updatePersonal();
        //updateGlobal(globalBestPos);
        return bestEval;
    }

    private void updatePos(){
        position=position.add(velocity);
    }

    private void updateVel(float[] parameters){
        Random impulse=new Random();
        Vector[] terms=new Vector[3];
        Vector pDist=bestPos.sub(position);
        Vector gDist=globalBestPos.sub(position);

        terms[0]=velocity.mul(parameters[0]);//inertia
        terms[1]=pDist.mul(parameters[1]).mul(impulse.nextFloat(parameters[3])+1);//cognitive component
        terms[2]=gDist.mul(parameters[2]).mul(impulse.nextFloat(parameters[3])+1);//social component

        velocity=terms[0].add(terms[1]).add(terms[2]);
    }

    private void updatePersonal(){
        float currEval=fitness.eval(position);
        if(currEval<fitness.eval(bestPos)){
            bestPos=position;
            bestEval=currEval;
        }
    }

    public void updateGlobal(Vector globalBestPos){
        this.globalBestPos=globalBestPos;
    }

}

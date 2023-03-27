public class Swarm {
    FitnessDetails fitness;
    Particle[] particles;
    Vector bestPos;
    float bestEval;

    public Swarm(int[] space, FitnessDetails fitness){
        this.fitness=fitness;
        int size=space[0];
        particles=new Particle[size];
        for (int i=0; i<size; i++){
            particles[i]=new Particle(space[1],space[2],fitness);
        }
        bestPos=particles[0].getPosition();
        bestEval=fitness.eval(bestPos);
        checkParticles();
    }

    public boolean update(float[] parameters){
        boolean change=false;
        for (Particle particle:particles){
            if(particle.update(parameters)<bestEval) {
                updateBestsFrom(particle);
                change=true;
            }
        }
        return change;
    }

    public void checkParticles(){
        for (Particle particle:particles){
            if(particle.getBestEval()<bestEval){
                updateBestsFrom(particle);
            }
        }
    }

    private void updateBestsFrom(Particle particle){
        bestPos=particle.getBestPos();
        bestEval=particle.getBestEval();
        //tellParticles();
        for(Particle listener : particles){
            listener.updateGlobal(bestPos);
        }
    }

    public Vector getBestPos(){
        return bestPos;
    }

    public float getBestEval(){
        return bestEval;
    }

}

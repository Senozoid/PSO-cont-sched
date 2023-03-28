import java.util.*;

public class Vector {

    public final int dimension;
    final int range;
    int[] coordinates;

    public Vector(int dimension, int range, boolean randomness){
        this.dimension=dimension;
        this.range=range;
        coordinates=new int[dimension];
        if(randomness) randomize();
        else set(0);
    }

    private void randomize(){
        Random chaos=new Random();
        for(int i=0;i<dimension;i++){
            setCoordinate(i,chaos.nextInt(range));
        }
    }

    public Vector add(Vector toAdd){
        Vector sum=new Vector(dimension,range,false);
        for(int i=0;i<dimension;i++){
            sum.setCoordinate(i,(coordinates[i]+toAdd.getCoordinate(i)));
        }
        return sum;
    }

    public Vector sub(Vector toSub) {
        Vector diff=new Vector(dimension,range,false);
        for(int i=0;i<dimension;i++){
            diff.setCoordinate(i,(coordinates[i]-toSub.getCoordinate(i)));
        }
        return diff;
    }

    public Vector mul(float toMul){
        Vector prod=new Vector(dimension,range,false);
        for(int i=0;i<dimension;i++){
            prod.setCoordinate(i, (toMul*coordinates[i]));
        }
        return prod;
    }

    public int getCoordinate(int axis){
        return coordinates[axis];
    }

    public void setCoordinate(int axis, int coordinate){
        if(coordinate<0) coordinate=0;
        if(coordinate>=range) coordinate=(range-1);
        coordinates[axis]=coordinate;
    }

    public void setCoordinate(int axis, float coordinate){
        if(coordinate<0) coordinate=0;
        if(coordinate>=range) coordinate=(range-1);
        coordinates[axis]=Math.round(coordinate);
    }

    public int[] get(){
        return coordinates;
    }

    public void set(int coordinate){
        if(coordinate<0) coordinate=0;
        for(int i=0;i<dimension;i++){
            setCoordinate(i,coordinate);
        }
    }

    public String toString(){
        return Arrays.toString(coordinates);
    }

}

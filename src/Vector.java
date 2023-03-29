import java.util.*;

public class Vector {

    public final int dimension;
    public final int range;
    boolean index;
    private int[] coordinates;

    public Vector(int dimension, int range, boolean index){
        this.dimension=dimension;
        this.range=range;
        this.index=index;
        coordinates=new int[dimension];
        randomize();
    }

    public Vector(int dimension){
        this.dimension=dimension;
        range=Integer.MAX_VALUE;
        index=false;
        coordinates=new int[dimension];
        randomize();
    }

    private void randomize(){
        Random chaos=new Random();
        for(int i=0;i<dimension;i++){
            if(index) setCoordinate(i,chaos.nextInt(range+1));
            else setCoordinate(i,chaos.nextInt(-100,101));
        }
    }

    public Vector add(Vector toAdd){
        Vector sum=new Vector(dimension);
        for(int i=0;i<dimension;i++){
            sum.setCoordinate(i,(coordinates[i]+toAdd.getCoordinate(i)));
        }
        return sum;
    }

    public Vector sub(Vector toSub) {
        Vector diff=new Vector(dimension);
        for(int i=0;i<dimension;i++){
            diff.setCoordinate(i,(coordinates[i]-toSub.getCoordinate(i)));
        }
        return diff;
    }

    public Vector mul(float toMul){
        Vector prod=new Vector(dimension);
        for(int i=0;i<dimension;i++){
            prod.setCoordinate(i, (toMul*coordinates[i]));
        }
        return prod;
    }

    public int getCoordinate(int axis){
        return coordinates[axis];
    }

    public void setCoordinate(int axis, int coordinate){
        if(index && (coordinate<0)) coordinate=0;
        if(coordinate>=range) coordinate=(range-1);
        coordinates[axis]=coordinate;
    }

    public void setCoordinate(int axis, float coordinate){
        if(index && (coordinate<0)) coordinate=0;
        if(coordinate>=range) coordinate=(range-1);
        coordinates[axis]=Math.round(coordinate);
    }

    public int[] get(){
        return coordinates.clone();
    }

    /*
    public void set(int coordinate){
        for(int i=0;i<dimension;i++){
            setCoordinate(i,coordinate);
        }
    }
    */

    public String toString(){
        return Arrays.toString(coordinates);
    }

}

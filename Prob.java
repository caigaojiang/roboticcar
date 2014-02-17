/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Yosef
 */
public class Prob {
    private ArrayList<Double> grid;
    
    
    public Prob(int worldSize){
        grid = new ArrayList<>();
        for (int i = 0; i<worldSize;i++){
            grid.add(i, 1.0/worldSize);
        }
    }
    public Prob(int worldSize, int opt){
        grid = new ArrayList<>();
        if (opt == 1){
            for (int i = 0; i<worldSize;i++){
            grid.add(i, 0.0);
        }
        
        }
    }
    public Prob(int[] mat){
        grid = new ArrayList<>();
        for (int i = 0; i<mat.length;i++){
            grid.add(i, (double)mat[i]);
        }
    }
    @Override
    public String toString(){
        return Arrays.toString(grid.toArray());
    }
    

    public void setLocationMult(int loc, double amt){
        grid.set(loc,grid.get(loc) * amt);
    }
    public void setLocationAdd(int loc, double amt){
        grid.set(loc,grid.get(loc) + amt);
    }
    public void setLocaction(int loc, Double amt){
        grid.set(loc,amt);
    }
    
    public double sum(){
        double sum = 0.0;
        for (Double i: grid){
            sum += i;
        }
        return sum;
        
    }

    public int size() {
        return grid.size();
    }
    public double get(int i){
        return grid.get(i);
    }
}

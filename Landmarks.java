/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;

/**
 *
 * @author Yosef
 */
public class Landmarks {
    private final double x;
    private final double y;
    public Landmarks(double xloc,double yloc){
        x = xloc;
        y = yloc;
    }
    public double getLandmarks(String pos){
        double ret = 0.0;
        if (pos == "x" || pos == "X"){
            ret = x;
        }
        else if (pos == "y" || pos == "Y"){
            ret = y;
        }
        return ret;
        
    }
    @Override
    public String toString(){
        return "X: " + x + " Y: " + y + "\n";
    }
}

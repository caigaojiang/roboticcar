/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;

import java.util.ArrayList;
import java.util.Random;

public class Robot {
    
    private double x;
    private double y;
    private double forward_noise;
    private double turn_noise;
    private double sense_noise;
    private double orientation;
    private double world_size;
    Random r = new Random();
    public Robot(){
        world_size = 100;
        x = Math.random() * world_size;
        y = Math.random() * world_size;
        orientation = Math.random() * 2.0 * Math.PI;
        forward_noise = 0.0;
        turn_noise    = 0.0;
        sense_noise   = 0.0;

    }    

    public void set(double newx, double newy, double neworientation){
        this.x = newx;
        this.y = newy;
        this.orientation = neworientation;
    }
    public void set_noise(double new_f_noise,double new_t_noise,double new_s_noise){
        forward_noise = new_f_noise;
        sense_noise = new_s_noise;
        turn_noise = new_t_noise;
        }
    public ArrayList<Double> sense(ArrayList<Landmarks> landmarks){  
        ArrayList<Double> Z = new ArrayList<Double>();
        Double dist;
        
        for (int i = 0; i<landmarks.size();i++){
            
            dist = 0.0;
            dist = Math.sqrt(Math.pow(this.x - landmarks.get(i).getLandmarks("x"),2) + 
                    Math.pow(this.y - landmarks.get(i).getLandmarks("y"),2));
            dist +=  r.nextGaussian() * sense_noise;
            Z.add(dist);        
        }
        return Z;
    } 
    public Robot moved(double turn, double forward){  //Really need to check implementiation
        if (forward < 0.0){
            System.out.print("Value cannot be below 0");
            System.exit(1);
        }
    //    Random r = new Random(System.currentTimeMillis());
        double neworientation = this.orientation + (float)turn + r.nextGaussian()*turn_noise;
        neworientation %= (2 * Math.PI);
        
        double dist = (float)forward + r.nextGaussian()*forward_noise;
        double newx = this.x + Math.cos(neworientation) * dist;  // Not sure whether newx should be x or newx
        double newy = this.y + Math.sin(neworientation) * dist;
        newx %= world_size;
        newy %= world_size;
        Robot res = new Robot();
        res.set(newx,newy,neworientation);
        res.set_noise(forward_noise, turn_noise, sense_noise);
        return res;
        
    }
    public double Gaussian(double mu,double sigma, double gx){
        return (Math.exp((-1*( Math.pow(mu - gx, 2))/ (Math.pow(sigma, 2) / 2.0))))
                / (Math.sqrt(2.0 * Math.PI * (Math.pow(sigma, 2))));
    }
   
    public double measurement_prob(ArrayList<Double> measurement,ArrayList<Landmarks> landmarks){
        //Need to check on the type of measurement
        double prob = 1.0;
        
        for(int i =0;i<landmarks.size();i++){
             double dist = Math.sqrt(Math.pow(this.x - landmarks.get(i).getLandmarks("x"), 2) + 
                     Math.pow(this.y - landmarks.get(i).getLandmarks("y"),2));
             prob *= Gaussian(dist,sense_noise,measurement.get(i));
        }
        return prob;
    }
    public void printRobot(){
        System.out.print("X: "+ x + " Y: " + y + " Orientation: " + orientation);
    }
    @Override
    public String toString(){
        return "X: "+ x + " Y: " + y + " Orientation: " + orientation + " Deg: " + Math.toDegrees(orientation);
    }
    
}
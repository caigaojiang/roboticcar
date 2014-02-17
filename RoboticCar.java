/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Yosef
 */
public class RoboticCar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        /*int worldSize = 5;
        double pHit = .6;
        double pMiss = .2;
        String[] world = {"green", "red", "red","green", "green"};
        String[] Z = {"red","green"};
        
        World w = new World(world); //Known Enviornment
        World m = new World(Z); //Measurements
        Prob p = new Prob(w.size()); //Probability Distro;
        //Localization l = new Localization();  //Localization Library
        Localization.sense(p, w, "green", pHit, pMiss);
        //System.out.println(p);
        p = Localization.move(p, 3);
        //System.out.println(p);
        * */
        ArrayList<Landmarks> landmarks = new ArrayList<Landmarks>();
        Random rand = new Random();
        landmarks.add(new Landmarks(20.0,20.0));
        landmarks.add(new Landmarks(80.0,80.0));
        landmarks.add(new Landmarks(20.0,80.0));
        landmarks.add(new Landmarks(80.0,20.0));
        
        Robot r = new Robot();
        r.set_noise(5.0, .1, 5.0);
        r.set(30.0, 50.0, Math.PI/2);
        
        r = r.moved((-1 * Math.PI/2),15.0);
        //r.sense(landmarks);
        //System.out.println(r.sense(landmarks));
        r = r.moved((-1 * Math.PI/2),10.0);
        //System.out.println(r.sense(landmarks));
        int particleNum = 1000;
        ArrayList<Robot> particle = new ArrayList<Robot>();
        for (int i = 0;i<particleNum;i++){
            Robot x = new Robot();
            x.set_noise(.05,.05,4.0);
            particle.add(x);
        }
        int numTimes = 2;
        for (int numt = 0;numt < numTimes; numt++){
            
            ArrayList<Robot> particle2 = new ArrayList<Robot>();
            for (int i = 0; i<particleNum;i++){
                particle2.add(particle.get(i).moved(.1, 5.0));
            }
            particle = particle2;

            ArrayList<Double> weights = new ArrayList<Double>();
           for (int i = 0; i<particle.size();i++){
                ArrayList<Double> measurments = particle.get(i).sense(landmarks);
                double weight = particle.get(i).measurement_prob(measurments, landmarks);
                weights.add(weight);

            }
            ArrayList<Robot> particle3 = new ArrayList<Robot>();
            int index = (int)(rand.nextInt(particleNum));
            double beta = 0.0;

            double mw = Collections.max(weights);
            for (int i = 0;i<particleNum;i++){
                beta += Math.random() * 2.0 * mw;
                while (beta > weights.get(index)){
                    beta -= weights.get(index);
                    index = (index + 1) % particleNum;
                }
                particle3.add(particle.get(index));

            }
            particle = particle3;
        for (int i = 0;i<50;i++){
            System.out.println("T" + numTimes + ": " + particle.get(i));
                    
        }
            }

        //System.out.println(weights.size());
        

         //System.out.println(particle.size());
        //System.out.println(l.sense(p, w, m.get(0), pHit, pMiss));
        //System.out.println(l.sense(p, w, m.get(1), pHit, pMiss));
        
        /*
        Gaussian upd = new Gaussian();
        Gaussian measurement  = new Gaussian();
        Gaussian motion  = new Gaussian();
        
                     
        upd.mean = 0.0;
        upd.vari = .00000001;
        measurement.vari = 4.0;
        motion.vari = 2.0;
        double[] meas = {5.0,6.0,7.0,9.0,10.0};
        double[] mot = {1.0,1.0,2.0,1.0,1.0};
        for (int i = 0;i<meas.length;i++){
            System.out.print("Update: ");
            upd = Localization.kalmanUpdate(upd.mean,upd.vari, meas[i], measurement.vari);
            System.out.println(upd);
            System.out.print("Predict: ");
            upd = Localization.kalmanPredict(upd.mean,upd.vari, mot[i], motion.vari);
            System.out.println(upd);
        }
        */
        

        
        
        
        
        
        
        
        
        
        
        
        
    }
}

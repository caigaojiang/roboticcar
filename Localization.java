/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;

/**
 *
 * @author Yosef
 */
public class Localization {
    public static double normalization(double num, double sum){
        return num/sum;
    }


    public static Prob sense(Prob p, World w, String Z,double pHit,double pMiss){
        
        for (int i = 0;i<p.size();i++){
           if (Z.equals(w.get(i))){
               p.setLocationMult(i, pHit);
           }
           else if (!Z.equals(w.get(i))){
               p.setLocationMult(i, pMiss);
           }
           
        }
        double s = p.sum();
        
        for (int i = 0; i<p.size();i++){
            p.setLocationMult(i, 1/s);
        }
               
        
       return p;
}
    public static Prob move(Prob p, int m){
        
        Prob d = new Prob(p.size(),1);
        int loc;
        for (int i = 0;i<p.size();i++){
            if (((i-m)%p.size())<0){
                loc = p.size() + ((i-m)%p.size());
            }
            else {
                loc = ((i-m)%p.size());
                        }
            d.setLocaction(i, Double.valueOf(p.get(loc)));
            }
        return d;
    }
    public static double uniformdistribution(double start, int num){
        return start/num;
    }
    public static Gaussian kalmanUpdate(double mean1, double var1, double mean2, double var2){
        Gaussian kal = new Gaussian();
        kal.mean = (1/(var1 + var2))*((var2*mean1)+(var1*mean2));
        kal.vari = (1/((1/var2)+(1/var1)));
        return kal;
    }
    public static Gaussian kalmanPredict(double mean1, double var1, double mean2, double var2){
        Gaussian kal = new Gaussian();
        kal.mean = mean1+ mean2;
        kal.vari = var1+var2;
        return kal;
    }

    }

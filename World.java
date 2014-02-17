/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticcar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Yosef-
 */
public class World {
       private ArrayList<String> grid;
    
    
    public World(int worldSize){
        grid = new ArrayList<>();
        for (int i = 0; i<worldSize;i++){
            grid.add(i, null);
        }
    }
    public World(String[] s){
        grid = new ArrayList<>();
        for (int i = 0; i<s.length;i++){
            grid.add(i, s[i]);
        }
    }
    @Override
    public String toString(){
        return Arrays.toString(grid.toArray());
    }
    

    public void setWorld(int loc, String Loc){
        grid.set(loc,Loc);
    }

    public int size() {
        return grid.size();
    }

    public String get(int i) {
        return grid.get(i);
    }
    
}


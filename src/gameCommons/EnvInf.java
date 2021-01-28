package gameCommons;

import environment.Environment;
import environment.Lane;
import util.Case;

import java.util.Iterator;
import java.util.ArrayList;

public class EnvInf extends Environment{

    private Game game;
    private ArrayList<Lane> numberOfLanes;


    public EnvInf(Game game1) {
        super(game1);
        this.game = game1;
        if (this.game.isInfinite()) {
            this.numberOfLanes = this.initialisationLaneInf();
        }
    }

    private ArrayList<Lane> initialisationLaneInf() {
        ArrayList<Lane> initialisation = new ArrayList();
        initialisation.add(new Lane(this.game, 0, 0.0D));
        initialisation.add(new Lane(this.game, 1, 0.0D));
        //initialisation.add(new Lane(this.game, 2, 0.0D));
        //initialisation.add(new Lane(this.game, 3, 0.0D));

        for(int i = 2; i < this.game.height; ++i) {
            initialisation.add(new Lane(this.game, i, this.game.defaultDensity));
        }

        return initialisation;
    }


    public boolean isSafe(Case c) {
        Lane l;
        int k = numberOfLanes.size();
        int i = 0;
        do {
            if (i >= k) {
                return false;
            }
            l = numberOfLanes.get(i);
            i++;
        } while(l.ord != c.ord);

        return l.isSafe(c);

        //return this.Lane1.get(c.ord).isSafe(c);
    }

    public void update() {
        /*Lane l;
        int k = numberOfLanes.size();

        for(int i = 0 ; i < k ; i++){
            l = numberOfLanes.get(i);
            l.update();
        }
        */
        for (int i = 0; i < numberOfLanes.size(); i++) {
            numberOfLanes.get(i).update();
        }
    }

    private int getMaxOrd() {
        int max = -1;
        Lane l;
        int k = numberOfLanes.size();


        for(int i = 0 ; i < k ; i++){
            l = numberOfLanes.get(i);
            if (max < l.ord) {
                max = l.ord;
            }
        }

        return max;
    }

    private int getMinOrd() {
        int min = -1;
        Lane l;
        int k = numberOfLanes.size();


        for(int i = 0 ; i < k ; i++){
            l = numberOfLanes.get(i);
            if (min > l.ord) {
                min = l.ord;
            }
        }

        return min;
    }

    public void laneOrd(int ancienneOrd, int nouvelleOrd) {
        numberOfLanes.get(ancienneOrd).switchOrd(nouvelleOrd);
    }

    public void moveUp() {
        Lane l;
        int k = numberOfLanes.size();

        for(int i = 1 ; i < k ; i++){
            l = numberOfLanes.get(i);
            l.moveUpAll();
        }

        if (this.getMaxOrd() < this.game.height) {
            this.numberOfLanes.add(new Lane(this.game, this.getMaxOrd()+1, this.game.defaultDensity));
        }
    }

    public void moveDown(){
        Lane l;
        int k = numberOfLanes.size();


        for(int i = 0 ; i < k ; i++){
            l = numberOfLanes.get(i);
            l.moveDownAll();
        }

        if (this.getMinOrd() < 0) {
            this.numberOfLanes.add(new Lane(this.game, this.getMinOrd()-1, this.game.defaultDensity));
        }

    }
}

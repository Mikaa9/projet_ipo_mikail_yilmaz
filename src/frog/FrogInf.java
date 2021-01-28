package frog;

import util.Case;
import util.Direction;
import gameCommons.EnvInf;
import gameCommons.Game;

public class FrogInf extends Frog {

    private EnvInf environment;
    private int score;
    private Case position;
    private Game game;
    private Direction direction;


    public FrogInf(Game game1) {
        super(game1);
        this.game = game1;
        this.score = 0;
        this.direction = Direction.up;
        this.position = new Case(game.width/2 , 1);
    }

    public int getScore() {
        return this.score;
    }

    public Case getPosition() {
        return this.position;
    }

    public Direction getDirection(){
        return this.direction;
    }

    /**
     * Déplace la grenouille dans la direction donne et teste la fin de partie
     * @param key
     */
    public void moveInf(Direction key) {
        this.direction = key;
        if( key == Direction.up ) {
            this.game.EnvInf.moveUp();
            this.game.score++;
            this.game.testLose();
        }

        else if( key == Direction.down ) {
            if(this.position.ord != 0) {
                this.game.score--;
                this.game.EnvInf.moveDown();
                this.game.testLose();
            }
        }

        else if ( key == Direction.left ) {
            if(this.position.absc > 0) {
                this.position = new Case( this.position.absc-1, this.position.ord );
            }
        }

        else if( key == Direction.right ) {
            if(this.position.absc < this.game.width - 1) {
                this.position = new Case( this.position.absc+1, this.position.ord );
            }
        }
        //this.direction = key;
        this.game.testLose();
    }



}

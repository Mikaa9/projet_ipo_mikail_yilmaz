package environment;

import java.util.ArrayList;
import java.util.Random;


import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//TODO
	private Game game;
	public final Random Random = new Random();
	private ArrayList<Lane> Lane1;
	private int nbLane;
	
	public Environment(Game game1) {

		this.game = game1;
		this.nbLane = this.game.height;
		this.Lane1 = new ArrayList<Lane>(nbLane);
		Lane1.add(new Lane(game, 0, 0.0));

		for(int i=1; i<game.height-1; i++) {
			double density = Random.nextDouble() * (0.3);
			Lane1.add(new Lane(game, i, density));
		}

		Lane1.add(new Lane(game, game.height-1, 0.0));
		
	}

	public boolean isSafe(Case posFrog) {

		return this.Lane1.get(posFrog.ord).isSafe(posFrog);
	}
	
	public ArrayList<Lane> getLane() {

		return this.Lane1;
	}	
	
	public Game getGame() {

		return this.game;
	}
	

	public boolean isWinningPosition(Case posFrog) {

		return posFrog.ord == game.height - 1;
	}
	
	public void update() {
		for (int i = 0; i < Lane1.size(); i++) {
			Lane1.get(i).update();
		}
	}

	@Override
	public void laneOrd(int ancienneOrd, int nouvelleOrd) {

	}

	@Override
	public void moveUp() {

	}

	@Override
	public void moveDown() {

	}


}

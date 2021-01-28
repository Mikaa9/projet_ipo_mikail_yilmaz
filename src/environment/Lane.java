package environment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import util.Case;
import gameCommons.Game;

public class Lane {
	
	public final Random Random = new Random();
	public int ord;

	private Game game;
	private int speed;
	private ArrayList<Car> cars;
	private boolean leftToRight;
	private double density;
	private int timer;

	// TODO : Constructeur(s)

	public Lane(Game game1, int ord1 , double density1) {
		this.game = game1;
		this.ord = ord1;
		this.speed = Random.nextInt(game.minSpeedInTimerLoops) + 1;
		this.cars = new ArrayList();
		this.leftToRight = Random.nextBoolean();
		this.density = density1;
		this.timer = 0;

		for(int i = 0; i < game.width ; ++i) {
            this.moveCars(true);
            this.mayAddCar();
        }
	}

	//Constructeurs de Lane avec la densité default
	public Lane(Game game, int ord) {
		this(game, ord, game.defaultDensity);
	}

		// TODO

		// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
		// d'horloge" égal à leur vitesse
		// Notez que cette méthode est appelée à chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut être ajoutée

		public void update() {
			/*timer++;
			if (this.timer >= this.speed) {
				for(Car gova : cars) {
					gova.moveCar();
				}
				mayAddCar();
				this.timer =0;
			}
			else {
				moveCars();
				for (Car gova : cars) {
				gova.addToGraphics();
				}
			}*/
			this.timer++;
			if( this.timer <= this.speed ) {
					this.moveCars(false);
			} else {
				this.moveCars(true);
				this.mayAddCar();
				this.timer = 0;
			}

		}
		
		private void moveCars(boolean h) {
			
				for (int i = 0; i < cars.size(); i++) {
					cars.get(i).moveCar(h);
				}
			
		}

	public void moveUpAll() {
		this.ord--;
		int k = cars.size();
		Car c;

		for(int i = 0 ; i < k ; i++){
			c = cars.get(i);
			c.moveUp();
		}

	}


	public void moveDownAll() {
		this.ord++;
		int k = cars.size();
		Car c;


		for(int i = 0 ; i < k ; i++){
			c = cars.get(i);
			c.moveDown();
		}

	}
	
		public ArrayList<Car> getCars() {

			return this.cars;

		}	
		
		public int getOrd() {

			return this.ord;

		}
		
		public void switchOrd (int newOrd ) {

			this.ord = newOrd;

		}

	// TODO : ajout de methodes
		
		/**
		 * A function telling whether a case is safe or not (whether a car is already
		 * present)
		 * 
		 * @param Casefrog the case we want to know whether its safe or not
		 * @return true if the case is safe, else fase
		 */
		public boolean isSafe(Case Casefrog) {
			for (Car c : cars) { 
				if (c.caseidentique(Casefrog))
					return false;
			}
			return true;
		}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}

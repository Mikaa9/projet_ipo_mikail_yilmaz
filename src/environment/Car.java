package environment;

import java.awt.Color;

import java.util.Random;
import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	
	
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.YELLOW;
	
	public final Random Random = new Random();
	

	//TODO Constructeur(s)
	public Car(Game game1, Case Pos , boolean leftToRight1) {
		this.game = game1;
		this.leftToRight = leftToRight1;
		this.length = //Random.nextInt((3 - 1)+1) + 1 ;
				1 + (int)(Math.random() * ((3 - 1) + 1));
		if (leftToRight) {
            this.leftPosition = new Case(Pos.absc - this.length, Pos.ord);
        } else {
            this.leftPosition = new Case(Pos.absc, Pos.ord);
        }
    }
	
	//TODO : ajout de methodes
	public void moveCar(boolean yes) {
		
		if (yes) {
			this.leftPosition = new Case(leftPosition.absc + (this.leftToRight ? 1 : -1), leftPosition.ord);
		}
		this.addToGraphics();
	}
	public int getLength() {
		return this.length;
	}
	
	public boolean leftToRight() {
		return this.leftToRight;
	}
	
	public Case position() {
		return this.leftPosition;
	}

	public void moveUp() {
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
	}

	public void moveDown() {
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + 1);
	}
	
	public boolean caseidentique(Case c) {
		
		if(leftPosition.ord != c.ord) {
			return false;
		}
		else if (leftPosition.absc <= c.absc ) {
			//ne pas mettre = seul ou < seul sinon bug(idk why)
				if (leftPosition.absc + length >= c.absc) {
			return true;
				}
		}		
		return false;
		
	}


	//Dit si la voiture est sur la voie
	public boolean voituresurvoie() {
		return this.leftPosition.absc + this.length > -2 || this.leftPosition.absc < this.game.width;
	}


	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	 void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	
	
	

}

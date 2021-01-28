package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import frog.Frog;
import frog.FrogInf;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.11;
		boolean infinite = true;

		//Création de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity , infinite);
		//Création et liason de la grenouille
		if(infinite == true){
			IFrog frog = new FrogInf(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
		} else {
			IFrog frog = new Frog(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
		}

		//Création et liaison de l'environnement
		if(infinite == true) {
			Environment env = new EnvInf(game);
			game.setEnvironment(env);
		} else {
			IEnvironment env = new Environment(game);
			game.setEnvironment2(env);
		}

		//Boucle principale : l'environnement s'actualise tous les temps milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}

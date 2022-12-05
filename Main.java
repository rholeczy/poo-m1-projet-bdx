package fr.ubordeaux.miage.s7.todolist;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Classe principale
 * Lance l'applicatin dans une fenêtre mainStage
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) {
		// On lance un nouveau contrôleur
		try {
			new Controller(mainStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

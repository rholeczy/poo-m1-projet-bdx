package fr.ubordeaux.miage.s7.todolist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;
import fr.ubordeaux.miage.s7.todolist.controller.IController;
import javafx.collections.ObservableList;

public class Model implements Observable {

	// Liste de priorité de tâches
	private PriorityQueue<Task> tasks;

	// L'état du modèle influencera ces observateurs.
	// Il n'y a qu'un seul observateur pour l'instant: la vue
	private List<IController> observers;
	private Task nextTask;
	private Task currentTask;

	private ObservableList<String> topics;

	/*
	 * Constructeur du modèle
	 */
	public Model() {
		// On initialise la liste des observers
		observers = new ArrayList<IController>();

		// On initialise la liste des tâches en indiquant
		// - La capacité initiale de 31 (elle augmentera automatiquement si besoin)
		// - La comparaison appliquée entre deux tâches
		tasks = new PriorityQueue<Task>(new TaskComparator<Task>());
	}

	@Override
	public void attach(IController observer) {
		observers.add(observer);
	}

	@Override
	public void notifyAllObservers() throws Exception {
		for(IController obs : observers) {
			obs.update(this);
		}
	}


}

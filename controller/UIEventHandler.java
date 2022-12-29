package fr.ubordeaux.miage.s7.todolist.controller;

import fr.ubordeaux.miage.s7.todolist.controller.state.Action;
import fr.ubordeaux.miage.s7.todolist.model.Model;
import fr.ubordeaux.miage.s7.todolist.model.TaskCategory;
import fr.ubordeaux.miage.s7.todolist.view.View;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class UIEventHandler implements EventHandler<Event> {

	private Model model;
	private View view;
	private Controller controller;

	public UIEventHandler(Controller controller) {
		this.controller = controller;
		this.model = controller.model;
		this.view = controller.view;
	}

	@Override
	public void handle(Event event) {
		/*
		 * On récupère l'objet qui a émis cet événement. Selon cet identifiant, on
		 * modifie le modèle en conséquence.
		 * 
		 */

		String id = null;

		Object source = event.getSource();
		if (source instanceof MenuItem) {
			id = ((MenuItem) source).getId();

		} else if (source instanceof Button) {
			id = ((Button) source).getId();

		} else if (source instanceof ListView) {

			System.out.println(((ListView<?>) source).getSelectionModel().getSelectedItems());


		} else {
			System.err.println("**** Error: unexpected case: " + source.getClass().getName());
		}

		try {
			switch(id) {
				case "menuItem1":
					controller.getCurrentState().handle(controller,Action.SELECT_ACTION);
					break;
				case "menuItem2":
					controller.getCurrentState().handle(controller,Action.SELECT_ACTION);
					break;
				case "menuItem3":
					controller.getCurrentState().handle(controller,Action.SELECT_ACTION);
					break;
				case "proceed_btn":
					controller.getCurrentState().handle(controller,Action.PROCEED_ACTION);
					break;
				case "dialog_ok_btn":
					System.out.println("dialog_ok_btn");
					break;
				case "dialog_cancel_btn":
					System.out.println("dialog-cancel-btn");
					break;
				default:
					System.out.println("error");
					break;
			}



			model.notifyAllObservers(); // Update de tous les observers

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}

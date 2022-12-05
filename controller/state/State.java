package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public abstract class State {

	public enum States{
		INIT_STATE, 
		ERROR_STATE, 
		EDIT_STATE, 
		PROCEED_STATE, 
		RECORD_STATE
	};
	
	public abstract States getType();

	public abstract void handle(Controller controller, Action proceeds) throws Exception;

	@Override
	public String toString() {
		return getType().name();
	}

}

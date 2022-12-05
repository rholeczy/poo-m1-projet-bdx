package fr.ubordeaux.miage.s7.todolist.model;

import fr.ubordeaux.miage.s7.todolist.controller.IController;

public interface Observable{

	void attach(IController observer);
	void notifyAllObservers() throws Exception;


}

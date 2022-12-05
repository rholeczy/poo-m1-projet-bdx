package fr.ubordeaux.miage.s7.todolist.controller;

import fr.ubordeaux.miage.s7.todolist.model.Observable;

public interface IController {

	void update(Observable observable) throws Exception;

}

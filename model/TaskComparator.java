package fr.ubordeaux.miage.s7.todolist.model;

import java.util.Comparator;

public class TaskComparator<T> implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		return task1.compareTo(task2);
	}

}

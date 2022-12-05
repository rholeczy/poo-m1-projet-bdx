package fr.ubordeaux.miage.s7.todolist.model;

import java.util.List;

/*
 * Tâche
 */
public abstract class Task implements Comparable<Task> {

	// Les tâches sont numérotées par ordre d'arrivée croissant
	private static int nextOrder;
	private Integer order;
	protected TaskCategory taskCategory;
	private List<String> topics;

	public Task(List<String> topics) {
		this.order = nextOrder++;
		this.topics = topics;
	}

	/*
	 * Fonction de comparaison de deux tâches
	 * Si la première est prioritaire: -1
	 * Si la seconde est prioritaire: +1
	 * Si elles sont de même priorité: 0
	 */
	@Override
	public int compareTo(Task o) {
		if (priority() < o.priority())
			return -1;
		else if (priority() > o.priority())
			return 1;
		else if (order < o.order)
			return -1;
		else if (order > o.order)
			return 1;
		return 0;
	}

	public int priority() {
		return taskCategory.getPriority();
	}

	public int order() {
		return this.order;
	}

	public String description() {
		String result = "Tâche n° " + order + "[";
		boolean first = true;
		for (String topic : topics) {
			if (first) first=false; else result += ", ";
			result += topic + " ";
		}
		result += "]";
		return result;
	}

}

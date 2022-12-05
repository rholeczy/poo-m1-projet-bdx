package fr.ubordeaux.miage.s7.todolist.view;

import java.util.List;
import fr.ubordeaux.miage.s7.todolist.controller.Controller;
import fr.ubordeaux.miage.s7.todolist.controller.UIEventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View  {

	private Stage stage;
	private Label state_lb;
	private TextArea todo_ta;
	private MenuButton menu_btn;
	private Button proceed_btn;

	private Stage dialog_edition;
	private Label dialog_edition_lb;
	private Text dialog_edition_txt;
	private Label dialog_edition_resources_lb;
	private EventHandler<Event> uiEventHandler;
	private ObservableList<String> dialog_edition_ressources;

	private Stage dialog_message;
	private Text dialog_message_txt;
	private Label dialog_message_lb;

	public View(Stage stage) {
		this.stage = stage;
	}

	public void init(Controller controller) {
		this.uiEventHandler = new UIEventHandler(controller); 		

		// Fenêtre principale
		this.stage.setTitle("To do list");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(4);
		grid.setVgap(4);
		BorderPane borderpane = new BorderPane();
		BorderPane.setAlignment(grid, Pos.CENTER);
		BorderPane.setMargin(grid, new Insets(24, 24, 24, 24));
		borderpane.setCenter(grid);

		state_lb = new Label();
		grid.add(state_lb, 0, 0);

		menu_btn = new MenuButton("Type de tâche");

		MenuItem menuItemHight = new MenuItem("Urgence Vitale");
		menuItemHight.setId("menuItem1");
		menuItemHight.addEventHandler(EventType.ROOT, uiEventHandler);
		MenuItem menuItemMedium = new MenuItem("Consultation médicale");
		menuItemMedium.setId("menuItem2");
		menuItemMedium.addEventHandler(EventType.ROOT, uiEventHandler);
		MenuItem menuItemLow = new MenuItem("Examen médical");
		menuItemLow.setId("menuItem3");
		menuItemLow.addEventHandler(EventType.ROOT, uiEventHandler);
		
		menu_btn.getItems().addAll(menuItemHight, menuItemMedium, menuItemLow);
		grid.add(menu_btn, 0, 3);

		Label todo_lb = new Label("Liste des tâches à réaliser");
		grid.add(todo_lb, 0, 5);
		todo_ta = new TextArea();
		todo_ta.setEditable(false);
		grid.add(todo_ta, 0, 6, 2, 1);

		proceed_btn = new Button("Réaliser la tâche prioritaire");
		proceed_btn.setId("proceed_btn");
		grid.add(proceed_btn, 0, 7, 2, 1);
		proceed_btn.setOnMouseClicked(uiEventHandler);
		
		
		Scene scene = new Scene(borderpane);
		stage.setScene(scene);
		stage.setResizable(false);

		// Fenêtre édition de la tâche
		dialog_edition = new Stage();
		dialog_edition.initModality(Modality.APPLICATION_MODAL);
		dialog_edition.initOwner(stage);

		GridPane dialog_grid = new GridPane();
		dialog_grid.setAlignment(Pos.CENTER);
		dialog_grid.setHgap(4);
		dialog_grid.setVgap(4);
		
		BorderPane.setMargin(dialog_grid, new Insets(24, 24, 24, 24));
		HBox dialogHBoxPane = new HBox();
		dialogHBoxPane.setPadding(new Insets(15, 20, 10, 10));
		dialogHBoxPane.getChildren().add(dialog_grid);
		
		dialog_edition_lb = new Label("Édition de la tâche");
		dialog_edition_txt = new Text();
		dialog_edition_resources_lb = new Label("Moyens à ajouter");
		ListView<String> listView = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"Salle déchocage", "Salle de consultation", 
				"Radiographie", "Brancardier", 
				"Généraliste", "Cardiologue");		
		listView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			dialog_edition_ressources = listView.getSelectionModel().getSelectedItems();
			uiEventHandler.handle(e);
		});
		listView.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			dialog_edition_ressources = listView.getSelectionModel().getSelectedItems();
			uiEventHandler.handle(e);
		});
		
		listView.setItems(items);
		listView.setPrefWidth(400);
		listView.setPrefHeight(200);
		listView.setOrientation(Orientation.VERTICAL);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		Button dialog_ok_btn = new Button("Enregistrer");
		dialog_ok_btn.setId("dialog_ok_btn");
		dialog_ok_btn.setOnMouseClicked(uiEventHandler);
		
		Button dialog_cancel_btn = new Button("Annuler");
		dialog_cancel_btn.setId("dialog_cancel_btn");
		
		dialog_cancel_btn.setOnMouseClicked(uiEventHandler);
		dialog_grid.add(dialog_edition_lb, 0, 0);
		dialog_grid.add(dialog_edition_txt, 1, 0);
		dialog_grid.add(dialog_edition_resources_lb, 0, 1);
		dialog_grid.add(listView, 0, 2, 2, 1);
		dialog_grid.add(dialog_cancel_btn, 0, 3);
		dialog_grid.add(dialog_ok_btn, 1, 3);
		Scene dialogScene = new Scene(dialogHBoxPane, 512, 300);
		dialog_edition.setScene(dialogScene);

		// Fenêtre message
		dialog_message = new Stage();
		dialog_message.initModality(Modality.APPLICATION_MODAL);
		dialog_message.initOwner(stage);

		GridPane dialog_message_grid = new GridPane();
		dialog_message_grid.setAlignment(Pos.CENTER);
		dialog_message_grid.setHgap(4);
		dialog_message_grid.setVgap(4);
		
		BorderPane.setMargin(dialog_message_grid, new Insets(24, 24, 24, 24));
		VBox dialog_message_VBoxPane = new VBox();
		dialog_message_VBoxPane.setPadding(new Insets(15, 20, 10, 10));
		dialog_message_VBoxPane.getChildren().add(dialog_message_grid);
		
		dialog_message_lb = new Label();
		dialog_message_VBoxPane.getChildren().add(dialog_message_lb);
		
		dialog_message_txt = new Text();
		dialog_message_VBoxPane.getChildren().add(dialog_message_txt);
		
		Button dialog_message_ok_btn = new Button("OK");
		dialog_message_VBoxPane.getChildren().add(dialog_message_ok_btn);
		dialog_message_ok_btn.setId("dialog_error_ok_btn");
		dialog_message_ok_btn.setOnMouseClicked(uiEventHandler);
		
		Scene dialog_message_Scene = new Scene(dialog_message_VBoxPane, 300, 100);
		dialog_message.setScene(dialog_message_Scene);
	}

	// Affiche la fenêtre principale
	public void show() {
		stage.show();
	}

	// Affiche la fenêtre édition
	public void showModalWindow(String name) {
		this.dialog_edition_txt.setText(name);
		dialog_edition.show();
	}

	// Cache la fenêtre édition
	public void hideModalWindow() {
		dialog_edition.hide();
	}

	// Remplit la liste des tâches
	public void setTextTodo_ta(List<String> taskDescriptions) {
		this.todo_ta.clear();
		for (String text : taskDescriptions) {
			this.todo_ta.appendText(text + "\n");
		}
	}

	// Affiche l'état courant
	public void setStateLabel(String text) {
		this.state_lb.setText(text);
	}

	// Liste des ressources 
	public ObservableList<String> dialog_ressources() {
		return dialog_edition_ressources;
	}

	// désactive le menu "Type de tâche" 
	public void disableMenu_btn(boolean disable) {
		this.menu_btn.setDisable(disable);
	}

	// Affiche la fenêtre de message
	public void showModalMessageWindow(String title, String errorMessage) {
		this.dialog_message_lb.setText(title);
		this.dialog_message_txt.setText(errorMessage);
		this.dialog_message.show();
	}

	// Cache la fenêtre de message
	public void hideModalMessageWindow() {
		this.dialog_message.hide();
	}

	// désactive le bouton "réaliser la tâche prioritaire" 
	public void disableProceed_btn(boolean disable) {
		this.proceed_btn.setDisable(disable);
	}

}
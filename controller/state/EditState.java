package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public class EditState extends State {

    private static EditState instance;

    private EditState() {}

    public static EditState getInstance(){
        if (instance==null){
            instance=new EditState();
        }
        return instance;
    }
    @Override
    public States getType() {
        return States.EDIT_STATE;
    }

    @Override
    public void handle(Controller controller, Action proceeds) throws Exception {

        switch(proceeds){

            case RECORD_ACTION:
                RecordState recordState = RecordState.getInstance();
                controller.setCurrentState(recordState);
                break;

            case CANCEL_ACTION:
                InitState initState = InitState.getInstance();
                controller.setCurrentState(initState);
                break;

            default:
                System.out.println("ERROR");
                break;
        }
    }
}

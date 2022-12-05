package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public class RecordState extends State{

    private static RecordState instance;

    private RecordState() {}

    public static RecordState getInstance(){
        if (instance==null){
            instance=new RecordState();
        }
        return instance;
    }


    @Override
    public States getType() {
        return States.RECORD_STATE;
    }

    @Override
    public void handle(Controller controller, Action proceeds) throws Exception {

        switch(proceeds){
            case OK_ACTION:
                InitState initState = InitState.getInstance();
                controller.setCurrentState(initState);
                break;

            default:
                System.out.println("ERROR");
                break;
        }
    }
}

package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public class ProceedState extends State{


    private static ProceedState instance;

    private ProceedState() {}

    public static ProceedState getInstance(){
        if (instance==null){
            instance=new ProceedState();
        }
        return instance;
    }


    @Override
    public States getType() {
        return States.PROCEED_STATE;
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

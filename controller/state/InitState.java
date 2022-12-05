package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public class InitState extends State{

    private static InitState instance;

    private InitState() {}

    public static InitState getInstance(){
        if (instance==null){
            instance=new InitState();
        }
        return instance;
    }

    @Override
    public States getType() {
        return States.INIT_STATE;
    }

    @Override
    public void handle(Controller controller, Action proceeds) throws Exception {

        switch(proceeds){
            case SELECT_ACTION:
                EditState editState = EditState.getInstance();
                controller.setCurrentState(editState);
                break;

            case PROCEED_ACTION:
                ProceedState proceedState = ProceedState.getInstance();
                controller.setCurrentState(proceedState);
                break;

            default:
                System.out.println("ERROR");
                break;
        }
    }
}

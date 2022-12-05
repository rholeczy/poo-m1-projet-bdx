package fr.ubordeaux.miage.s7.todolist.controller.state;

import fr.ubordeaux.miage.s7.todolist.controller.Controller;

public class ErrorState extends State{

    private static ErrorState instance;

    private ErrorState() {}

    public static ErrorState getInstance(){
        if (instance==null){
            instance=new ErrorState();
        }
        return instance;
    }

    @Override
    public States getType() {
        return States.ERROR_STATE;
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

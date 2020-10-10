package ui;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Main {
    public static void main(String[] args) {
        new Game();
//        ControllerManager controllers = new ControllerManager();
//        controllers.initSDLGamepad();
//
//        while(true) {
//            ControllerState currState = controllers.getState(0);
//
//            if(!currState.isConnected) {
//                break;
//            }
//            if(currState.a) {
//                System.out.println("\"A\" on \"" + currState.controllerType + "\" is pressed");
//            }
//            if(currState.b) {
//                System.out.println("\"B\" on \"" + currState.controllerType + "\" is pressed");
//            }
//            if(currState.y) {
//                System.out.println("\"Y\" on \"" + currState.controllerType + "\" is pressed");
//            }
//            if(currState.x) {
//                System.out.println("\"X\" on \"" + currState.controllerType + "\" is pressed");
//            }
//        }
    }
}

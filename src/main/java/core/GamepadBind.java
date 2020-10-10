package core;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import core.character.Character;

public class GamepadBind implements Runnable {
    private final float THRESHOLD = 0.2F;
    private final Character character;

    public GamepadBind(Character character) {
        this.character = character;
    }


    @Override
    public void run() {
        ControllerManager controllerManager = new ControllerManager();
        controllerManager.initSDLGamepad();

        while (true) {
            ControllerState controller = controllerManager.getState(0);
            if (!controller.isConnected) {
                System.out.println("Disconnected");
            }
            if (controller.leftStickY > THRESHOLD) {
                character.top();
            }
            if (controller.leftStickX > THRESHOLD) {
                character.right();
            }
            if (controller.leftStickY < -THRESHOLD) {
                character.bottom();
            }
            if (controller.leftStickX < -THRESHOLD) {
                character.left();
            }
            if(controller.a){
                controllerManager.doVibration(0, .9F, .9F, 1000);
            }
            if (controller.start) {
                controllerManager.quitSDLGamepad();
                System.exit(0);
            }
            if (controller.leftStickX > -THRESHOLD && controller.leftStickX < THRESHOLD && controller.leftStickY > -THRESHOLD && controller.leftStickY < THRESHOLD && controller.isConnected) {
                character.stopMove();
            }
        }
    }
}

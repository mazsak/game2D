package core;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import constant.Const;
import core.character.Character;
import ui.Game;

import java.util.List;

public class BindGamepad implements Runnable {
    private final Game game;
    private final List<Character> characters;
    private boolean isMulti = false;

    public BindGamepad(Game game) {
        this.game = game;
        this.characters = game.getCharacters();
    }


    @Override
    public void run() {
        ControllerManager controllerManager = new ControllerManager();
        controllerManager.initSDLGamepad();
        while (true) {
            if (controllerManager.getNumControllers() > 1 && !isMulti){
                game.addPlayer();
                isMulti = true;
            }
            for (Character character : characters) {
                ControllerState controller = controllerManager.getState(character.getIndexGamepad());
                if (!controller.isConnected) {
//                    System.out.println("Disconnected");
                }
                if (controller.leftStickY > Const.THRESHOLD) {
                    character.movingVertical((int) (Const.SPEEDHERO * controller.leftStickY));
                }
                if (controller.leftStickX > Const.THRESHOLD) {
                    character.movingHorizontally((int) (Const.SPEEDHERO * controller.leftStickX));
                }
                if (controller.leftStickY < -Const.THRESHOLD) {
                    character.movingVertical((int) (Const.SPEEDHERO * controller.leftStickY));
                }
                if (controller.leftStickX < -Const.THRESHOLD) {
                    character.movingHorizontally((int) (Const.SPEEDHERO * controller.leftStickX));
                }
                if (controller.a) {
                    controllerManager.doVibration(character.getIndexGamepad(), .9F, .9F, 1000);
                }
                if (controller.start) {
                    controllerManager.quitSDLGamepad();
                    System.exit(0);
                }
            }
//            if (controller.leftStickX > -THRESHOLD && controller.leftStickX < THRESHOLD && controller.leftStickY > -THRESHOLD && controller.leftStickY < THRESHOLD && controller.isConnected) {
//                character.stopMove();
//            }
        }
    }
}

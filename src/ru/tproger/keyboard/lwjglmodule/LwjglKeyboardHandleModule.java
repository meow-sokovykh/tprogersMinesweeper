/*
 * @LwjglKeyboardHandleModule.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */


package ru.tproger.keyboard.lwjglmodule;

import org.lwjgl.input.Keyboard;
import ru.tproger.keyboard.KeyboardHandleModule;

/**
 * Реализует считывание необходимых игре параметров с клавиатуры средствами LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglKeyboardHandleModule implements KeyboardHandleModule {

    /* Данные о вводе за последнюю итераци. */
    private boolean wasEscPressed;

    /**
     * Считывание последних данных из стека событий
     */
    @Override
    public void update() {
        resetValues();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                }
            }
        }
    }

    /**
     * Обнуление данных, полученых в при предыдущих запросах
     */
    private void resetValues() {
        wasEscPressed = false;
    }

    /**
     * @return Возвращает информацию о том, был ли нажат ESCAPE за последнюю итерацию
     */
    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }


}

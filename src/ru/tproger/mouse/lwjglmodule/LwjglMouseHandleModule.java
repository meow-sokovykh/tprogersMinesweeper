/*
 * @LwjglMouseHandleModule.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.mouse.lwjglmodule;

import org.lwjgl.input.Mouse;
import ru.tproger.mouse.Click;
import ru.tproger.mouse.MouseHandleModule;

import java.util.LinkedList;

import static ru.tproger.main.Constants.CELL_SIZE;

/**
 * Реализует считывание с мыши необходимых игре параметров
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglMouseHandleModule implements MouseHandleModule {

    LinkedList<Click> stack;


    /**
     * Считывание последних данных из стека событий
     */
    @Override
    public void update() {
        resetValues();

        while(Mouse.next()){
            ///Если это было нажатие кнопки мыши, а не
            ///перемещение...
            if(Mouse.getEventButton()>=0 && Mouse.getEventButtonState()){
                int x = Mouse.getEventX()/CELL_SIZE;
                int y = Mouse.getEventY()/CELL_SIZE;
                int button = Mouse.getEventButton();

                stack.add(new Click(x, y, button));
            }
        }
    }

    /**
     * Обнуление данных, полученых в при предыдущих запросах
     */
    private void resetValues(){
        stack = new LinkedList<>();
    }

    /**
     * @return Возвращает стек кликов, произешдших за последнюю итерацию.
     */
    @Override
    public LinkedList<Click> getClicksStack() {
        return stack;
    }
}

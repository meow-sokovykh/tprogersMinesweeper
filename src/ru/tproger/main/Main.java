/*
 * @Main.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

import ru.tproger.graphics.GraphicsModule;
import ru.tproger.graphics.lwjglmodule.LwjglGraphicsModule;
import ru.tproger.keyboard.KeyboardHandleModule;
import ru.tproger.keyboard.lwjglmodule.LwjglKeyboardHandleModule;
import ru.tproger.mouse.Click;
import ru.tproger.mouse.MouseHandleModule;
import ru.tproger.mouse.lwjglmodule.LwjglMouseHandleModule;

import java.util.LinkedList;

import static ru.tproger.main.Constants.COUNT_CELLS_X;
import static ru.tproger.main.Constants.COUNT_CELLS_Y;

/**
 *
 * Главный управляющий класс.
 * Считывает действия пользователя, передаёт данные в graphicsModule, затем обрабатывает результат.
 *
 * @version 1.0
 * @author DoKel
 *
 */

public class Main {

    private static boolean endOfGame; //Флаг для завершения основного цикла программы
    private static GraphicsModule graphicsModule;
    private static KeyboardHandleModule keyboardModule;
    private static MouseHandleModule mouseModule;
    private static LinkedList<Click> clicksStack;
    private static GameField gameField;

    /**
     * Точка входа. Содержит все необходимые действия для одного игрового цикла.
     */
    public static void main(String[] args) {
        initFields();

        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
        }

        graphicsModule.destroy();
    }

    /**
     * Задаёт значения полей для начала игры
     */
    private static void initFields() {
        endOfGame = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        mouseModule = new LwjglMouseHandleModule();
        gameField = new GameField();
        clicksStack = new LinkedList<>();
    }


    /**
     * Считывает пользовательский ввод.
     */
    private static void input() {
        keyboardModule.update();
        mouseModule.update();

        clicksStack = mouseModule.getClicksStack();

        endOfGame = endOfGame || graphicsModule.isCloseRequested() || keyboardModule.wasEscPressed();
    }

    /**
     * Основная логика игры.
     *
     */
    private static void logic() {
        for(Click click : clicksStack) {
            ClickResult clickResult = gameField.recieveClick(click);
            switch(clickResult) {
                case EXPLOSED:
                    gameField.showAll();
                    break;
                case OPENED:
                    if(gameField.getMinesNear(click.x, click.y) == 0) {
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if ((click.x + i >= 0) && (click.x + i < COUNT_CELLS_X)
                                        && (click.y + j >= 0) && (click.y + j < COUNT_CELLS_Y)) {
                                    gameField.show(click.x + i, click.y + j);
                                }
                            }
                        }
                    }
                    break;
                case REGULAR:
                default:
                    //ignore
                    break;
            }
        }
    }

}

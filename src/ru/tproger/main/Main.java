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
           gameField.recieveClick(click);
        }
    }

}

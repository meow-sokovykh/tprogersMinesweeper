/*
 * @MouseHandleModule.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */

package ru.tproger.mouse;

import java.util.LinkedList;

/**
 * Определяет параметры, которые игре необходимо считывать с мыши.
 *
 * @author DoKel
 * @version 1.0
 */
public interface MouseHandleModule {

    /**
     * Считывание последних данных из стека событий, если можулю это необходимо
     */
    void update();

    /**
     * @return Возвращает информацию о кликах пользователя за последнюю итерацию
     */
    LinkedList<Click> getClicksStack();
}

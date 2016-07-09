/*
 * @CellState.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.main;

/**
 * Содержит все возможные состояния клетки
 *
 * @author DoKel
 * @version 1.0
 */
public enum CellState {
    EMPTY, /* В клетке нет мины */
    MINE, /* В клетке есть мина, но она не взорвана */
    EXPLOSED; /* В клетке есть мина и она взорвана*/
}

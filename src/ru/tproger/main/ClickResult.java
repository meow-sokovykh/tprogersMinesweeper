/*
 * @ClickResult.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.main;

/**
 * Определяет все возможные результаты клика по клетке
 *
 * @author DoKel
 * @version 1.0
 */
public enum ClickResult {
    REGULAR, /* Ничего специфического не произшло */
    OPENED, /* Клетка была открыта, но в ней не оказалось мины */
    EXPLOSED; /* Клетка была открыта и в ней оказалась мина */
}

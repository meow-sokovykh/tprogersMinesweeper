/*
 * @Constants.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

/**
 * Хранит ключевые для игры константы в public static final полях
 *
 * @author DoKel
 * @version 1.0
 */
public class Constants {

	/* Размер одной плитки */
	public static final int CELL_SIZE = 32;

	/* Количество ячеек на экране по горизонтали и вертикали */
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 10;

	/* Параметры окна */
    public static final int SCREEN_WIDTH = COUNT_CELLS_X *CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELLS_Y *CELL_SIZE;
    public static final String SCREEN_NAME = "Tproger's Minesweeper";

	/* Шанс спавна мины */
    public static final int SPAWN_CHANCE_OF_MINE = 15; //%
}

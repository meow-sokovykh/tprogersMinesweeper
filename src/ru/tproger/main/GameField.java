/*
 * @GameField.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.main;

import ru.tproger.mouse.Click;

import java.util.Random;

import static ru.tproger.main.Constants.COUNT_CELLS_X;
import static ru.tproger.main.Constants.COUNT_CELLS_Y;
import static ru.tproger.main.Constants.SPAWN_CHANCE_OF_MINE;

/**
 * Хранит и изменяет состояние игрового поля
 *
 * @author DoKel
 * @version 1.0
 */
public class GameField {

    /** Матрица, хранящая клетки поля */
    private Cell[][] theField;

    /** Матрица, хранящая для каждой клетки поля количество мин рядом с ней */
    private short[][] minesNear;

    /**
     * Конструктор инициализиурет поля класса, затем
     * создаёт на поле мины в случайном порядке и заполняет minesNear.
     */
    public GameField(){
        theField = new Cell[COUNT_CELLS_X][COUNT_CELLS_Y];
        minesNear = new short[COUNT_CELLS_X][COUNT_CELLS_Y];

        Random rnd = new Random();

        for(int x = 0; x < COUNT_CELLS_X; x++){
            for(int y = 0; y < COUNT_CELLS_Y; y++){
                boolean isMine = rnd.nextInt(100) < SPAWN_CHANCE_OF_MINE;

                if(isMine){
                    theField[x][y] = new Cell(true);

                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            if( (x+i >= 0) && (x+i < COUNT_CELLS_X) && (y+j >= 0) && (y+j < COUNT_CELLS_Y)){
                               minesNear[x+i][y+j]++;
                            }
                        }
                    }
                }else{
                    theField[x][y] = new Cell(false);
                }
            }
        }
    }

    /**
     * @param x Координата X клетки
     * @param y Координата Y клетки
     * @return Клетка поля с координатами (X, Y)
     */
    public Cell getCell(int x, int y) {
        return theField[x][y];
    }

    /**
     * @param x Координата X клетки
     * @param y Координата Y клетки
     * @return Количество мин рядом с клеткой с координатами (X, Y)
     */
    public int getMinesNear(int x, int y) {
        return minesNear[x][y];
    }

    /**
     * Метод предназначен для обработки кликов по полю.
     *
     * @param click Информация о переданном клике
     * @return Возвращает результат клика (Если ничего важного не произошло -- ClickResult.REGULAR)
     */
    public void recieveClick(Click click) {
        ClickResult clickResult = this.theField[click.x][click.y].recieveClick(click.button);
        switch(clickResult) {
            case EXPLOSED:
                showAll();
                break;
            case OPENED:
                if(getMinesNear(click.x, click.y) == 0) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((click.x + i >= 0) && (click.x + i < COUNT_CELLS_X)
                                    && (click.y + j >= 0) && (click.y + j < COUNT_CELLS_Y)) {
                                Click pseudoClick = new Click(click.x + i, click.y + j, click.button);
                                recieveClick(pseudoClick);
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

    /**
     * Делает содержимое клетки с координатами (X, Y) видимым
     *
     * @param x Координата X клетки
     * @param y Координата Y клетки
     */
    public void show(int x, int y){
        theField[x][y].show();
    }

    /**
     * Делает видимым содержимое всех клеток поля
     */
    public void showAll() {
        for(Cell[] row : theField){
            for(Cell cell : row){
                cell.show();
            }
        }
    }
}

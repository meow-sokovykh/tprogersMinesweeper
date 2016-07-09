/*
 * @Click.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.mouse;

/**
 * Содержит данные о клике -- координаты X и Y нажатия и кнопка, которой оно было сделано.
 *
 * Внимание! Каждому элементу следует передавать координаты ОТНОСИТЕЛЬНО НЕГО САМОГО.
 * Т.е. если элемент находится в окне в точке (5,5), а клик был совершён в точку (7,7),
 * то элементу следует передать Click с координатами (2, 2)!
 *
 * @author DoKel
 * @version 1.0
 */
public class Click {
    public int x, y, button;

    public Click(int x, int y, int button) {
        this.x = x;
        this.y = y;
        this.button = button;
    }
}

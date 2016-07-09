/*
 * @LwjglGraphicsModule.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.graphics.lwjglmodule;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import ru.tproger.graphics.GraphicsModule;
import ru.tproger.main.*;

import static org.lwjgl.opengl.GL11.*;
import static ru.tproger.main.Constants.*;

/**
 * Реализует графический модуль игры на основе LWJGL.
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglGraphicsModule implements GraphicsModule {

    private LwjglSpriteSystem spriteSystem;

    /**
     * Инициализирует графический движок и необходимые поля модуля.
     */
    public LwjglGraphicsModule() {
        initOpengl();
        spriteSystem = new LwjglSpriteSystem();
    }

    private void initOpengl() {
        try {
            /* Задаём размер будущего окна */
            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

            /* Задаём имя будущего окна */
            Display.setTitle(Constants.SCREEN_NAME);

            /* Создаём окно */
            Display.create();
        } catch (LWJGLException e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH,0, Constants.SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);

		/* Для поддержки текстур */
        glEnable(GL_TEXTURE_2D);

		/* Для поддержки прозрачности */
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		/* Белый фоновый цвет */
        glClearColor(1,1,1,1);
    }

    /**
     * Отрисовывает переданное игровое поле
     *
     * @param field Игровое поле, которое необходимо отрисовать
     */
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int i = 0; i < COUNT_CELLS_X; i++) {
            for (int j = 0; j < COUNT_CELLS_Y; j++) {
                drawSprite(CELL_SIZE*i, CELL_SIZE*j, calculateSprite(field.getCell(i,j), field.getMinesNear(i,j)) );
            }
        }

        Display.update();
        Display.sync(60);
    }

    private LwjglSpriteSystem.LwjglSprite calculateSprite(Cell cell, int minesNear) {
        if(cell.isMarked()){
            if(!cell.isHiden() && (cell.getState() != CellState.MINE)){
                ///Если эта клетка не скрыта, и на ней
                ///ошибочно стоит флажок...
                return LwjglSpriteSystem.LwjglSprite.BROKEN_FLAG;
            }
            ///В другом случае --
            return LwjglSpriteSystem.LwjglSprite.FLAG;
        }else if(cell.isHiden()){
            ///Если клетка не помечена, притом скрыта...
            return LwjglSpriteSystem.LwjglSprite.SPACE;
        }else{
            ///Если не помечена и не скрыта, выводим как есть
            switch (cell.getState()){
                case EXPLOSED:
                    return LwjglSpriteSystem.LwjglSprite.EXPLOSION;
                case MINE:
                    return LwjglSpriteSystem.LwjglSprite.MINE;
                case EMPTY:
                default:
                    if(minesNear>8 || minesNear<0){
                        ErrorCatcher.cantDisplayCellWrongMinesNear();
                    }

                    return  LwjglSpriteSystem.spriteByNumber[minesNear];
            }
        }
    }


    /**
     * Отрисовывает отдельную ячейку
     *
     * @param x Координата отрисовки X
     * @param y Координата отрисовки Y
     * @param sprite Текстура, которую надо отрисовывать
     */
    private void drawSprite(int x, int y, LwjglSpriteSystem.LwjglSprite sprite) {
        sprite.getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }

    /**
     * @return Возвращает true, если в окне нажат "крестик"
     */
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    /**
     * Заключительные действия.
     * Принудительно уничтожает окно.
     */
    @Override
    public void destroy() {
        Display.destroy();
    }
}

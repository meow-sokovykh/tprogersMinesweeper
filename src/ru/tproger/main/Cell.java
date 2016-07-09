/*
 * @Cell.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.main;

/**
 * Хранит информацию о клетке и обрабатывает клики по ней
 *
 * @author DoKel
 * @version 1.0
 */
public class Cell {

    /** Состояние клетки (пустая клетка / клетка с миной / взорванная клетка) */
    private CellState state;

    /** Указывает на то, открыта ли клетка пользователем */
    private boolean isHiden;

    /** Указывает на то, была ли клетка отмечена пользователем (поставил ли он на неё флажок) */
    private boolean isMarked;

    /**
     * Инициализирует поля класса
     *
     * @param isMine Является ли клетка миной
     */
    public Cell(boolean isMine){
        this.isHiden = true;
        this.isMarked = false;
        this.state = isMine ? CellState.MINE : CellState.EMPTY;
    }

    /**
     * Метод предназначен для обработки кликов по полю.
     *
     * @param button Кнопка, которой был сделан клик
     * @return Возвращает результат клика (Если ничего важного не произошло -- ClickResult.REGULAR)
     */
    public ClickResult recieveClick(int button) {
        if(isHiden){ ///Нет смысла обрабатывать клики по уже открытым полям

            /* Если клик был сделан левой кнопкой */
            if(button==0 && !this.isMarked){ ///Заметим: щелчёк левой кнопкой по флагу не даст никакого результата

                /* Если это была мина, меняем состояние на взорванную и передаём сигнал назад */
                if(this.state == CellState.MINE){
                    this.state = CellState.EXPLOSED;
                    return ClickResult.EXPLOSED;
                }

                /* Иначе открываем клетку и передаём сигнал об открытии назад */
                if(this.state == CellState.EMPTY){
                    this.isHiden = false;
                    return ClickResult.OPENED;
                }

            /* Если клик был сделан правой кнопкой */
            }else if(button==1){
                this.isMarked = ! this.isMarked;
            }
        }

        return ClickResult.REGULAR;
    }

    /**
     * @return Возвращает информацию о том, была ли клетка открыта пользователем
     */
    public boolean isHiden(){
        return isHiden;
    }

    /**
     * @return Возвращает информацию о том, была ли клетка помечена пользователем (поставил ли он на неё флажок)
     */
    public boolean isMarked(){
        return isMarked;
    }

    /**
     * @return Возвращает сотостояние клетки (пустая клетка / клетка с миной / взорванная клетка)
     */
    public CellState getState() {
        return state;
    }

    /**
     * Открывает содержимое клетки
     */
    public void show() {
        this.isHiden = false;
    }
}

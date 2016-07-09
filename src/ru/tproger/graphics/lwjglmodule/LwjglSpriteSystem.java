/*
 * @LwjglSpriteSystem.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */

package ru.tproger.graphics.lwjglmodule;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Система загрузки и хранения спрайтов для LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
class LwjglSpriteSystem {

    /**
	 * Загружает и хранит все доступные в игре текстуры
     */
	enum LwjglSprite {

		ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
		SEVEN("7"), EIGHT("8"), SPACE("space"), MINE("mine"), FLAG("flag"), BROKEN_FLAG("broken_flag"),
		EXPLOSION("explosion");

		private Texture texture;

		LwjglSprite(String texturename) {
			try {
				this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + texturename + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public Texture getTexture() {
			return this.texture;
		}
	}

    /**
     * Хранит в себе ссылки на все доступные в игре текстуры с ключом, равным изображённой на текстуре цифре
     */
	public static final LwjglSprite[] spriteByNumber = {
		LwjglSprite.ZERO,
		LwjglSprite.ONE,
		LwjglSprite.TWO,
		LwjglSprite.THREE,
		LwjglSprite.FOUR,
		LwjglSprite.FIVE,
		LwjglSprite.SIX,
		LwjglSprite.SEVEN,
		LwjglSprite.EIGHT
	};

}

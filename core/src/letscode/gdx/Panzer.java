package letscode.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

//класс - главного героя - танка
public class Panzer {
    //размер в пикселях
    private final float size = 64;
    private final float halfSize = size / 2;

    //создаем расположение танка
    private final Vector2 position = new Vector2();
    private final Vector2 angle = new Vector2();

    private final Texture texture;
    //для метода draw
    private final TextureRegion textureRegion;

    //передача текстуры танка + init vector
    public Panzer(float x, float y) {
        this(x, y, "panzer_me.png");
    }
    //передача текстуры ВРАЖИНЫ!!! + init vector
    public Panzer(float x, float y, String textureName) {
        texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        position.set(x, y);
    }

    //рендер танка,наклона пушки
    public void render(Batch batch) {
        batch.draw(
                textureRegion,
                position.x,
                position.y,
                halfSize,
                halfSize,
                size,
                size,
                1,
                1,
                //угол в градусах(-90, т.к. работал некорректно(картинка дулом вверх а надо вправо))
                angle.angleDeg() - 90
        );
    }

    //cleen memory
    public void dispose() {
        texture.dispose();
    }

    //передвижение танка
    public void moveTo(Vector2 direction) {
        position.add(direction);
    }

    //для движения башни танка в нужную сторону с помощью мышки
    public void rotateTo(Vector2 mousePos) {
        //какие-то хитрые махинации...
        //коррекция точки вращения т.к. изначальная от тексуры снизу слева
        angle.set(mousePos).sub(position.x + halfSize, position.y + halfSize);
    }

    //гетер для позиции танка
    public Vector2 getPosition() {
        return position;
    }
}
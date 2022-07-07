package letscode.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
//класс для инфы от клавиатуры
//InputAd - класс для получения инфы от клавиатуры/мыши от lib.gdx
public class KeyboardAdapter extends InputAdapter {
    //булевы для информации о том, нажата ли клавиша
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    //создане векторов позиции мышки и направления движения
    private final Vector2 mousePos = new Vector2();
    private final Vector2 direction = new Vector2();

    //keycode - автоматически получается от кликов по клаве
    //указывается что нажато
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) leftPressed = true;
        if (keycode == Input.Keys.D) rightPressed = true;
        if (keycode == Input.Keys.W) upPressed = true;
        if (keycode == Input.Keys.S) downPressed = true;

        return false;
    }

    //false при отпускании клавиши
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) leftPressed = false;
        if (keycode == Input.Keys.D) rightPressed = false;
        if (keycode == Input.Keys.W) upPressed = false;
        if (keycode == Input.Keys.S) downPressed = false;

        return false;
    }

    //установление позиции мышки
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //коррекция точки по y для корректного вращения башни
        mousePos.set(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }

    //метод возвращает вектор в зависимости от нажатых клавиш
    public Vector2 getDirection() {
        //обнуление вектора
        direction.set(0, 0);

        if (leftPressed) direction.add(-5, 0);
        if (rightPressed) direction.add(5, 0);
        if (upPressed) direction.add(0, 5);
        if (downPressed) direction.add(0, -5);

        return direction;
    }


    //гетер для позиции мышки
    public Vector2 getMousePos() {
        return mousePos;
    }
}
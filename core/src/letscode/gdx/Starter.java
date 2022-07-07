package letscode.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//AppAdapter - базовая обертка дял всех lib.gdx игр предоставляет 3 этих метода
public class Starter extends ApplicationAdapter {
	//холст от lib.gdx
	SpriteBatch batch;

	//созданем объект класса танк
	private Panzer me;
	//лист противников
	private final List<Panzer> enemies = new ArrayList<>();

	//init обработчика нажатий
	private KeyboardAdapter inputProcessor = new KeyboardAdapter();

	//init
	@Override
	public void create () {
		//регистрация обработчика нажатий
		Gdx.input.setInputProcessor(inputProcessor);
		batch = new SpriteBatch();
		//init танк
		me = new Panzer(100, 200);

		//заполнение листа ВРАЖИН рандомными значениями
		List<Panzer> newEnemies = IntStream.range(0, 15)
				//преобразование в Object из int
				.mapToObj(i -> {
					int x = MathUtils.random(Gdx.graphics.getWidth());
					int y = MathUtils.random(Gdx.graphics.getHeight());
					//init ВРАЖИН
					return new Panzer(x, y, "panzer_enemy.png");
				})
				.collect(Collectors.toList());
		enemies.addAll(newEnemies);
	}

	//вызывается каждый кадр
	@Override
	public void render () {
		//движение танка, башни
		me.moveTo(inputProcessor.getDirection());
		me.rotateTo(inputProcessor.getMousePos());
		//цыет фона
		ScreenUtils.clear(1, 1, 1, 1);
		//начало рендера
		batch.begin();
		//рендерим танк(в аргументах холст от lib.gdx)
		me.render(batch);
		//рендерим ВРАЖИН и настраиваем движение их пушек за нами
		enemies.forEach(enemy -> {
			enemy.render(batch);
			enemy.rotateTo(me.getPosition());
		});
		//конец рендера
		batch.end();
	}

	//при завершении процеса(для освобождения ресурсов)
	@Override
	public void dispose () {
		batch.dispose();
		me.dispose();
	}
}
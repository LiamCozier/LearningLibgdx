package io.github.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer sr;

    private Ball ball;
    private Paddle paddle;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        sr = new ShapeRenderer();

        ball = new Ball(new int[] {75, 10}, new int[] {7,7}, new int[] {20, 20} ,Color.WHITE);
        paddle = new Paddle(new int[] {265, 25}, new int[] {0,0}, new int[] {70,20} ,Color.WHITE);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        paddle.take_input(Input.Keys.LEFT, Input.Keys.RIGHT);
        paddle.collide(ball);

        paddle.move();
        paddle.color = Color.WHITE;
        if (paddle.is_intersecting_with(ball)) {
            paddle.color = Color.RED;
        }
        ball.move();


        sr.begin(ShapeRenderer.ShapeType.Filled);
            ball.draw(sr);
            paddle.draw(sr);
        sr.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

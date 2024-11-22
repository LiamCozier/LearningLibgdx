package io.github.pong;

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
    private Paddle paddle1;
    private Paddle paddle2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        sr = new ShapeRenderer();

        ball = new Ball(new int[] {75, 290}, new int[] {7,7}, new int[] {20, 20} ,Color.WHITE);
        paddle1 = new Paddle(new int[] {25, 265}, new int[] {0,0}, new int[] {20,70} ,Color.WHITE);
        paddle2 = new Paddle(new int[] {755, 265}, new int[] {0,0}, new int[] {20,70} ,Color.WHITE);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        paddle1.take_input(Input.Keys.W, Input.Keys.S);
        paddle2.take_input(Input.Keys.UP, Input.Keys.DOWN);

        paddle1.move();
        paddle2.move();
        paddle1.collide(ball);
        paddle2.collide(ball);
        int collision_side = ball.move();
        if (collision_side==0) {
            ball.velocity = new int[] {0, 0};
        }


        sr.begin(ShapeRenderer.ShapeType.Filled);
            ball.draw(sr);
            paddle1.draw(sr);
            paddle2.draw(sr);
        sr.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

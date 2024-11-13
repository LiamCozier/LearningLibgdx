package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Random r;
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer sr;
    private Ball[] balls = new Ball[2];

    @Override
    public void create() {
        r = new Random();

        int i;
        for (i=0; i<balls.length; i++) {
            int[] position = {r.nextInt(1200)+40, r.nextInt(700)+10};
            int[] velocity = {r.nextInt(7)+3,r.nextInt(7)+3};
            int[] dimensions = {20, 20};
            balls[i] = new Ball(position, velocity, dimensions);
        }

        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        sr = new ShapeRenderer();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //batch.begin();
        //batch.draw(image, 140, 210);
        //batch.end();


        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball b : balls) {
            b.move();
            sr.rect(b.position[0], b.position[1], b.dimensions[0], b.dimensions[1]);
        }
        sr.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

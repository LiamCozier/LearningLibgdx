package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Random r;
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer sr;
    private Ball[] balls = new Ball[10];

    @Override
    public void create() {
        r = new Random();

        int i;
        for (i=0; i<balls.length; i++) {
            int scale_factor = (r.nextInt(9)+1);

            int[] position = {r.nextInt(1200)+40, r.nextInt(700)+10};
            int[] velocity = {r.nextInt(1)+3,r.nextInt(1)+3};
            int[] dimensions = {10*scale_factor, 10*scale_factor};
            int mass = scale_factor*scale_factor;
            balls[i] = new Ball(position, velocity, dimensions, mass);
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

        ball_collision(balls);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball b : balls) {

            b.move();

            sr.setColor(b.color);
            sr.rect(b.position[0], b.position[1], b.dimensions[0], b.dimensions[1]);

        }
        sr.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public void ball_collision(Ball[] ball_array) {
        for (Ball b : ball_array) {
            for (Ball c: ball_array) {

                if (b.id == c.id) {
                    continue; //don't collide with self
                }

                if (!b.is_intersecting_with(c)) {
                    continue; //don't do anything if there are no collisions
                }



                int i;
                i = b.intersection_direction(c);

                int total_velocity = Math.abs(b.velocity[i])+Math.abs(c.velocity[i]);
                int total_mass = b.mass + c.mass;
                int new_b_sign = b.velocity[i]/Math.abs(b.velocity[i]) * -1;
                int new_c_sign = c.velocity[i]/Math.abs(c.velocity[i]) * -1;

                b.velocity[i] = ((total_velocity*c.mass)/total_mass) * new_b_sign;
                c.velocity[i] = ((total_velocity*b.mass)/total_mass) * new_c_sign;

                int shift = b.velocity[i];
                while (b.is_intersecting_with(c)) {
                    b.position[i] += shift;
                    shift *= -2;
                }
            }
        }
    }

}

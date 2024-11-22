package io.github.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    static int[] screen_dimensions = {Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};

    public int[] position;
    public int[] velocity;
    public int[] dimensions;
    public Color color;

    public Paddle(int[] position, int[] velocity, int[] dimensions, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.dimensions = dimensions;
        this.color = color;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(color);
        sr.rect(position[0], position[1], dimensions[0], dimensions[1]);
    }

    public void take_input(int up_key, int down_key) {
        velocity[1] = 0;
        if (Gdx.input.isKeyPressed(up_key)) {
            velocity[1] += 7;
        }
        if (Gdx.input.isKeyPressed(down_key)) {
            velocity[1] += -7;
        }
    }

    public void move() {
        for (int i=0; i<2; i++) {
            position[i] += velocity[i];

            position[i] = Math.clamp(position[i], 0, screen_dimensions[i]-dimensions[i]);
        }
    }

    public void collide(Ball b) {
        if (!is_intersecting_with(b)) {
            return;
        }

        int i = intersection_direction(b);

        b.velocity[i] *= -1;
        while (is_intersecting_with(b)) {
            b.position[i] += b.velocity[i];
        }
    }

    public boolean is_intersecting_with(Ball b) {
        boolean is_intersecting;

        int[] distance_vector = displacement_to(b);
        is_intersecting = (Math.abs(distance_vector[0]) < (float) dimensions[0]) && (Math.abs(distance_vector[1]) < (float) dimensions[1]);

        return is_intersecting;
    }

    public int[] displacement_to(Ball b) {
        int x_displacement, y_displacement;

        x_displacement = position[0]-b.position[0];
        y_displacement = position[1]-b.position[1];

        return new int[]{x_displacement, y_displacement};
    }

    public int intersection_direction(Ball b) {
        if (!is_intersecting_with(b)) {
            return -1;
        }

        for (int i=0; i<2; i++) {
            b.position[i] -= b.velocity[i];
            if (!is_intersecting_with(b)) {
                return i;
            }
            b.position[i] += b.velocity[i];
        }
        return -1;
    }


}


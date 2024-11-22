package io.github.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    static int[] screen_dimensions = {Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};

    public int[] position;
    public int[] velocity;
    public int[] dimensions;
    public Color color;

    public Ball(int[] position, int[] velocity, int[] dimensions, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.dimensions = dimensions;
        this.color = color;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(color);
        sr.rect(position[0], position[1], dimensions[0], dimensions[1]);
    }

    public int move() {
        for (int i = 0; i < 2; i++) {
            if (screen_dimensions[i] - dimensions[i] > position[i] && position[i] > 0) {
                position[i] += velocity[i];
            } else {
                position[i] -= velocity[i];
                velocity[i] *= -1;
                return i;
            }
        }
        return -1;
    }
}


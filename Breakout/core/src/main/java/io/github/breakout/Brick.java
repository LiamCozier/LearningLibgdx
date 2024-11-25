package io.github.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick {
    static int[] screen_dimensions = {Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};

    public int[] position;
    public int[] velocity;
    public int[] dimensions;
    public boolean is_active;
    public Color color;

    public Brick(int[] position, int[] velocity, int[] dimensions, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.dimensions = dimensions;
        this.color = color;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(color);
        sr.rect(position[0], position[1], dimensions[0], dimensions[1]);
    }

    public void collide(Ball b) {
        if (!is_intersecting_with(b)) {
            return;
        }

        int i = intersection_direction(b);
        if (i==-1) {
            return;
        }
        b.velocity[i] *= -1;
        while (is_intersecting_with(b)) {
            b.position[i] += b.velocity[i];
        }
    }

    public boolean is_intersecting_with(Ball b) {
        boolean is_intersecting = true;

        for (int i=0; i<2; i++) {
            int greaterPos, lesserPos, lesserDim;
            if (this.position[i]>b.position[i]) {
                greaterPos = this.position[i];
                lesserPos = b.position[i];
                lesserDim = b.dimensions[i];

            } else if (this.position[i]<b.position[i]) {
                greaterPos = b.position[i];
                lesserPos = this.position[i];
                lesserDim = this.dimensions[i];

            } else {
                return true;
            }

            int difference = greaterPos-lesserPos;

            if (!(difference < lesserDim)) {
                is_intersecting = false;
            }
        }
        return is_intersecting;
    }

    public int[] displacement_to(Ball b) {
        int x_displacement, y_displacement;

        x_displacement = position[0]-b.position[0];
        y_displacement = position[1]-b.position[1];
        System.out.printf("x: %d\n y: %d\n", x_displacement, y_displacement);
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


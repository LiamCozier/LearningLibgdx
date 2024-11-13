package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class Ball {
    static int[] screen_dimensions = {1280, 720};


    int[] position, velocity, dimensions;
    int id;

    Color color = new Color(1, 1, 1, 1);

    public Ball(int[] init_position, int[] init_velocity, int[] init_dimensions) {
        Random r = new Random();
        id = r.nextInt();

        position = init_position;
        velocity = init_velocity;
        dimensions = init_dimensions;

    }

    public void move() {
        int i;
        for (i=0; i<2; i++) {

            boolean is_out_of_bounds = (position[i]>screen_dimensions[i]-dimensions[i]) || (position[i]<0);
            if (is_out_of_bounds) {

                velocity[i] *= -1;
                while (is_out_of_bounds) {
                    is_out_of_bounds = (position[i]>screen_dimensions[i]-dimensions[i]) || (position[i]<0);
                    position[i] += velocity[i];
                }

            }
            position[i]+=velocity[i];
        }
    }

    public boolean is_intersecting_with(Ball b) {
        boolean is_intersecting;

        int[] distance_vector = displacement_to(b);
        is_intersecting = (Math.abs(distance_vector[0]) < (double) dimensions[0]) && (Math.abs(distance_vector[1]) < (double) dimensions[1]);

        return is_intersecting;
    }
    public int intersection_direction(Ball b) {
        int[] distance_vector = displacement_to(b);
        return (Math.abs(distance_vector[0]) > Math.abs(distance_vector[1]) ? 0 : 1);
    }

    public int[] displacement_to(Ball b) {
        int x_displacement, y_displacement;

        x_displacement = position[0]-b.position[0];
        y_displacement = position[1]-b.position[1];

        return new int[]{x_displacement, y_displacement};
    }


}

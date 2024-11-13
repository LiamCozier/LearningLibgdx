package io.github.some_example_name;

public class Ball {
    static int[] screen_dimensions = {1280, 720};

    int[] position, velocity, dimensions;

    public Ball(int[] init_position, int[] init_velocity, int[] init_dimensions) {
        position = init_position;
        velocity = init_velocity;
        dimensions = init_dimensions;
    }

    public void move() {
        int i;
        for (i=0; i<2; i++) {
            if ((position[i]>screen_dimensions[i]-dimensions[i]) || (position[i]<0)) {
                position[i] -= velocity[i];
                velocity[i] *= -1;
            }
            position[i]+=velocity[i];
        }
    }
}

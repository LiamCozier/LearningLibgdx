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

    public boolean is_intersecting_with(Ball b) {
        boolean is_intersecting = false;

        is_intersecting = ((double) dimensions[0] > distance_to(b)) || ((double) dimensions[1] > distance_to(b));

        return is_intersecting;
    }

    public double distance_to(Ball b) {
        double x_magnitude, y_magnitude, distance;

        x_magnitude = Math.abs(position[0]-b.position[0]);
        y_magnitude = Math.abs(position[1]-b.position[1]);
        distance = Math.sqrt(Math.pow(x_magnitude, 2) + Math.pow(y_magnitude, 2));
        //a^2 + b^2 = c^2

        return distance;
    }
}

import java.util.Random;

public class Ostrich implements Animals {
    int distance,time;
    public Ostrich() {

        this.distance = 60 + new Random().nextInt(20);
        this.time = 600 + new Random().nextInt(200);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int go(int length) {
        int i =0;
        int dis = 0;
        do {
            dis = dis +distance;
            i++;
        }while (dis<length);
        return time*i;

    }
}

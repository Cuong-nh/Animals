import java.util.Random;

public class Rabbit implements Animals {
    int distance, time;

    public Rabbit() {

        this.distance = 40 + new Random().nextInt(20);
        this.time = 400 + new Random().nextInt(200);
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

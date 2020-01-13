import javafx.scene.effect.Blend;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Ostrich implements Animals {
    int distance,time;
    int count;
    int totalTime ;

    public Ostrich(int length,int count) {

        this.distance = 60 + new Random().nextInt(5);
        this.time = 600 + new Random().nextInt(50);
        this.count = count;
        int i =0,dis = 0,t=0;
        do {
            dis = dis+distance;
            i++; t = time*i;
        }while (dis<length);
        this.totalTime = t;

    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void go(int length) {

        System.out.println("Đà điểu "+ count + " bắt đầu chạy " );
        int i =0,dis = 0,t=0;
        do {
            dis = dis+distance;
            i++;
            System.out.println("Đà điểu "+ count + " chạy được " + dis+ " cm");
            t = time*i;
        }while (dis<length);
        totalTime = t;

        System.out.println("Đà điểu "+count+" hoàn thành đường đua với thời gian " + this.getTotal()/1000+" giây.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getTotal() {
        return this.totalTime;
    }

    @Override
    public String getType() {
        return "Đà điểu";
    }
}

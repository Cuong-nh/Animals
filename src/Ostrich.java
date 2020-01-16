import javafx.scene.effect.Blend;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class Ostrich extends AbstractAnimals implements Animals,Runnable {
    int distance, time, count, totalTime, length,number;
    Runnable runnable;
    ExecutorService executor;


    public Ostrich(int length, int count,int number) {
        this.length = length;
        this.number = number;
        this.distance = 60 + new Random().nextInt(5);
        this.time = 600 + new Random().nextInt(50);
        this.count = count;
        int i = 0, dis = 0, t = 0;
        do {
            dis = dis + distance;
            i++;
            t = time * i;
        } while (dis < length);
        this.totalTime = t;

    }

    public int getCount() {
        return count;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int getTotal() {
        return this.totalTime;
    }

    @Override
    public String getType() {
        return "Đà điểu";
    }

    @Override
    public void setRunnable(Runnable runnable) {
        if(count<number){
            this.runnable = runnable;}
    }

    @Override
    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
    @Override
    public void run() {
        super.start(this.getType(), number,distance, time, length, count,runnable,executor);

    }
}

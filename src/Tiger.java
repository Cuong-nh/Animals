import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class Tiger extends AbstractAnimals implements Animals, Runnable {
    int distance, time, length, count, totalTime,number;
    Runnable runnable;
    ExecutorService executor;

    public Tiger(int length, int count,int number) {
        this.length = length;
        this.number = number;
        this.distance = 70 + new Random().nextInt(5);
        this.time = 700 + new Random().nextInt(50);
        this.count = count;
        int i = 0, dis = 0, t = 0;
        do {
            dis = dis + distance;
            i++;
            t = time * i;
        } while (dis < length);
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
    public int getTotal() {
        return this.totalTime;
    }

    @Override
    public String getType() {
        return "Hổ";
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





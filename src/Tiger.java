import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Tiger implements Animals {
    int distance,time;
    int count;
    int totalTime ;

    public Tiger(int length,int count) {

        this.distance = 70 + new Random().nextInt(5);
        this.time = 700 + new Random().nextInt(50);
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
        System.out.println("Hổ "+ count + " bắt đầu chạy " );
        int i =0,dis = 0,t=0;
        do {
            dis = dis+distance;
            i++;
            System.out.println("Hổ "+ count + " chạy được " + dis+ " cm");
            t = time*i;
        }while (dis<length);
        totalTime = t;

        System.out.println("Hổ "+count+" hoàn thành đường đua với thời gian " + this.getTotal()/1000 + " giây.");


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
        return "Hổ";
    }
}





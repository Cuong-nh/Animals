import java.util.concurrent.ExecutorService;

public abstract class AbstractAnimals {
    void start(String type, int number,int distance, int time, int length, int count, Runnable runnable, ExecutorService executor) {
        System.out.println(type + " " + count + " bắt đầu chạy ");
        int i = 1, dis = 0, t = 0;
        do {
            dis = dis + distance;
            i++;
            t = time * i;

        } while (dis < length);

        if (count < number+1) {
            try {
                System.out.println(type + " " + count + " hoàn thành đường đua với thời gian " + t/ 1000 + " giây.");
                executor.execute(runnable);
            } catch (Exception e) {

            }

        }
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public interface  Animals  {
    //Phương thức tính ra thời gian mỗi con hoàn thành đường đua có chiều dài truyền vào là length
    int getTotal();
    String getType();
    void setRunnable(Runnable runnable);
    void setExecutor(ExecutorService executor);


}

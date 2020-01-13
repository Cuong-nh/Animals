import java.util.concurrent.atomic.AtomicInteger;

public interface  Animals {
    //Phương thức tính ra thời gian mỗi con hoàn thành đường đua có chiều dài truyền vào là length

    void go(int length);
    int getTotal();
    String getType();


}

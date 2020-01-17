
import java.util.*;
import java.util.concurrent.*;


public class Main {
    public static final int NUMBER = 4, LENGTH = 7000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Race race = new RaceConcreteBuilder().setLength(LENGTH).setNumber(NUMBER).build();
        race.play();
    }
}








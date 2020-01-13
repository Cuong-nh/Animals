
import java.util.*;
import java.util.concurrent.*;


public class Main {
    public static final int NUMBER = 3, LENGTH = 5000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Animals> animalslist = new ArrayList<>();
        String[] strings = {"Cánh cụt", "Thỏ", "Đà điểu", "Hổ"};
        // tạo list animals gồm 12 con, mỗi loài 3 con
        for (String s : strings) {
            for (int i = 0; i < NUMBER; i++) {
                Animals animals = FactoryAnimal.createAnimals(s, LENGTH, i + 1);
                animalslist.add(animals);
            }
        }
        // Khởi tạo đường đua với 3 tham số : list , chiều dài đường đua , số lượng mỗi loài
        RaceBuilder raceBuilder = new RaceConcreteBuilder().setListAnimal(animalslist).setLength(LENGTH).setNumber(NUMBER);
        Race race = raceBuilder.build();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Map<String, LinkedList<Runnable>> mapAnimals = new HashMap<>();
        for (int i = 0; i < animalslist.size(); i++) {
            Animals animals = animalslist.get(i);
            if (!mapAnimals.containsKey(animals.getType())) {
                LinkedList<Runnable> linkedList = new LinkedList<Runnable>();
                linkedList.add(new Runnable() {
                    @Override
                    public void run() {
                        animals.go(race.getLength());
                    }
                });
                mapAnimals.put(animals.getType(), linkedList);
            } else {
                mapAnimals.get(animals.getType()).add(new Runnable() {
                    @Override
                    public void run() {
                        animals.go(race.getLength());
                    }
                });
            }

        }
        for (int i = 0; i < 3; i++) {
            for (String s : mapAnimals.keySet()) {
                LinkedList<Runnable> linkedList = mapAnimals.get(s);
                executorService.execute(linkedList.get(i));
            }
        }
        executorService.shutdown();
    }

}









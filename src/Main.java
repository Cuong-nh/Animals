
import java.util.*;
import java.util.concurrent.*;


public class Main {
    public static final int NUMBER = 3, LENGTH = 5000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String, LinkedList<Animals>> mapAnimals = new HashMap<>();
        Map<String, List<Future<Integer>>> mapResult = new HashMap<>();
        for (int i = 0; i < animalslist.size(); i++) {
            Animals animals = animalslist.get(i);
            if (!mapAnimals.containsKey(animals.getType())) {
                LinkedList<Animals> linkedList = new LinkedList<>();
                linkedList.add(animals);
                mapAnimals.put(animals.getType(), linkedList);
            } else {
                mapAnimals.get(animals.getType()).add(animals);
            }


        }
        for (int i = 0; i < 3; i++) {
            for (String s : mapAnimals.keySet()) {
                Animals animals = mapAnimals.get(s).get(i);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        animals.go(race.getLength());
                    }
                };
                executorService.execute(runnable);
                Future<String> stringFuture = executorService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return animals.getType();
                    }
                });
                Future<Integer> integerFuture = executorService.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return animals.getTotal();
                    }
                });
                if (!mapResult.containsKey(stringFuture.get())) {
                    List<Future<Integer>> futureList = new ArrayList<>();
                    futureList.add(integerFuture);
                    mapResult.put(stringFuture.get(), futureList);

                } else {
                    mapResult.get(stringFuture.get()).add(integerFuture);
                }
            }
        }
        executorService.shutdown();
        String winner = "";
        String loser = "";
        int min = 300000;
        int max = 0;
        for (String s : mapResult.keySet()) {
            if(getTotal(mapResult.get(s))<min){
                min = getTotal(mapResult.get(s));
                winner = s;
            }
            if(getTotal(mapResult.get(s))>max){
                max = getTotal(mapResult.get(s));
                loser = s;
            }

            System.out.println("Thành tích của " + s + " là: " + getTotal(mapResult.get(s)) / 1000 + " giây.");
        }

        System.out.println("Kẻ chiến thắng là: "+winner);
        System.out.println("Kẻ thua cuộc là: "+loser);

    }

    public static int getTotal(List<Future<Integer>> list) throws ExecutionException, InterruptedException {
        int total = 0;
        for (Future<Integer> future : list) {
            total = total + future.get();
        }
        return total;
    }

}









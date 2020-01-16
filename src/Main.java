
import java.util.*;
import java.util.concurrent.*;


public class Main {
    public static final int NUMBER = 3, LENGTH = 5000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Tạo list animals gồm 4 loài, mỗi loài có NUMBER con:
        List<Animals> animalslist = createListAnimals();

        Map<String, LinkedList<Animals>> mapAnimals = createMapAnimal(animalslist);
        Map<String, List<Future<Integer>>> mapResult = new HashMap<>();

        // Khởi tạo đường đua với 3 tham số : list , chiều dài đường đua , số lượng mỗi loài
        Race race = new RaceConcreteBuilder().setListAnimal(animalslist).setLength(LENGTH).setNumber(NUMBER).build();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < NUMBER-1; i++) {
            for (String s : mapAnimals.keySet()) {
                Animals animals = mapAnimals.get(s).get(i);
                animals.setRunnable((Runnable) mapAnimals.get(s).get(i+1));
                mapAnimals.get(s).get(i).setExecutor(executorService);}}

        for (String s : mapAnimals.keySet()){
            executorService.execute((Runnable) mapAnimals.get(s).get(0));
            for(int i =0;i<NUMBER;i++){
                Animals animals = mapAnimals.get(s).get(i);
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
                addMapResult(mapResult, stringFuture, integerFuture);
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(3,TimeUnit.SECONDS);
        // hiển thị kết quả:
        showResult(mapResult);
    }

    public static int getTotal(List<Future<Integer>> list) throws ExecutionException, InterruptedException {
        int total = 0;
        for (Future<Integer> future : list) {
            total = total + future.get();
        }
        return total;
    }

    public static List<Animals> createListAnimals() {
        List<Animals> animals = new ArrayList<>();
        String[] strings = {"Cánh cụt", "Thỏ", "Đà điểu", "Hổ"};
        // tạo list animals gồm 12 con, mỗi loài 3 con
        for (String s : strings) {
            for (int i = 0; i < NUMBER; i++) {
                Animals animal = FactoryAnimal.createAnimals(s, LENGTH, i + 1,NUMBER);
                animals.add(animal);
            }
        }
        return animals;
    }

    public static Map<String, LinkedList<Animals>>  createMapAnimal(List<Animals> animalslist) {
        Map<String, LinkedList<Animals>> mapAnimals =new HashMap<>();
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
        return mapAnimals;

    }

    public static void addMapResult(Map<String, List<Future<Integer>>> mapResult, Future<String> stringFuture, Future<Integer> integerFuture) throws ExecutionException, InterruptedException {
        if (!mapResult.containsKey(stringFuture.get())) {
            List<Future<Integer>> futureList = new ArrayList<>();
            futureList.add(integerFuture);
            mapResult.put(stringFuture.get(), futureList);

        } else {
            mapResult.get(stringFuture.get()).add(integerFuture);
        }
    }

    public static void showResult(Map<String, List<Future<Integer>>> mapResult) throws ExecutionException, InterruptedException {
        String winner = "",loser = "";
        int min = getTotal(mapResult.get("Hổ")) + 1,max =0;
        for (String s : mapResult.keySet()) {
            if (getTotal(mapResult.get(s)) < min) {
                min = getTotal(mapResult.get(s));
                winner = s;
            }
            if (getTotal(mapResult.get(s)) > max) {
                max = getTotal(mapResult.get(s));
                loser = s;
            }
            System.out.println("Thành tích của " + s + " là: " + getTotal(mapResult.get(s)) / 1000 + " giây.");
        }
        System.out.println("Kẻ chiến thắng là: " + winner);
        System.out.println("Kẻ thua cuộc là: " + loser);
    }


}









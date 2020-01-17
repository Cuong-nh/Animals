import java.util.*;
import java.util.concurrent.*;

public class Race {
    int numberOfAnimal;
    int length;
    public static final int NUMBER = 4, LENGTH = 7000;

    public Race( int numberOfAnimal, int length) {
        this.numberOfAnimal = numberOfAnimal;
        this.length = length;
    }


    public int getNumberOfAnimal() {
        return numberOfAnimal;
    }

    public int getLength() {
        return length;
    }
    public void play() throws ExecutionException, InterruptedException {
        List<Animals> animalslist = createListAnimals();

        Map<String, LinkedList<Animals>> mapAnimals = createMapAnimal(animalslist);
        Map<String, List<Integer>> mapResult = new HashMap<>();
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
                String name = animals.getType();
               int result = animals.getTotal();
                addMapResult(mapResult, name, result);
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        showResult(mapResult);
    }
    public static int getTotal(List<Integer> list) {
        int total = 0;
        for (int i : list) {
            total = total + i;
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

    public static void addMapResult(Map<String, List<Integer>> mapResult,String name,int result) {
        if (!mapResult.containsKey(name)) {
            List<Integer> list = new ArrayList<>();
            list.add(result);
            mapResult.put(name, list);

        } else {
            mapResult.get(name).add(result);
        }
    }

    public static void showResult(Map<String, List<Integer>> mapResult)  {
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

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Animals>> animalslist = new ArrayList<>();
        String[] strings = {"Penguin", "Rabbit", "Ostrich", "Tiger"};
        // tạo 4 list, mỗi list gồm 4 con cùng loài để add vào animalslist
        for (String s : strings) {
            List<Animals> list = new ArrayList<>();
            for (int i = 0; i <4; i++) {
                list.add(new FactoryAnimal().createAnimals(s));

            }
            animalslist.add(list);
        }
        // Khởi tạo đường đua với 3 tham sô : list , chiều dài đường đua , số lượng mỗi loài
        RaceBuilder raceBuilder = new RaceConcreteBuilder().setListAnimal(animalslist).setLength(1000).setNumber(4);
        Race race = raceBuilder.build();

        // tạo 4 luồng chạy tương ứng với mỗi loài, mỗi luồng tính ra tổng thời gian hoàn thành của 4 con
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Animals> list = animalslist.get(finalI);
                    int totalTime = 0;
                    for (int i = 0; i < list.size(); i++) {
                        Animals animals = list.get(i);
                        totalTime = totalTime + animals.go(race.getLength());


                    }
                    System.out.println(strings[finalI] + " "+ totalTime);
                }
            });
            thread.start();
        }


    }

}

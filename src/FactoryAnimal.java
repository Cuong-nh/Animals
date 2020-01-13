public class FactoryAnimal {
public static Animals createAnimals(String s,int length,int count){
        Animals animals = null;
        switch (s){
            case "Hổ":
                animals = new Tiger(length,count);
                break;
            case "Thỏ":
                animals = new Rabbit(length,count);
                break;
            case "Đà điểu":
                animals = new Ostrich(length,count);
                break;
            case "Cánh cụt":
                animals = new Penguin(length,count);
                break;
        }
return animals;
}
}

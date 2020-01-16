public class FactoryAnimal {
public static Animals createAnimals(String s,int length,int count,int number){
        Animals animals = null;
        switch (s){
            case "Hổ":
                animals = new Tiger(length,count,number);
                break;
            case "Thỏ":
                animals = new Rabbit(length,count,number);
                break;
            case "Đà điểu":
                animals = new Ostrich(length,count,number);
                break;
            case "Cánh cụt":
                animals = new Penguin(length,count,number);
                break;
        }
return animals;
}
}

public class FactoryAnimal {
public static Animals createAnimals(String s){
        Animals animals = null;
        switch (s){
            case "Tiger":
                animals = new Tiger();
                break;
            case "Rabbit":
                animals = new Rabbit();
                break;
            case "Ostrich":
                animals = new Ostrich();
                break;
            case "Penguin":
                animals = new Penguin();
                break;
        }
return animals;
}
}

import java.util.ArrayList;
import java.util.List;

public class Race {
    List<List<Animals>> animalsList = new ArrayList<>();
    int numberOfAnimal;
    int length;

    public Race(List<List<Animals>> animalsList, int numberOfAnimal, int length) {
        this.animalsList = animalsList;
        this.numberOfAnimal = numberOfAnimal;
        this.length = length;
    }

    public List<List<Animals>> getAnimalsList() {
        return animalsList;
    }

    public int getNumberOfAnimal() {
        return numberOfAnimal;
    }

    public int getLength() {
        return length;
    }
}

import java.util.List;

public interface RaceBuilder {
    RaceBuilder setListAnimal(List<List<Animals>> animalsList);
    RaceBuilder setNumber(int number);
    RaceBuilder setLength(int length);
    Race build();
}
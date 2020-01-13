import java.util.List;

public class RaceConcreteBuilder implements RaceBuilder {
    private List<Animals> animalsList;
    private int number;
    private int length;


    @Override
    public RaceBuilder setListAnimal(List<Animals> animalsList) {
        this.animalsList = animalsList;
        return this;
    }

    @Override
    public RaceBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    @Override
    public RaceBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    @Override
    public Race build() {
        return new Race(animalsList,number,length);
    }
}

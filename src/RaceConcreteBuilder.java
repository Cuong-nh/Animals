import java.util.List;

public class RaceConcreteBuilder implements RaceBuilder {
    private int number;
    private int length;



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
        return new Race(number,length);
    }
}

import java.util.List;

public interface RaceBuilder {
    RaceBuilder setNumber(int number);
    RaceBuilder setLength(int length);
    Race build();
}

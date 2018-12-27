import java.util.Objects;

public class Animal {

    Animal(){
        name = "";
        area = null;
    }

    public Animal(String name, Area place) {
        this.name = name;
        this.area = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area place) {
        this.area = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) &&
                area == animal.area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area);
    }

    @Override
    public String toString() {
        return  name + "\t" + area;
    }

    int countMass(){
        return MASS;
    }

    private String name;
    private Area area;

    private final int MASS = 100;
}
enum Area {
    BELARUS, CANADA, AUSTRALIA;
}

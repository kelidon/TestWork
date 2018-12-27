import java.util.Objects;

public class Beast extends Animal {
    Beast(){
        super();
        prey = "";
    }

    public Beast(String name, Area place, String prey) {
        super(name, place);
        this.prey = prey;
    }

    public String getPrey() {
        return prey;
    }

    public void setPrey(String prey) {
        this.prey = prey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beast beast = (Beast) o;
        return Objects.equals(prey, beast.prey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prey);
    }
    @Override
    public String toString() {
        return  super.toString()+"\t"+ prey + "\t"+countMass();
    }

    @Override
    int countMass() {
        return prey.length()*100;
    }

    private String prey;
}

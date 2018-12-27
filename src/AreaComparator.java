import java.util.Comparator;

public class AreaComparator implements Comparator<Beast> {
    public int compare(Beast e1, Beast e2) {
        if(e1.getArea().equals(e2.getArea()))
            return e1.getName().compareTo(e2.getName());
        else
            return e1.getArea().compareTo(e2.getArea());
    }
}
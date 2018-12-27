import java.util.Comparator;

public class MassComparator implements Comparator<Beast>{



     public int compare(Beast e1, Beast e2) {
        return e2.countMass() - e1.countMass();
        }

}

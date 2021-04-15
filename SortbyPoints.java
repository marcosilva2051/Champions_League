import java.util.Comparator;

class SortbyPoints implements Comparator<equipa>{
    public int compare(equipa a, equipa b)
    {
        return b.getPoints() - a.getPoints();
    }
}
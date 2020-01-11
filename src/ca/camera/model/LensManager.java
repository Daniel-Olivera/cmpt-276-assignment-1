package ca.camera.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens> {
    private List<Lens> lenses = new ArrayList<>();

    public void add(Lens lens){
        lenses.add(lens);
    }

    public Lens get(int i){
        return lenses.get(i);
    }

    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}

package ca.camera.model;

import java.util.ArrayList;
import java.util.List;

public class LensManager {
    private List<Lens> lenses = new ArrayList<>();

    public void add(Lens lens){
        lenses.add(lens);
    }
}

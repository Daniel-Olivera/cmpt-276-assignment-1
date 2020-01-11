package ca.camera.model;

import java.util.Scanner;

public class DoFCalculator {

    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager calculate;
    private Scanner in = new Scanner(System.in);

    public DoFCalculator(LensManager calculate, Lens lens) {
         this.calculate = calculate;

    }

    public double getHyperDist(int index){
        Lens hyperLens = calculate.get(index);
        int focal = hyperLens.getFocalLength();
        double aperture = in.nextDouble();

        double hyperDist = (focal^2)/(aperture*COC);
        return hyperDist;
    }
}

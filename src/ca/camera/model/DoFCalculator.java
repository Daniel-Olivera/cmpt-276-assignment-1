package ca.camera.model;

import java.util.Scanner;

public class DoFCalculator {

    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager calculate;
    private Scanner in = new Scanner(System.in);


    public DoFCalculator(LensManager calculate) {
        this.calculate = calculate;

    }

    public double getHyperDist(int index, double fValue) {
        Lens hyperLens = calculate.get(index);
        double aperture = hyperLens.getMaxAperture();
        int focal = hyperLens.getFocalLength();



        return ((focal * focal) / (fValue * COC));
    }

    public double getDofNear(int index, double fValue, double distance) {
        Lens nearLens = calculate.get(index);
        double hyper = getHyperDist(index, fValue);
        distance *= 1000;
        return (hyper * distance) / (hyper + (distance - (nearLens.getFocalLength())));

    }

    public double getDofFar(int index, double fValue, double distance) {
        Lens farLens = calculate.get(index);
        double hyper = getHyperDist(index, fValue);
        distance *= 1000;
        return (hyper * distance) / (hyper - (distance - (farLens.getFocalLength())));
    }
}
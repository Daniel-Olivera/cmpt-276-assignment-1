package ca.camera.model;

import java.util.Scanner;

public class DoFCalculator {

    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager calculate;
    private Scanner in = new Scanner(System.in);


    public DoFCalculator(LensManager calculate) {
         this.calculate = calculate;

    }

    public double getHyperDist(int index, double fValue){
        Lens hyperLens = calculate.get(index);
        double aperture = hyperLens.getMaxAperture();
        int focal = hyperLens.getFocalLength();

        if(fValue < aperture){
            System.out.println("-----------ERROR: Invalid F-value------------");
            System.out.println("Defaulting to maximum aperture of: " + aperture);
            return ((focal*focal)/(aperture*COC))/1000;

        }

        return ((focal*focal)/(fValue*COC))/1000;
    }
}

package ca.camera.ui;

import ca.camera.model.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Sample (incomplete) text UI to interact with the user.
 * You may change any part of this!
 */
public class CameraTextUI {
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard

    public CameraTextUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    public void show() {
        DoFCalculator dofCalc = new DoFCalculator(manager);
        boolean isDone = false;
        while(!isDone){
            int lensCount = 0;
            System.out.println("Choose from one of the following lenses:");
            for(Lens lens: manager){
                System.out.println(lensCount + ": " + lens.getMake() + ", "
                + lens.getFocalLength() + "mm, F" + lens.getMaxAperture());
                lensCount++;
            }
            System.out.println("-1 to exit");

            int input = in.nextInt();
            double aperture;
            double dofNear;
            switch(input){
                case(0):
                case(1):
                case(2):
                case(3):
                    System.out.print("Aperture [the F number]: ");
                    aperture = in.nextDouble();
                    System.out.print("Distance to subject[m]: ");
                    dofNear = in.nextDouble();
                    System.out.println("DOF Near point is: " + dofCalc.getDofNear(input,aperture,dofNear));
                    System.out.println("Hyperfocal point is: " + formatM(dofCalc.getHyperDist(input,aperture)/1000) + "m");
                    System.out.println("-------------------------------------------------------");
                    break;
                case(-1):
                    isDone = true;
                    System.out.println("Done!");
                    break;
                default:
                    System.out.println("ERROR: Invalid lens index");
            }
        }

    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
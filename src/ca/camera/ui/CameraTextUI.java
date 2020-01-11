package ca.camera.ui;

import ca.camera.model.Lens;
import ca.camera.model.LensManager;
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

            switch(input){
                case(0):
                    System.out.println("You picked 0");
                    break;
                case(1):
                    System.out.println("You picked 1");
                    break;
                case(2):
                    System.out.println("You picked 2");
                    break;
                case(3):
                    System.out.println("You picked 3");
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
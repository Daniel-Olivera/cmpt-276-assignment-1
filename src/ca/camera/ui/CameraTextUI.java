package ca.camera.ui;

import ca.camera.model.*;
import java.text.DecimalFormat;
import java.util.Scanner;

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
            //prints out the list of lenses
            int lensCount = 0;
            System.out.println("Choose from one of the following lenses:");
            for(Lens lens: manager){
                System.out.println(lensCount + ": " + lens.getMake() + ", "
                + lens.getFocalLength() + "mm, F" + lens.getMaxAperture());
                lensCount++;
            }
            //exits the program if it receives a -1
            System.out.println("-1 to exit");
            int input = in.nextInt();

            double aperture;
            double distance;
            switch(input){
                case(0):
                case(1):
                case(2):
                case(3):
                    //gets a lens with the right index and prints out the information on the lens
                    Lens lens = manager.get(input);

                    System.out.print("Aperture [the F number]: ");
                    aperture = in.nextDouble();
                    if (aperture < lens.getMaxAperture()) {
                        System.out.println("-----------ERROR: Invalid F-value------------");
                        break;
                    }

                    System.out.print("Distance to subject[m]: ");
                    distance = in.nextDouble();
                    if(distance <= 0){
                        System.out.println("ERROR: distance cannot be less than or equal to 0.");
                        break;
                    }
                    double dofFar = dofCalc.getDofFar(input,aperture,distance)/1000;
                    double dofNear = dofCalc.getDofNear(input,aperture,distance)/1000;
                    double hyper = dofCalc.getHyperDist(input,aperture)/1000;

                    if(distance > hyper){
                        System.out.println("In focus: " + formatM(dofNear) + "m to " + '\u221e' + "m, [DoF = " + '\u221e' + "m]");
                    }
                    else{
                        System.out.println("In focus: " + formatM(dofNear) + "m to " + formatM(dofFar) + "m, [DoF = " + formatM(dofFar - dofNear) + "m]");
                    }
                    System.out.println("Hyperfocal point: " + formatM(hyper) + "m");
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
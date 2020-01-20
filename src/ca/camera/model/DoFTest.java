package ca.camera.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoFTest {


    @Test
    void testHyperDist(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 1.8, 50));
        testManager.add(new Lens("Tamron", 2.8, 90));
        testManager.add(new Lens("Sigma", 2.8, 200));
        testManager.add(new Lens("Nikon", 4, 200));

        assertEquals(((50 * 50) / (3 * 0.029)),testDoF.getHyperDist(0,3));
        assertEquals(((200 * 200) / (4 * 0.029)),testDoF.getHyperDist(2,4));
        assertEquals(((90 * 90) / (3 * 0.029)),testDoF.getHyperDist(1,3));
    }

    @Test
    void testDoFNear(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 1.8, 50));
        testManager.add(new Lens("Tamron", 2.8, 90));
        testManager.add(new Lens("Sigma", 2.8, 200));
        testManager.add(new Lens("Nikon", 4, 200));

        double hyper = testDoF.getHyperDist(0,3);
        double hyper2 = testDoF.getHyperDist(2,4);
        double hyper3 = testDoF.getHyperDist(1,3);

        assertEquals((hyper * 5000) / (hyper + (5000 - (50))), testDoF.getDofNear(0,3,5));
        assertEquals((hyper2 * 10000) / (hyper2 + (10000 - (200))),testDoF.getDofNear(2,4,10));
        assertEquals((hyper3 * 5000) / (hyper3 + (5000 - 90)),testDoF.getDofNear(1,3,5));
    }

    @Test
    void testDoFFar(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 1.8, 50));
        testManager.add(new Lens("Tamron", 2.8, 90));
        testManager.add(new Lens("Sigma", 2.8, 200));
        testManager.add(new Lens("Nikon", 4, 200));

        double hyper = testDoF.getHyperDist(0,3);
        double hyper2 = testDoF.getHyperDist(2,4);
        double hyper3 = testDoF.getHyperDist(1,3);

        assertEquals((hyper * 5000) / (hyper - (5000 - (50))), testDoF.getDofFar(0,3,5));
        assertEquals((hyper2 * 10000) / (hyper2 - (10000 - (200))),testDoF.getDofFar(2,4,10));
       // assertEquals((hyper3 * 5000) / (hyper - (5000 - (90))),testDoF.getDofFar(1,3,5));

    }
}

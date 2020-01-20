package ca.camera.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoFTest {

    @Test
    void testHyperDist(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 0.1, 10));
        testManager.add(new Lens("Tamron", 3, 90));
        testManager.add(new Lens("Sigma", 20, 2000));


        assertEquals(((10 * 10) / (0.1 * 0.029)),testDoF.getHyperDist(0,0.1));
        assertEquals(((90 * 90) / (3 * 0.029)),testDoF.getHyperDist(1,3));
        assertEquals(((2000 * 2000) / (20 * 0.029)),testDoF.getHyperDist(2,20));
    }

    @Test
    void testDoFNear(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 0.1, 10));
        testManager.add(new Lens("Tamron", 3, 90));
        testManager.add(new Lens("Sigma", 20, 2000));

        double hyper = testDoF.getHyperDist(0,0.1);
        double hyper2 = testDoF.getHyperDist(1,3);
        double hyper3 = testDoF.getHyperDist(2,20);

        assertEquals((hyper * 5000) / (hyper + (5000 - 10)), testDoF.getDofNear(0,0.1,5));
        assertEquals((hyper2 * 10000) / (hyper2 + (10000 - 90)),testDoF.getDofNear(1,3,10));
        assertEquals((hyper3 * 5000) / (hyper3 + (5000 - 2000)),testDoF.getDofNear(2,20,5));
    }

    @Test
    void testDoFFar(){
        LensManager testManager = new LensManager();
        DoFCalculator testDoF = new DoFCalculator(testManager);

        testManager.add(new Lens("Canon", 0.1, 10));
        testManager.add(new Lens("Tamron", 3, 90));
        testManager.add(new Lens("Sigma", 20, 2000));

        double hyper = testDoF.getHyperDist(0,0.1);
        double hyper2 = testDoF.getHyperDist(1,3);
        double hyper3 = testDoF.getHyperDist(2,20);

        assertEquals((hyper * 5000) / (hyper - (5000 - 10)), testDoF.getDofFar(0,0.1,5));
        assertEquals((hyper2 * 10000) / (hyper2 - (10000 - 90)),testDoF.getDofFar(1,3,10));
        assertEquals((hyper3 * 5000) / (hyper3 - (5000 - 2000)),testDoF.getDofFar(2,20,5));

    }
}

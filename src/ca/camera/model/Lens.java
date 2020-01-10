package ca.camera.model;

public class Lens {
    private static String make;
    private static double maxAperture;
    private static int focalLength;

    public Lens(String make, double maxAperture, int focalLength) {
        this.make = make;
        this.maxAperture = maxAperture;
        this.focalLength = focalLength;
    }

    public static String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public static double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(float maxAperture) {
        this.maxAperture = maxAperture;
    }

    public static int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }
}


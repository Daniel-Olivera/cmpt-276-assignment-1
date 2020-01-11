import ca.camera.model.DoFCalculator;
import ca.camera.model.LensManager;
import ca.camera.ui.CameraTextUI;

public class Main {
    public static void main(String args[]) {
        LensManager manager = new LensManager();
        CameraTextUI ui = new CameraTextUI(manager);
        ui.show();
    }
}
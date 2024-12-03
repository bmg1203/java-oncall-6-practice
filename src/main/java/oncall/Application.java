package oncall;

import oncall.controller.OnCallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OnCallController onCallController = new OnCallController(new InputView(), new OutputView());
        onCallController.run();
    }
}

package oncall.controller;

import oncall.domain.DateInfo;
import oncall.domain.Employees;
import oncall.domain.WorkOrder;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {

    private DateInfo dateInfo;
    private Employees employees;
    private WorkOrder workOrder;

    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        input();
        calculateWorkOrder();
        output();
    }

    private void input() {
        dateInfo = inputView.dateInfoInput();
        employees = inputView.employeeInput();
    }

    private void calculateWorkOrder() {
        workOrder = new WorkOrder(dateInfo, employees);
    }

    private void output() {
        outputView.workOrderOutput(workOrder, dateInfo);
    }
}

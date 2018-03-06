package Helpers;

import BigNumbers.BigNumber;

public class OperationData {
    private BigNumber firstArg;
    private BigNumber secondArg;
    private String operation;

    OperationData(BigNumber firstArg, BigNumber secondArg, String operation) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        this.operation = operation;
    }

    public BigNumber getFirstArg() {
        return firstArg;
    }

    public BigNumber getSecondArg() {
        return secondArg;
    }

    public String getOperation() {
        return operation;
    }
}

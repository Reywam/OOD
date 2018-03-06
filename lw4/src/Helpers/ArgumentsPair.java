package Helpers;

import BigNumbers.BigNumber;

public class ArgumentsPair {

    public ArgumentsPair(BigNumber first, BigNumber second) {
        firstArg = first;
        secondArg = second;
    }

    BigNumber firstArg;
    BigNumber secondArg;

    public BigNumber getSecondArg() {
        return secondArg;
    }

    public BigNumber getFirstArg() {
        return firstArg;
    }
}

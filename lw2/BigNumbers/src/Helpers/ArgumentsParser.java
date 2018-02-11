package Helpers;

import BigNumbers.BigNumber;

public class ArgumentsParser {
    private static final int ARGS_COUNT = 3;
    public OperationData parseString(String string) throws Exception {
        String[] strArr = string.split(" ");

        if(strArr.length != ARGS_COUNT) {
            throw new Exception("invalid arguments count");
        }

        BigNumber firstArg = new BigNumber(strArr[0]);
        String operation = strArr[1];
        BigNumber secondArg = new BigNumber(strArr[2]);

        return new OperationData(firstArg, secondArg, operation);
    }
}
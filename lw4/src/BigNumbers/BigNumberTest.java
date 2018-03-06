package BigNumbers;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {
    BigNumber number = new BigNumber("0");

    @org.junit.jupiter.api.Test
    void canFindSqrtOfSimpleDigits() {
        for (int digit = 0; digit < 10; digit++) {
            number = new BigNumber(String.valueOf(digit));
            BigNumber expectedValue = new BigNumber(String.valueOf((int) Math.sqrt(digit)));
            assertTrue(expectedValue.compareTo(number.Sqrt()) == 0);
        }
    }

    @org.junit.jupiter.api.Test
    void canFindSqrtOfNumbers() {
        for (int number = 100; number < 10000; number += 100) {
            this.number = new BigNumber(String.valueOf(number));
            BigNumber expectedValue = new BigNumber(String.valueOf((int) Math.sqrt(number)));

            if(expectedValue.compareTo(this.number.Sqrt()) != 0) {
                System.out.println(this.number);
            }

            assertTrue(expectedValue.compareTo(this.number.Sqrt()) == 0);
        }
    }

    @org.junit.jupiter.api.Test
    void canFindSqrtOfBigNumbers() {
        number = new BigNumber("90000");
        BigNumber expected = new BigNumber("300");
        assertTrue(expected.compareTo(number.Sqrt()) == 0);

        number = new BigNumber("40000000000000000000000");
        expected = new BigNumber("200000000000");
        assertTrue(expected.compareTo(number.Sqrt()) == 0);

        number = new BigNumber("90000000000000000");
        expected = new BigNumber("300000000");
        assertTrue(expected.compareTo(number.Sqrt()) == 0);

        number = new BigNumber("100000000000000000000000000000000000000000000000000000000000000000000000000000000");
        expected = new BigNumber("10000000000000000000000000000000000000000");
        assertTrue(expected.compareTo(number.Sqrt()) == 0);
    }

    @org.junit.jupiter.api.Test
    void canDivSimpleNumbers() {
        number = new BigNumber("265");
        BigNumber expected = new BigNumber("53");
        assertTrue(expected.compareTo(number.Divide(new BigNumber("5"))) == 0);
    }

    @org.junit.jupiter.api.Test
    void cantDivZero() {
        number = new BigNumber("265");
        BigNumber expected = new BigNumber("0");
        try{
            number.Divide(new BigNumber("0"));
        } catch(Exception ex) {
            assertTrue(true);
        }
    }

    @org.junit.jupiter.api.Test
    void canDivNumbers() {
        for (int number = 100; number < 10000; number += 100) {
            this.number = new BigNumber(String.valueOf(number));
            BigNumber expectedValue = new BigNumber(String.valueOf(number / 246));
            assertTrue(expectedValue.compareTo(this.number.Divide(new BigNumber("246"))) == 0);
        }
    }

    @org.junit.jupiter.api.Test
    void canDivBigNumbers() {
        number = new BigNumber("386998333333333333333333333333333333333333333333");
        BigNumber expected = new BigNumber("3869983333333333333333333333333333333333333333");
        assertTrue(expected.compareTo(number.Divide(new BigNumber("100"))) == 0);

        number = new BigNumber("20000000000000000000000000000000000000000000000000000000000000000000000000000");
        expected = new BigNumber("2");
        BigNumber divValue = new BigNumber("10000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertTrue(expected.compareTo(number.Divide(divValue)) == 0);

        number = new BigNumber("450000000000000000000000000000000000000000000000000000000000000000000000000000000");
        divValue = new BigNumber("90000000000000000000000000000000000000000000000000000");
        expected = new BigNumber("5000000000000000000000000000");
        assertTrue(expected.compareTo(number.Divide(divValue)) == 0);
    }
}
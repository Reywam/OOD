package BigNumbers;

import java.util.ArrayList;
import java.util.List;

public class BigNumber implements IBigNumber {

    final static int BASE = 10;
    private boolean positive = true;

    private List<Character> value = new ArrayList();

    public BigNumber(String value) {
        for (int i = value.length() - 1; i >= 0; i--) {
            if (value.charAt(i) == '-') {
                positive = false;
                continue;
            }
            this.value.add(value.charAt(i));
        }
        if (!isValidValue()) {
            throw new ExceptionInInitializerError("no digit character");
        }
    }

    private String reverse(String str) {
        return new StringBuffer(str).reverse().toString();
    }

    @Override
    public BigNumber Add(BigNumber number) {
        if (this.isPositive() && !number.isPositive()) {
            return this.Sub(number);
        } else if (!this.isPositive() && number.isPositive()) {
            return number.Sub(this);
        } else if (!this.isPositive() && !number.isPositive()) {
            this.positive = true;
            number.positive = true;
            BigNumber newNumber = Add(number);
            this.positive = false;
            number.positive = false;
            newNumber.positive = false;
            return newNumber;
        }

        setSameSize(number);

        String newNumberData = "";
        boolean carry = false;
        for (int i = 0; i < number.size(); i++) {
            int v1 = Character.getNumericValue(this.value.get(i));

            int v2 = Character.getNumericValue(number.value.get(i));
            int newDigit = v1 + v2;

            if (carry) {
                newDigit++;
                carry = false;
            }

            if (newDigit >= BASE) {
                carry = true;
                newDigit -= BASE;
            }

            newNumberData += Integer.toString(newDigit);
        }

        if (carry) {
            newNumberData += 1;
        }

        BigNumber newNumber = new BigNumber(reverse(newNumberData));
        newNumber.removeBadZeros();
        return newNumber;
    }

    @Override
    public BigNumber Sub(BigNumber number) {

        if (!this.isPositive() && !number.isPositive()) {
            number.positive = true;
            BigNumber newNumber = Add(number);
            //newNumber.positive = false;
            return newNumber;
        }

        int compareRes = compareTo(number);
        if (compareRes == -1) {
            BigNumber newNumber = number.Sub(this);
            newNumber.positive = false;
            return newNumber;
        }

        setSameSize(number);

        String newNumberData = "";

        boolean carry = false;
        for (int i = 0; i < number.size(); i++) {
            int v1 = Character.getNumericValue(this.value.get(i));
            int v2 = Character.getNumericValue(number.value.get(i));
            int newDigit = v1 - v2;

            if (carry) {
                newDigit--;
                carry = false;
            }

            if (newDigit < 0) {
                carry = true;
                newDigit += BASE;
            }

            newNumberData += Integer.toString(newDigit);
        }

        if (carry) {
            newNumberData += (0);
        }
        BigNumber newNumber = new BigNumber(reverse(newNumberData));
        newNumber.removeBadZeros();
        return newNumber;
    }

    @Override
    public BigNumber Multiply(BigNumber number) {
        List<BigNumber> numbers = new ArrayList();

        if (!this.isPositive() && !number.isPositive()) {
            BigNumber firstArg = this;
            firstArg.positive = true;

            BigNumber secondArg = number;
            secondArg.positive = true;

            BigNumber newNumber = firstArg.Multiply(secondArg);

            return newNumber;
        } else if (!this.isPositive() || !number.isPositive()) {
            BigNumber firstArg = this;
            firstArg.positive = true;

            BigNumber secondArg = number;
            secondArg.positive = true;

            BigNumber newNumber = firstArg.Multiply(secondArg);
            newNumber.positive = false;
            return newNumber;
        }

        for (int i = 0; i < number.size(); i++) {
            int v1 = Character.getNumericValue(number.value.get(i));
            String newNumData = "";
            int carryValue = 0;
            for (int currDigit = 0; currDigit < this.size(); currDigit++) {
                int v2 = Character.getNumericValue(this.value.get(currDigit));

                int newValue = v1 * v2;

                if (carryValue > 0) {
                    newValue += carryValue;
                }

                if (newValue >= BASE) {
                    carryValue = newValue / BASE;
                    newValue = newValue % BASE;
                }

                newNumData += Integer.toString(newValue);
            }

            String zerosString = "";
            for (int zerosCount = 0; zerosCount < i; zerosCount++) {
                zerosString += '0';
            }

            if (carryValue > 0) {
                newNumData += Integer.toString(carryValue);
            }

            newNumData = (zerosString + newNumData);

            numbers.add(new BigNumber(reverse(newNumData)));
        }

        BigNumber newNumber = new BigNumber("0");

        for (int i = 0; i < numbers.size(); i++) {
            newNumber = newNumber.Add(numbers.get(i));
        }

        return newNumber;
    }

    @Override
    public BigNumber Divide(BigNumber number) throws IllegalArgumentException {
        if (number.compareTo(new BigNumber("0")) == 0) {
            throw new IllegalArgumentException("/ 0");
        }

        if (!this.isPositive() && !number.isPositive()) {
            BigNumber firstArg = this;
            firstArg.positive = true;

            BigNumber secondArg = number;
            secondArg.positive = true;

            BigNumber newNumber = firstArg.Divide(secondArg);

            return newNumber;
        } else if (!this.isPositive() || !number.isPositive()) {
            BigNumber firstArg = this;
            firstArg.positive = true;

            BigNumber secondArg = number;
            secondArg.positive = true;

            BigNumber newNumber = firstArg.Divide(secondArg);
            newNumber.positive = false;

            return newNumber;
        }

        String newNumberData = "";
        BigNumber newNumber = new BigNumber("0");
        if (this.size() < number.size()) {
            return newNumber;
        }

        while (!this.value.isEmpty()) {
            BigNumber d1 = new BigNumber(this.value.get(this.size() - 1).toString());
            this.value.remove(this.size() - 1);
            do {
                if (d1.compareTo(number) >= 0 || this.value.isEmpty()) {
                    break;
                }
                int lastElement = this.size() - 1;
                d1.value.add(0, this.value.get(lastElement));
                this.value.remove(lastElement);
            } while (!this.value.isEmpty());

            BigNumber multiplicationResult = new BigNumber("0");
            for (int divCoefficient = BASE - 1; divCoefficient >= 0; divCoefficient--) {
                multiplicationResult = number.Multiply(new BigNumber(Integer.toString(divCoefficient)));
                if (multiplicationResult.compareTo(d1) != 1) {
                    newNumberData += Integer.toString(divCoefficient);
                    break;
                }
            }
            BigNumber iterRes = d1.Sub(multiplicationResult);

            if (iterRes.compareTo(new BigNumber("0")) != 0 && !this.value.isEmpty()) {
                for (int i = 0; i < iterRes.size(); i++) {
                    this.value.add(iterRes.value.get(i));
                }
            }
        }
        newNumber = new BigNumber(newNumberData);
        return newNumber;
    }

    @Override
    public String toString() {
        String value = positive ? "" : "-";
        for (int i = this.value.size() - 1; i >= 0; i--) {
            value += this.value.get(i);
        }
        return value;
    }

    @Override
    public void upSizeTo(int size) {
        if (this.size() < size) {
            while (this.size() != size) {
                this.value.add('0');
            }
        }
    }

    void setSameSize(BigNumber number) {
        if (number.size() > this.size()) {
            this.upSizeTo(number.size());
        } else {
            number.upSizeTo(this.size());
        }
    }

    void removeBadZeros() {
        for (int i = this.size() - 1; this.size() > 1 && i >= 0; i--) {
            if (value.get(i) != '0') {
                break;
            } else {
                value.remove(i);
            }
        }
    }

    public boolean isPositive() {
        return positive;
    }

    private boolean isValidValue() {
        boolean result = true;
        for (int i = 0; i < this.size(); i++) {
            if (!Character.isDigit(value.get(i))) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public int size() {
        return value.size();
    }

    public int compareTo(BigNumber number) {
        int result = 0;
        if (this.size() > number.size()) {
            result = 1;
        } else if (this.size() < number.size()) {
            result = -1;
        } else {
            for (int i = number.size() - 1; i >= 0; i--) {
                if (this.value.get(i) > number.value.get(i)) {
                    result = 1;
                    break;
                } else if (this.value.get(i) < number.value.get(i)) {
                    result = -1;
                    break;
                }
            }
        }

        return result;
    }
}
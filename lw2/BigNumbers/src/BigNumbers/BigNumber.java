package BigNumbers;

import java.util.ArrayList;
import java.util.List;

public class BigNumber implements IBigNumber {

    private List<Character> value = new ArrayList();

    public BigNumber(String value) {
        for (int i = value.length() - 1; i >= 0; i--) {
            this.value.add(value.charAt(i));
        }
    }

    private String reverse(String str) {
        return new StringBuffer(str).reverse().toString();
    }

    @Override
    public BigNumber Add(BigNumber number) {
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

            if (newDigit >= 10) {
                carry = true;
                newDigit -= 10;
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

        int compareRes = compareTo(number);

        if (compareRes == -1) {
            BigNumber newNumber = number.Sub(this);
            newNumber.value.add('-');
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
                newDigit += 10;
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

                if (newValue >= 10) {
                    carryValue = newValue / 10;
                    newValue = newValue % 10;
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

    public BigNumber Multiply(int number) {
        BigNumber newNumber = this;
        for (int i = 1; i < number; i++) {
            newNumber = newNumber.Add(this);
        }
        return newNumber;
    }

    @Override
    public BigNumber Divide(BigNumber number) {
        return null;
    }

    @Override
    public String toString() {
        String value = "";
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
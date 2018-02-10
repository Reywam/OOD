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
        for (int i = 0; i < Math.max(this.size(), number.size()); i++) {
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

        if(compareRes == -1) {
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
        return null;
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
                if (this.value.get(i) < number.value.get(i)) {
                    result = -1;
                    break;
                }
            }
        }

        return result;
    }
}
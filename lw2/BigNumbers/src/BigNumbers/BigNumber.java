package BigNumbers;

import java.util.ArrayList;
import java.util.List;

public class BigNumber implements IBigNumber {

    private List<Integer> value = new ArrayList();

    public BigNumber(String value) {
        for(int i = value.length() - 1; i >= 0; i--) {
            this.value.add(Character.getNumericValue(value.charAt(i)));
        }
    }

    public BigNumber(List<Integer> value) {
        for(int i = value.size() - 1; i >= 0; i--) {
            this.value.add(value.get(i));
        }
    }

    private List<Integer> reverse(List<Integer> arr) {
        List<Integer> reversedArr = new ArrayList<>();
        for(int i = arr.size() - 1; i >= 0; i--) {
            reversedArr.add(arr.get(i));
        }
        return reversedArr;
    }

    @Override
    public BigNumber Add(BigNumber number) {
        if(number.size() > this.size()) {
            this.upSizeTo(number.size());
        }
        List<Integer> newNumberData = new ArrayList<>();
        boolean carry = false;
        for(int i = 0; i < Math.max(this.value.size(), number.size()); i++) {
            int newDigit = this.value.get(i) + number.value.get(i);

            if(carry) {
                newDigit++;
                carry = false;
            }

            if(newDigit >= 10) {
                carry = true;
                newDigit = newDigit - 10;
            }

            newNumberData.add(newDigit);
        }
        return new BigNumber(reverse(newNumberData));
    }

    @Override
    public BigNumber Sub(BigNumber number) {
        return null;
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
        for(int i = this.value.size() - 1; i >= 0; i--) {
            value += this.value.get(i);
        }
        return value;
    }

    @Override
    public void upSizeTo(int size) {
        if(this.size() < size) {
            while(this.size() != size) {
                this.value.add(0);
            }
        }
    }

    @Override
    public int size() {
        return value.size();
    }
}
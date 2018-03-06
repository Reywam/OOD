package BigNumbers;

import Helpers.ArgumentsPair;

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
        removeBadZeros();
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
            BigNumber firstArg = this;
            firstArg.positive = true;

            BigNumber secondArg = number;
            secondArg.positive = true;

            BigNumber newNumber = Add(number);
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
            BigNumber arg = number;
            arg.positive = true;
            BigNumber newNumber = Add(arg);
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
            ArgumentsPair pair = getPositiveCopies(this, number);
            BigNumber newNumber = pair.getFirstArg().Multiply(pair.getSecondArg());

            return newNumber;
        } else if (!this.isPositive() || !number.isPositive()) {
            ArgumentsPair pair = getPositiveCopies(this, number);
            BigNumber newNumber = pair.getFirstArg().Multiply(pair.getSecondArg());
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
                    carryValue = 0;
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
        //TODO косяк с делением
        if (number.compareTo(new BigNumber("0")) == 0) {
            throw new IllegalArgumentException("/ 0");
        }

        if (!this.isPositive() && !number.isPositive()) {
            ArgumentsPair pair = getPositiveCopies(this, number);
            BigNumber newNumber = pair.getFirstArg().Divide(pair.getSecondArg());
            return newNumber;
        } else if (!this.isPositive() || !number.isPositive()) {
            ArgumentsPair pair = getPositiveCopies(this, number);
            BigNumber newNumber = pair.getFirstArg().Divide(pair.getSecondArg());
            newNumber.positive = false;

            return newNumber;
        }

        StringBuffer newNumberData = new StringBuffer("");
        BigNumber newNumber = new BigNumber("0");
        if (this.size() < number.size()) {
            return newNumber;
        }

        int digitCount = 0;
        while (!this.value.isEmpty()) {
            BigNumber dividend = createNumberForDivision(number, newNumberData, digitCount);
            dividend.removeBadZeros();
            if(dividend.compareTo(new BigNumber("0")) == 0) {
                newNumberData.append("0");
                continue;
            }
            digitCount = 0;
            BigNumber multiplicationResult = new BigNumber("0");
            for (int divCoefficient = BASE - 1; divCoefficient >= 0; divCoefficient--) {
                multiplicationResult = number.Multiply(new BigNumber(Integer.toString(divCoefficient)));
                if (multiplicationResult.compareTo(dividend) != 1) {
                    newNumberData.append(Integer.toString(divCoefficient));
                    break;
                }
            }
            BigNumber iterRes = dividend.Sub(multiplicationResult);

            if (iterRes.compareTo(new BigNumber("0")) != 0 && !this.value.isEmpty()) {
                digitCount = iterRes.size();
                for (int i = 0; i < iterRes.size(); i++) {
                    this.value.add(iterRes.value.get(i));
                }
            }
        }
        newNumber = new BigNumber(newNumberData.toString());
        newNumber.removeBadZeros();
        return newNumber;
    }

    private List<String> divideByNumbers() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < this.size(); i += 2) {
            String digit1 = "";
            String digit2 = "";

            if (i < this.size() - 1) {
                digit1 = this.value.get(i + 1).toString();
            }

            if (i < this.size()) {
                digit2 = this.value.get(i).toString();
            }

            String digits = digit1 + digit2;
            numbers.add(digits);
        }
        return numbers;
    }

    BigNumber calculateNewValue(List<Integer> numbers) {
        BigNumber number = new BigNumber("0");
        for (int i = 0; i < numbers.size(); i++) {
            number = number.Multiply(new BigNumber("10")).Add(new BigNumber(String.valueOf(numbers.get(i))));
        }
        return number;
    }

    @Override
    public BigNumber Sqrt() throws IllegalArgumentException {
        List<Integer> sqrt = new ArrayList<>();
        // Делим число по 2 цифры начиная слева
        List<String> numbers = divideByNumbers();
        int iterCount = numbers.size() - 1;

        int initDefaultValue = Integer.parseInt(numbers.get(numbers.size() - 1));
        numbers.remove(numbers.size() - 1);

        int firstSqrtDigit = (int) Math.sqrt(initDefaultValue);
        sqrt.add(firstSqrtDigit);

        BigNumber defaultValue = new BigNumber(String.valueOf(initDefaultValue));
        BigNumber sqrtValue = new BigNumber(String.valueOf(firstSqrtDigit));
        // Здесь подсчитывается корень из большого числа
        // Сначала возводим текущее значение корня в квадрат
        BigNumber valueSqr = sqrtValue.Multiply(sqrtValue);
        // Шаг по алгоритму, получаем первое число нового значения под корнем
        BigNumber firstValueForNumUnderRoot = defaultValue.Sub(valueSqr);
        for (int i = 0; i < iterCount; i++) {
            // Выносим следующее число к новому значению
            String newPartOfValueUnderRoot = "";
            BigNumber currValueUnderRoot = defaultValue;
            if (!numbers.isEmpty()) {
                // "Опускаем" следующую пару чисел к текущему числу под корнем
                newPartOfValueUnderRoot = String.valueOf(firstValueForNumUnderRoot) + String.valueOf(numbers.get(numbers.size() - 1));
                numbers.remove(numbers.size() - 1);
                // Создали число, которое будет "под корнем", из него вычитаем то, что выйдет при подборе
                currValueUnderRoot = new BigNumber(newPartOfValueUnderRoot);
            }

            // значение корня умножили на 2, по алгоритму
            sqrtValue = sqrtValue.Multiply(new BigNumber("2"));
            int newDigit = 0;
            BigNumber subtrahend = new BigNumber("0");
            for (int digit = 9; digit >= 0; digit--) {
                // конструируем число, подбираем коэффициент
                subtrahend = new BigNumber(String.valueOf(sqrtValue) + String.valueOf(digit));
                BigNumber comparisonValue = subtrahend.Multiply(new BigNumber(String.valueOf(digit)));
                if (comparisonValue.compareTo(currValueUnderRoot) <= 0) {
                    newDigit = digit;
                    sqrt.add(newDigit);
                    break;
                }
            }

            if (numbers.isEmpty()) {
                break;
            }

            BigNumber newDefaultValue = currValueUnderRoot.Sub(subtrahend.Multiply(new BigNumber(String.valueOf(newDigit))));
            defaultValue = newDefaultValue;
            sqrtValue = calculateNewValue(sqrt);
        }

        String sqrtString = "";
        for (int i = 0; i < sqrt.size(); i++) {
            sqrtString += String.valueOf(sqrt.get(i));
        }

        return new BigNumber(sqrtString);
    }

    BigNumber createNumberForDivision(BigNumber divider, StringBuffer newNumberData, int digitCount) {
        String valueForDiv = "";
        for (int i = 0; i <= digitCount && this.size() != 0; i++) {
            valueForDiv += this.value.get(this.size() - 1).toString();
            this.value.remove(this.size() - 1);
        }

        BigNumber dividend = new BigNumber(valueForDiv);
        do {
            if (dividend.compareTo(divider) >= 0 || this.value.isEmpty()) {
                break;
            }
            if (digitCount >= 0) {
                newNumberData.append('0');
            }
            int lastElement = this.size() - 1;
            dividend.value.add(0, this.value.get(lastElement));
            this.value.remove(lastElement);
        } while (!this.value.isEmpty());

        return dividend;
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

    ArgumentsPair getPositiveCopies(BigNumber first, BigNumber second) {
        BigNumber firstArg = first;
        firstArg.positive = true;

        BigNumber secondArg = second;
        secondArg.positive = true;

        return new ArgumentsPair(firstArg, secondArg);
    }

    public int compareTo(BigNumber number) {
        int result = 0;
        if (this.size() > number.size()) {
            result = 1;
        } else if (this.size() < number.size()) {
            result = -1;
        } else {
            for (int i = number.size() - 1; i >= 0; i--) {
                if (this.isPositive() && !number.isPositive()) {
                    result = 1;
                    break;
                } else if (!this.isPositive() && number.isPositive()) {
                    result = -1;
                    break;
                } else if (this.value.get(i) > number.value.get(i)) {
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
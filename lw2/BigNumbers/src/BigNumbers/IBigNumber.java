package BigNumbers;

public interface IBigNumber {
    BigNumber Add(BigNumber number);
    BigNumber Sub(BigNumber number);
    BigNumber Multiply(BigNumber number);
    BigNumber Divide(BigNumber number);
    String toString();
    void upSizeTo(int size);
    int size();
}

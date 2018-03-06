package BigNumbers;

public interface IBigNumber {
    BigNumber Add(BigNumber number);
    BigNumber Sub(BigNumber number);
    BigNumber Multiply(BigNumber number);
    BigNumber Divide(BigNumber number) throws IllegalArgumentException;
    BigNumber Sqrt() throws IllegalArgumentException;
    String toString();
    void upSizeTo(int size);
    int size();
}
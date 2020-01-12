import java.util.LinkedList;
import java.util.List;

public class CashRegister {
    public LinkedList<Car> cashRegisterQueue;
    public static int full;
    public static int numberOfCashRegisters;
    public static int occupiedCashRegisters;
    public double mi;

    public CashRegister(int full, int numberOfCashRegisters, int mi){
        cashRegisterQueue = new LinkedList<>();
        this.full = full;
        this.numberOfCashRegisters = numberOfCashRegisters;
        this.occupiedCashRegisters = 0;
        this.mi = mi;
    }
}

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimObj;

public class GasStation extends BasicSimObj {
    Distributor[] distributors;
    CashRegister cashRegister;
    Carwash carwash;
    SimGenerator simGenerator;
    public static int loss;
    public static int carLimit;

    public GasStation(Distributor[] distributors, CashRegister cashRegister, int carLimit){
        this.distributors = distributors;
        this.cashRegister = cashRegister;
        this.carwash = new Carwash();
        this.simGenerator = new SimGenerator();
        this.carLimit = carLimit;
        this.loss = 0;


    }
}

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Fuelling extends BasicSimEvent <GasStation, Object>{
    GasStation gasStation;
    int whichFuel;

    public Fuelling(GasStation entity, double delay, int whichFuel) throws SimControlException {
        super(entity, delay);
        this.whichFuel = whichFuel;
    }

    @Override
    protected void stateChange() throws SimControlException {
        gasStation = getSimObj();
        Car car = gasStation.distributors[whichFuel].distributorQueue.getFirst();
        gasStation.distributors[whichFuel].distributorQueue.removeFirst();
        System.out.println("Samochod " + car.id + " tankuje " + gasStation.distributors[whichFuel].name);
        gasStation.distributors[whichFuel].isDistributorFree = false;
        if(gasStation.cashRegister.cashRegisterQueue.size() < gasStation.cashRegister.full){
            gasStation.cashRegister.cashRegisterQueue.addLast(car);
        }

        if(gasStation.cashRegister.numberOfCashRegisters > gasStation.cashRegister.occupiedCashRegisters) {
            double dt = gasStation.distributors[whichFuel].dt;
            new Payment(gasStation, dt);
        }



    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}

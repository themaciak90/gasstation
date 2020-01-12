import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Wash extends BasicSimEvent<GasStation, Object> {
    GasStation gasStation;

    public Wash(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        gasStation = getSimObj();
        Car car = gasStation.carwash.carwashQueue.getFirst();
        if(car.whichDistributor != 3)
            gasStation.distributors[car.whichDistributor].isDistributorFree = true;
        gasStation.carwash.carwashQueue.removeFirst();
        System.out.println("Samochod " + car.id + " zajmuje myjnie");
        gasStation.carwash.isFree = false;
        gasStation.cashRegister.occupiedCashRegisters--;
        double delay = gasStation.simGenerator.exponential(gasStation.carwash.washTime);
        new CarDeparture(gasStation, delay, car);


    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}

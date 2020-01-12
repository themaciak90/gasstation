import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class CarDeparture extends BasicSimEvent<GasStation,Car> {
    GasStation gasStation;
    Car car;

    public CarDeparture(GasStation entity, double delay, Car car) throws SimControlException {
        super(entity, delay, car);
        this.car = car;
    }

    @Override
    protected void stateChange() throws SimControlException {
        gasStation = getSimObj();
        if(!car.toWash) {
            gasStation.cashRegister.occupiedCashRegisters--;
            gasStation.distributors[car.whichDistributor].isDistributorFree = true;
            if(!gasStation.distributors[car.whichDistributor].distributorQueue.isEmpty()){
                new Fuelling(gasStation, 0,car.whichDistributor);
            }
        }else {
            gasStation.carwash.isFree = true;
        }
        System.out.println("Samochod " + car.id + " odjezdza");



//        if(!gasStation.cashRegister.cashRegisterQueue.isEmpty()){
//            new Payment(gasStation,0);
//        }
//        for(Distributor distributor : gasStation.distributors){
//            if(!distributor.distributorQueue.isEmpty()){
//                new Fuelling(gasStation, 0, distributor.id);
//            }
//        }
//        if(!gasStation.carwash.carwashQueue.isEmpty() && gasStation.carwash.isFree){
//            new Wash(gasStation, 0);
//        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}

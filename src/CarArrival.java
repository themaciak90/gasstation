import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.Random;

public class CarArrival extends BasicSimEvent<GasStation, Object> {
    GasStation gasStation;
    public static int id = 0;
    Random rand = new Random();


    public CarArrival(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
        getSimObj().simGenerator.exponential(delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        gasStation = getSimObj();
        Car car = new Car();
        car.id = id;
        id++;
        car.whichDistributor = rand.nextInt(4);
        if(car.whichDistributor == 3){
            car.toWash = true;
            if(gasStation.cashRegister.cashRegisterQueue.size() < gasStation.cashRegister.full){
                System.out.println("Samochod " + car.id + " przyjechal tylko do myjni. ");
                gasStation.cashRegister.cashRegisterQueue.addLast(car);
            }else{
                gasStation.loss++;
                System.out.println("Samochod " + car.id + " odjechal, poniewaz brak miejsca w kolejce do kasy");
            }


        }else {
            if(gasStation.distributors[car.whichDistributor].distributorQueue.size() < gasStation.distributors[car.whichDistributor].full){
                gasStation.distributors[car.whichDistributor].distributorQueue.addLast(car);
                System.out.println("Samochod " + car.id + " przyjechal zatankowac: " +
                        gasStation.distributors[car.whichDistributor].name);
            }
            else{
                gasStation.loss++;
                System.out.println("Samochod " + car.id + " odjechal poniewaz nie ma miejsca w kolejce do dystrybutorow");
            }


//            if(gasStation.distributors[car.whichDistributor].isDistributorFree) {
//                new Fuelling(gasStation, 0, car.whichDistributor);
//            }
        }

        for(Distributor distributor : gasStation.distributors){
            if(distributor.isDistributorFree && !distributor.distributorQueue.isEmpty()){
                new Fuelling(gasStation,0,distributor.id);
            }
        }

        if(gasStation.cashRegister.numberOfCashRegisters > gasStation.cashRegister.occupiedCashRegisters && car.toWash) {
            new Payment(gasStation, 0);
        }
        if(gasStation.carLimit >= id){
            double dt = gasStation.simGenerator.exponential(gasStation.distributors[0].surrounding.lambda);
            new CarArrival(gasStation,dt);
        }

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}

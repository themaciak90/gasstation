import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.Random;

public class Payment extends BasicSimEvent<GasStation,Object> {
    GasStation gasStation;
    Random random = new Random();


    public Payment(GasStation entity, double delay ) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        gasStation = getSimObj();
        Car car = gasStation.cashRegister.cashRegisterQueue.getFirst();
        gasStation.cashRegister.cashRegisterQueue.removeFirst();
        System.out.println("Klient z samochodu nr " + car.id + " zajmuje okienko:" + gasStation.cashRegister.occupiedCashRegisters);
        gasStation.cashRegister.occupiedCashRegisters++;
        System.out.println("Liczba wolnych okienek: " +
                (gasStation.cashRegister.numberOfCashRegisters - gasStation.cashRegister.occupiedCashRegisters));
        if(car.toWash){
            gasStation.carwash.carwashQueue.addLast(car);
            if(gasStation.carwash.isFree){
                double delay = gasStation.simGenerator.exponential(gasStation.cashRegister.mi);
                new Wash(gasStation,delay);
            }
        }else {
            int wash = random.nextInt(10);
            if (wash < 6) {
                double delay = gasStation.simGenerator.exponential(gasStation.cashRegister.mi);
                new CarDeparture(gasStation,delay,car);
            } else {
                car.toWash = true;
                gasStation.carwash.carwashQueue.addLast(car);
                if(gasStation.carwash.isFree){
                    double delay = gasStation.simGenerator.exponential(gasStation.cashRegister.mi);
                    new Wash(gasStation, delay);
                }
            }

        }



    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}

import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

public class Main {
    public static void main(String[] args) {
        Surrounding surrounding = new Surrounding(5);
        Distributor petrol = new Distributor("Petrol",10,4, surrounding,0);
        Distributor lpg = new Distributor("LPG",15,3,surrounding,1);
        Distributor diesel = new Distributor("ON", 20, 5, surrounding, 2);
        Distributor[] distributors = new Distributor[]{petrol,lpg,diesel};

        SimManager simManager = new SimManager();

        GasStation gasStation = new GasStation(distributors,new CashRegister(25,4, 7), 200);

        try {
            new CarArrival(gasStation,0);
            simManager.startSimulation();
        } catch (SimControlException e) {
            e.printStackTrace();
        }

        System.out.println("Kolejka do tankowania Petrol: " + gasStation.distributors[0].distributorQueue.size());
        System.out.println("Kolejka do tankowania LPG : " + gasStation.distributors[1].distributorQueue.size());
        System.out.println("Kolejka do tankowania Diesel : " + gasStation.distributors[2].distributorQueue.size());
        System.out.println("Kolejka do placenia: " + gasStation.cashRegister.cashRegisterQueue.size());
        System.out.println("Kolejka do myjni: " + gasStation.carwash.carwashQueue.size());
        System.out.println("Strata wynosi: " + gasStation.loss);




    }
}

import java.util.LinkedList;


public class Distributor {
    public String name;
    int id;
    public int full;
    public int dt;
    public static Surrounding surrounding;
    public LinkedList<Car> distributorQueue;
    public boolean isDistributorFree;

    public Distributor(String name, int full, int dt, Surrounding surrounding, int id){
        this.name = name;
        this.full = full;
        this.dt = dt;
        this.surrounding = surrounding;
        distributorQueue = new LinkedList<>();
        this.isDistributorFree = true;
        this.id = id;
    }
}

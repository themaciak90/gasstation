import java.util.LinkedList;
import java.util.List;

public class Carwash {
    LinkedList<Car> carwashQueue;
    public static boolean isFree;
    double washTime;

    public Carwash(){
        this.carwashQueue = new LinkedList<>();
        this.isFree = true;
    }
}

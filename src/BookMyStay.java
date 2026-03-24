import java.util.*;

// -------------------- Service Class --------------------
class OrderService {

    public String placeOrder(String item) {
        return "Order placed for " + item;
    }
}

// -------------------- Controller Class --------------------
class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    public void createOrder(String item) {
        String result = service.placeOrder(item);
        System.out.println(result);
    }
}

// -------------------- Logger (Singleton) --------------------
class Logger {

    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}

// -------------------- Observer Pattern --------------------
interface Observer {
    void update(String message);
}

class EmailService implements Observer {
    public void update(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSService implements Observer {
    public void update(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class NotificationService {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void notifyObservers(String message) {
        for (Observer obs : observers) {
            obs.update(message);
        }
    }
}

// -------------------- Database Connection (Singleton) --------------------
class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connected to database");
    }
}

// -------------------- MAIN CLASS --------------------
public class MainApp {

    public static void main(String[] args) {

        // Service & Controller
        OrderService service = new OrderService();
        OrderController controller = new OrderController(service);
        controller.createOrder("Laptop");

        // Logger
        Logger logger = Logger.getInstance();
        logger.log("Order created successfully");

        // Observer
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new EmailService());
        notificationService.addObserver(new SMSService());
        notificationService.notifyObservers("Order shipped");

        // Database
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();
    }
}

import java.util.*;

// ---------------------- OBSERVER INTERFACE ----------------------
interface Observer {
    void update(String message);
}

// ---------------------- SUBJECT INTERFACE ----------------------
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message);
}

// ---------------------- CONCRETE SUBJECT ----------------------
class BookingSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    // Simulate booking confirmation
    public void confirmBooking(String userName, String service) {
        String message = "Booking confirmed for " + userName + " (" + service + ")";
        notifyObservers(message);
    }
}

// ---------------------- CONCRETE OBSERVERS ----------------------
class EmailService implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSService implements Observer {
    @Override
    public void update(String message) {
        System.out.println("SMS sent: " + message);
    }
}

// ---------------------- MAIN CLASS ----------------------
public class BookingNotificationSystem {
    public static void main(String[] args) {

        BookingSystem bookingSystem = new BookingSystem();

        // Observers
        Observer emailService = new EmailService();
        Observer smsService = new SMSService();

        // Register observers
        bookingSystem.registerObserver(emailService);
        bookingSystem.registerObserver(smsService);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter service (Hotel/Flight/Cab): ");
        String service = scanner.nextLine();

        // Confirm booking → triggers notifications
        bookingSystem.confirmBooking(userName, service);

        scanner.close();
    }
}

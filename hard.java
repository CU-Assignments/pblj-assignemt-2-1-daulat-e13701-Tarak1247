import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    public void bookSeat(int seatNumber, String customerName) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(customerName + " attempted to book an invalid seat.");
                return;
            }
            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(customerName + " successfully booked seat " + seatNumber);
            } else {
                System.out.println(customerName + " attempted to book an already reserved seat " + seatNumber);
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerName;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(10);
        
        Thread vip1 = new BookingThread(bookingSystem, 3, "VIP_John", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(bookingSystem, 4, "VIP_Alice", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(bookingSystem, 3, "Regular_Bob", Thread.NORM_PRIORITY);
        Thread regular2 = new BookingThread(bookingSystem, 5, "Regular_Emma", Thread.NORM_PRIORITY);
        
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
    }
}

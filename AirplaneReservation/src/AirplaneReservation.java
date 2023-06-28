import java.sql.SQLOutput;
import java.util.Scanner;

// Abstract class representing a reservation
abstract class Reservation {
    protected Flight flight;
    protected Passenger passenger;

    // Constructor
    public Reservation(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    // Abstract method to get the reservation details
    public abstract String getReservationDetails();

    // Getters
    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}

// Interface representing a booking
interface Bookable {
    void book(String bookingNumber);
}

// Interface representing seat availability
interface SeatAvailability {
    int getAvailableSeats();

    int getTotalSeats();
}

// Flight class representing a single flight
class Flight {
    private String flightNumber;
    private String source;
    private String destination;

    // Constructor
    public Flight(String flightNumber, String source, String destination) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
    // Method to display flight timings
    public void flightMenu() {
        System.out.println("\nFlight Timings:");
        System.out.println("......................");
        System.out.println("1: Timings for PIA flight are from 10am to 12am");
        System.out.println("......................");
        System.out.println("2: Timings for FlyDubai flight are from 7am to 9am");
        System.out.println("......................");
        System.out.println("3: Timings for Emirates flight are from 11am to 6pm");
        System.out.println("......................");
        System.out.println("4: Timings for AirCanada flight are from 1am to 4am");

        System.out.println("Please enter the number of your preferred flight timing.");
    }
}

// Passenger class representing a passenger
class Passenger {
    private String name;
    private int age;

    // Constructor
    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Payment{
    double bal;
    double prevTrans;
    String customerName;
    String customerId;

    Payment(String customerName,String customerId){
        this.customerName=customerName;
        this.customerId=customerId;
    }


    void deposit(double amount){
        if(amount!=0){
            bal+=amount;
            prevTrans=amount;
        }
    }

    void withdraw(double amt){
        if(amt!=0 && bal>=amt){
            bal-=amt;
            prevTrans=-amt;
        }
        else if(bal<amt){
            System.out.println("Bank balance insufficient");
        }
    }

    void getPreviousTrans(){
        if(prevTrans>0){
            System.out.println("Deposited: "+prevTrans);
        }
        else if(prevTrans<0){
            System.out.println("Withdrawn: "+Math.abs(prevTrans));
        }
        else{
            System.out.println("No transaction occured");
        }
    }

    void menu1(){
        char option;
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome "+customerName);
        System.out.println("Your ID:"+customerId);
        System.out.println("\n");
        System.out.println("a) Check Balance");
        System.out.println("b) Deposit Amount");
        System.out.println("c) Withdraw Amount");
        System.out.println("d) Previous Transaction");
        System.out.println("e) Exit");

        do{
            System.out.println("****************");
            System.out.println("Choose an option");
            option=sc.next().charAt(0);
            System.out.println("\n");

            switch (option){
                case 'a':
                    System.out.println("......................");
                    System.out.println("Balance ="+bal);
                    System.out.println("......................");
                    System.out.println("\n");
                    break;
                case 'b':
                    System.out.println("......................");
                    System.out.println("Enter a amount to deposit :");
                    System.out.println("......................");
                    double amt=sc.nextDouble();
                    deposit(amt);
                    System.out.println("\n");
                    break;
                case 'c':
                    System.out.println("......................");
                    System.out.println("Enter a amount to Withdraw :");
                    System.out.println("......................");
                    double amtW=sc.nextDouble();
                    withdraw(amtW);
                    System.out.println("\n");
                    break;
                case 'd':
                    System.out.println("......................");
                    System.out.println("Previous Transaction:");
                    getPreviousTrans();
                    System.out.println("......................");
                    System.out.println("\n");
                    break;

                case 'e':
                    System.out.println("......................");
                    break;
                default:
                    System.out.println("Choose a correct option to proceed");
                    break;
            }

        }while(option!='e');

        System.out.println("Thank you for using our transaction services");
    }

}
// Booking class representing a booking made by a passenger for a flight
class Booking extends Reservation implements Bookable {
    private String bookingNumber;

    // Constructor
    public Booking(Flight flight, Passenger passenger, String bookingNumber) {
        super(flight, passenger);
        this.bookingNumber = bookingNumber;
    }

    // Getter and setter
    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    // Implementation of the Bookable interface
    @Override
    public void book(String bookingNumber) {
        setBookingNumber(bookingNumber);
    }

    // Implementation of the getReservationDetails abstract method
    @Override
    public String getReservationDetails() {
        return "Booking Number: " + bookingNumber +
                "\nFlight Number: " + flight.getFlightNumber() +
                "\nSource: " + flight.getSource() +
                "\nDestination: " + flight.getDestination() +
                "\nPassenger: " + passenger.getName() +
                "\nPassenger Age: " + passenger.getAge();
    }
    public void assistance() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Would you like to load extra baggage?");
        System.out.println("2. Do you need extra assistance at airport i.e wheelchair?");
        System.out.println("3. Exit");

        System.out.println("Please enter the number for required option.");

        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Extra baggage service requested. We will contact you with more details.");
                break;
            case 2:
                System.out.println("Wheelchair service requested. We will contact you with more details.");
                break;
            case 3:
                System.out.println("Exiting assistance options.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                assistance();
                break;
        }
    }
}

// Seats class representing the seats on a flight
class Seats implements SeatAvailability {
    private int totalSeats;
    private int availableSeats;

    // Constructor
    public Seats(int totalSeats, int availableSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    // Getters and setters
    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

// Main class to demonstrate the usage of the reservation and booking system
public class AirplaneReservation {
    public static void main(String[] args) {
        // Create flight
        Flight flight = new Flight("ABC123", "City A", "City B");

        // Create scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Get passenger details from user
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        System.out.print("Enter passenger age: ");
        int passengerAge = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        // Create passenger
        Passenger passenger = new Passenger(passengerName, passengerAge);

        // Get booking details from user
        System.out.print("Enter booking number: ");
        String bookingNumber = scanner.nextLine();

        // Create booking
        Booking booking = new Booking(flight, passenger, bookingNumber);

        // Create seats
        Seats seats = new Seats(100, 90);

        flight.flightMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        switch (choice) {
            case 1:
                flight = new Flight("ABC123", "City A", "City B");
                break;
            case 2:
                flight = new Flight("ABC123", "City A", "City C");
                break;
            case 3:
                flight = new Flight("ABC123", "City A", "City D");
                break;
            default:
                System.out.println("Invalid choice. Please run the program again.");
                return;
        }

        // Display booking, seats, and reservation details
        System.out.println("\nBooking Details:");
        System.out.println(booking.getReservationDetails());
        System.out.println("Seats Available: " + seats.getAvailableSeats() + "/" + seats.getTotalSeats());
        booking.assistance();

        Scanner sc=new Scanner(System.in);
        System.out.println("Please proceed to pay to confirm your registration:");
        System.out.println("Enter your Account name and Bank CustomerId to access your Account");
        String name=sc.nextLine();
        String customerId=sc.nextLine();
        Payment obj1=new Payment(name,customerId);
        obj1.menu1();

    }
}

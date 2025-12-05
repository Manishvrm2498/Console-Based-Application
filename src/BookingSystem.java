import java.io.File;
import java.io.PrintWriter;
import java.util.*;


public class BookingSystem {
    private HashMap<String, Booking> bookings;
    private HashMap<Integer, TrainDetails> trains;
    private Scanner input;
    private final String adminUsername = "admin";
    private final String adminPassword = "admin1234";

    
    public BookingSystem() {
        trains = new HashMap<>();
        bookings = new HashMap<>();
        input = new Scanner(System.in);

        initializeSampleData();
    }

    private void initializeSampleData() {
        TrainDetails t1 = new TrainDetails(10121, "Rajdhani Express", "Delhi", "Mumbai", 50,23, 900.00);
        TrainDetails t2 = new TrainDetails(12345, "Shatabdi Exp", "Mumbai", "Pune", 30,21, 560.00);
        TrainDetails t3 = new TrainDetails(98765, "Kolkata Mail", "Delhi", "Kolkata", 60,22, 320.00);

        trains.put(t1.getTrainNumber(), t1);
        trains.put(t2.getTrainNumber(), t2);
        trains.put(t3.getTrainNumber(), t3);

        List<Passenger> pList1 = new ArrayList<>();
        pList1.add(new Passenger("John Doe", 30, "M"));
        List<Passenger> pList2 = new ArrayList<>();
        pList2.add(new Passenger("Mohan", 24, "M"));

        Booking b1 = new Booking(10121, pList1, t1.getFare() * 1);
        Booking b2 = new Booking(12345, pList2, t2.getFare() * 1);

        bookings.put(b1.getBookingId(), b1);
        bookings.put(b2.getBookingId(), b2);

        t1.setAvailableSeats(t1.getAvailableSeats() - 1);
    }


    public void displayMainMenu() {
        System.out.println("\n*** Welcome to Railway Ticket Booking System ***");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Admin Menu");
        System.out.println("2. Search Trains");
        System.out.println("3. Book Tickets");
        System.out.println("4. Cancel Tickets");
        System.out.println("5. View Booking by ID");
        System.out.println("6. Exit");

        int choice = 0;
        while (true) {
            System.out.print("Enter your choice (1-6): ");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                System.out.println();
            } else {
                System.out.println("\nInvalid input. Please enter a number from the menu.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    searchTrain();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    cancelTicket();
                    break;
                case 5:
                    viewBooking();
                    break;
                case 6:
                    System.out.println(" Thank you for using the Railway Ticket Booking System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.print("Enter Username:");
            String username = input.nextLine();
            System.out.print("Enter Password:");
            String password = input.nextLine();
            if (username.equals(adminUsername) && password.equals(adminPassword)) {
                System.out.println("Login Successful!");
                break;
            }
            System.out.println("Invalid username or password!");
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--------ADMIN DASHBOARD--------");
            System.out.println("1. Add Train");
            System.out.println("2. View Trains");
            System.out.println("3. View Bookings");
            System.out.println("4. Back to main menu");

            System.out.print("\nEnter your choice(1-4): ");
            int choice = -1;
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println(" Invalid choice. Please enter a number.");
                input.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addTrain();
                    break;
                case 2:
                    viewAllTrains();
                    break;
                case 3:
                    viewAllBookings();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid admin choice.");
            }
        }
    }

    private void addTrain() {
        System.out.println("\n---Add New Train---");
        int trainNumber;
        while (true) {
            System.out.print("Enter Train Number:");
            if (input.hasNextInt()) {
                trainNumber = input.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }

        if (trains.containsKey(trainNumber)) {
            System.out.println("Train with number " + trainNumber + " already exists!");
            return;
        }

        String tName;
        while (true) {
            System.out.print("Enter Train Name: ");
                tName = input.nextLine();

            if (tName.trim().isEmpty()) {
                    System.out.println("\nThis field cannot be empty.");
            } else {
               break;
            }
        }


        System.out.print("Enter Source Station:");
        String sourceS = input.nextLine();

        System.out.print("Enter Destination Station:");
        String destinationS = input.nextLine();

        int totalSeats;

        while (true) {
            System.out.print("Enter Total Seats (1-1000): ");

            if (input.hasNextInt()) {
                totalSeats = input.nextInt();
                input.nextLine();

                if (totalSeats >= 1 && totalSeats <= 1000) {
                    break;
                } else {
                    System.out.println("Total Seats must be between 1 and 1000.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }

        int availableSeats;
        while (true) {
            System.out.print("Enter Available Seats:");
            if (input.hasNextInt()) {
                availableSeats = input.nextInt();
                input.nextLine();
                break;
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }

            double fare;
            while (true) {
            System.out.print("Enter Fare:");
            if (input.hasNextDouble()) {
                fare = input.nextDouble();
                input.nextLine();
                if (fare >= 0) {
                    break;
                } else {
                    System.out.println("Total Seats must be greater than 0.");
                }
            }else  {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }


        TrainDetails newTrain = new TrainDetails(trainNumber, tName, sourceS, destinationS, totalSeats,availableSeats, fare);
        trains.put(trainNumber, newTrain);
        System.out.println("Train " + trainNumber + " - " + tName + " added successfully.");
    }


    private void viewAllTrains() {
        if (trains.isEmpty()) {
            System.out.println("No Trains in the System");
            return;
        }
        System.out.println("\n                     All Trains in the System");
        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.printf("%-12s | %-30s | %-15s | %-15s | %-10s%n",
                "Train No", "Train Name","Total Seats", "Available Seats", "Fare (INR)");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (TrainDetails t : trains.values()) {
            System.out.println(t.displayTrainDetails());
        }
        System.out.println("----------------------------------------------------------------------------------------------");

    }

    private void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No Bookings in the System");
        }
        System.out.println("\n         All Bookings in the System         ");
        for (Booking b : bookings.values()) {
            System.out.println(b.toString());
        }
    }

    private void searchTrain() {
        System.out.println("\n*** Search Train ***");
        System.out.print("Enter Source Station:");
        String sourceS = input.nextLine();
        System.out.print("Enter Destination Station:");
        String destinationS = input.nextLine();
        if (sourceS.equalsIgnoreCase(destinationS)) {
            System.out.println("Source and destination cannot be the same.");
            return;
        }
        List<TrainDetails> foundTrains = new ArrayList<>();
        for (TrainDetails t : trains.values()) {
            if (t.getSourceStation().equalsIgnoreCase(sourceS) && t.getDestinationStation().equalsIgnoreCase(destinationS)) {
                foundTrains.add(t);
            }
        }
        if (foundTrains.isEmpty()) {
            System.out.println("No trains found for the route: " + sourceS + " -> " + destinationS + ".");
            return;
        }
        System.out.println("\nAvailable Trains for " + sourceS + " -> " + destinationS + ":");
        System.out.printf("%-10s %-20s %-15s %s\n", "TrainNo.", "Train Name", "Seats Available.", "Fare");
        System.out.println("-------------------------------------------------------");
        for (TrainDetails train : foundTrains) {
            System.out.printf("%-10d %-20s %-15d INR%.2f\n",
                    train.getTrainNumber(), train.getTrainName(),
                    train.getAvailableSeats(), train.getFare());
        }
    }

    private void bookTicket() {

        System.out.println("\n*** Book Ticket ***");
        System.out.print("Enter Train Number to Book: ");

        int trainNumber;
        if (input.hasNextInt()) {
            trainNumber = input.nextInt();
            input.nextLine();
        } else {
            System.out.println("Please enter a valid train number.");
            input.nextLine();
            return;
        }

        TrainDetails t = trains.get(trainNumber);
        if (t == null) {
            System.out.println("Train with number " + trainNumber + " does not exist!");
            return;
        }

        int numPassengers;
        while (true) {
            System.out.print("Enter Number of Passengers: ");
            if (input.hasNextInt()) {
                numPassengers = input.nextInt();
                input.nextLine();
                if (numPassengers >= 1) break;
                else System.out.println("Number of passengers must be at least 1.");
            } else {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }

        if (numPassengers > t.getAvailableSeats()) {
            System.out.println("Only " + t.getAvailableSeats()
                    + " seats available. Cannot book " + numPassengers + " seats.");
            return;
        }

        List<Passenger> pList = new ArrayList<>();

        for (int i = 1; i <= numPassengers; i++) {

            System.out.println("\nPassenger " + i + " Details:");

            String name;
            while (true) {
                System.out.print("Name: ");
                name = input.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                } else if (name.length() > 100) {
                    System.out.println("Name is too long! Maximum 100 characters allowed.");
                } else {
                    break;
                }
            }

            int age;
            while (true) {
                System.out.print("Age: ");
                if (input.hasNextInt()) {
                    age = input.nextInt();
                    input.nextLine();
                    if (age >= 1 && age <= 120) break;
                    else System.out.println("Age must be between 1 and 120.");
                } else {
                    System.out.println("Enter a valid Age.");
                    input.nextLine();
                }
            }

            String gender;
            while (true) {
                System.out.print("Gender (M/F/Other): ");
                gender = input.nextLine().trim();

                if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Other")) {
                    break;
                } else {
                    System.out.println("Invalid Gender. Please enter M, F, or Other.");
                }
            }

            pList.add(new Passenger(name, age, gender));
        }

        double totalFare = numPassengers * t.getFare();

        Booking newBooking = new Booking(trainNumber, pList, totalFare);
        bookings.put(newBooking.getBookingId(), newBooking);

        t.deductSeats(numPassengers);

        System.out.println("\n===== Booking Successful =====");
        System.out.println(newBooking);
    }


    private void cancelTicket() {

        System.out.println("\n*** Cancel Ticket ***");
        System.out.println("Enter Booking ID to Cancel: ");
        String id = input.nextLine().trim();
        Booking b = bookings.get(id);

        if (b == null) {
            System.out.println("Booking with ID " + id + " not found!");
            return;
        }
        if (b.getStatus().equalsIgnoreCase("cancelled")) {
            System.out.println("This Booking is already cancelled!");
            return;
        }

        System.out.println("\nBooking ID Confirmed. Please review details before canceling:");
        System.out.println(b);
        System.out.print("\nConfirm cancellation? (Yes/No): ");
        String confirm = input.nextLine().trim();

        if (confirm.equalsIgnoreCase("Yes")) {
            TrainDetails t = trains.get(b.getTrainNumber());
            int cancelSeat = b.getPassengerList().size();
            b.setStatus("Cancelled");

            if (t != null) {
                t.increaseSeats(cancelSeat);
            }
            System.out.println("Booking " + b.getBookingId() + " cancelled Successful!.");
            System.out.println(cancelSeat + " seats have been released for Train " + b.getTrainNumber() + ".");
            bookings.remove(b.getBookingId());

        } else {
            System.out.println("Booking " + b.getBookingId() + " cancelled Unsuccessful!");
        }
    }


    private void viewBooking() {
        System.out.print("Enter Booking ID: :");
        String  bookingId = input.nextLine();
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking with ID " + bookingId + " not found!");
            return;
        }
        System.out.println("\n*** View Booking ***");
            System.out.println(b);
    }


    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();
        bookingSystem.displayMainMenu();
    }
}




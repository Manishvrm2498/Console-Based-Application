
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Booking {

    private static int nextBookingId = 1000;
    private final String bookingId;

    private int trainNumber;
    private List<Passenger> passengerList;
    private TrainDetails trains;

    private double totalFare;
    private String status;


    public Booking(int trainNumber, List<Passenger> passengerList, double totalFare) {
        this.bookingId = "BOOK" + nextBookingId++;
        this.trainNumber = trainNumber;
        this.passengerList = passengerList;
        this.totalFare = totalFare;
        this.status = "Confirmed";
    }

    public Booking(String bookingId, int trainNumber, List<Passenger> passengerList, double totalFare, String status) {
        this.bookingId = bookingId;
        this.trainNumber = trainNumber;
        this.passengerList = passengerList;
        this.totalFare = totalFare;
        this.status = status;

        try {
            int num = Integer.parseInt(bookingId.substring(1));
            if (num >= nextBookingId) nextBookingId = num + 1;
        } catch (Exception e) {

        }
    }
    public int getTrainNumber() {
        return trainNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void cancel() {
        status = "Cancelled";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("=====================================================\n");
        sb.append(String.format("%-20s : %s\n", "Booking ID", this.bookingId));
        sb.append(String.format("%-20s : %d\n", "Train Number", this.trainNumber));
        sb.append(String.format("%-20s : %s\n", "Status", this.status));
        sb.append(String.format("%-20s : INR %.2f\n", "Total Fare", this.totalFare));
        sb.append("----------------------------------------------------\n");

        sb.append(String.format("%-15s %-5s %-10s\n", "Name", "Age", "Gender"));
        sb.append("----------------------------------------------------\n");

        for (Passenger p : passengerList) {
            sb.append(String.format("%-15s %-5d %-10s\n",
                    p.getName(),
                    p.getAge(),
                    p.getGender()));
        }

        sb.append("=====================================================\n");
        return sb.toString();
    }

}




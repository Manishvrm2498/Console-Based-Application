public class TrainDetails {

    private int trainNumber;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
    private int totalSeats;
    private   int availableSeats;
    private double fare;

    public TrainDetails(int trainNumber, String trainName, String sourceStation, String destinationStation, int totalSeats,int availableSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }
    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public void deductSeats(int n) {
        availableSeats -= n;
    }
    public void increaseSeats(int n) {
        availableSeats += n;

    }

    public String displayTrainDetails() {
        return
                String.format("%-12d | %-30s | %-15d | %-15d | %-10.2f",
                        trainNumber, trainName,totalSeats, availableSeats, fare);
    }
}

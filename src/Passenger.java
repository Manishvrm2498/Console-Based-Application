public class Passenger {
    private static int nextPassengerID = 1;
    private int passengerID;
    private String name;
    private int  age;
    private String gender;

    public Passenger(String name, int age, String gender) {
        this.passengerID = nextPassengerID++;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public int getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String toString() {
        return String.format("[PNR-100%d] %s, Age: %d, Gender: %s",
                passengerID, name, age, gender);
    }
}

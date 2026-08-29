class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Main constructor
    public FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor with fare
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    // Provisional constructor
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (totalFare == 0) {
            return result;
        }

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;

        double assigned = 0;

        for (int i = 0; i < passengerCount - 1; i++) {
            result[i] = share;
            assigned += share;
        }

        // Give remaining paisa to the LAST passenger
        result[passengerCount - 1] =
                Math.round((totalFare - assigned) * 100) / 100.0;

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter f1 =
                new FareSplitter("TRIP001", 100000, 3);

        double[] breakdown = f1.fareBreakdown();

        for (double amount : breakdown) {
            System.out.println(amount);
        }

        FareSplitter f2 =
                new FareSplitter("TRIP003");

        double[] provisional = f2.fareBreakdown();

        for (double amount : provisional) {
            System.out.println(amount);
        }
    }
}
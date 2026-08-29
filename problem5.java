class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    // Class-level state
    protected static double penaltyRate;

    // Static block
    static {
        penaltyRate = 1.0;
    }

    // Full constructor
    public BusTicketAccount(String bookingId, double ticketFare) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
            );
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    // Provisional constructor
    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
            );
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        return ticketFare *
                penaltyRate / 100.0 *
                minutesLate;
    }

    public void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            return;
        }

        double penalty = account.calculatePenalty(minutesLate);

        System.out.println(
                "Booking: " + account.bookingId
        );

        System.out.println(
                "Amount: " + amount
        );

        System.out.println(
                "Penalty: " + penalty
        );
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null) {

            throw new IllegalArgumentException(
                    "Arrays cannot be null"
            );
        }

        // Use only the matching portion
        int length = Math.min(
                accounts.length,
                Math.min(
                        amounts.length,
                        minutesLateArray.length
                )
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotalPenalty = 0.0;

        for (int i = 0; i < length; i++) {

            BusTicketAccount account = accounts[i];

            // Null safety
            if (account == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    account.calculatePenalty(
                            minutesLateArray[i]
                    );

            grandTotalPenalty += penalty;

            processed++;

            // instanceof dispatch
            if (account instanceof Sleeper) {

                sleeperCount++;

                System.out.println(
                        "Sleeper account processed"
                );

            } else {

                regularCount++;

                System.out.println(
                        "Regular account processed"
                );
            }
        }

        System.out.println(
                processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeperCount + " sleeper | " +
                regularCount + " regular"
        );

        System.out.println(
                "Grand total penalties = " +
                grandTotalPenalty
        );
    }
}


// Sleeper account
class Sleeper extends BusTicketAccount {

    public Sleeper(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}


    class Reservation {

        int reservationId;
        String guestName;
        int roomNumber;

        public Reservation(int reservationId, String guestName, int roomNumber) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomNumber = roomNumber;
        }
    }


    /**
     * ============================================================
     * CLASS - BookingException
     * ============================================================
     * Custom exception for booking errors.
     */

    class BookingException extends Exception {

        public BookingException(String message) {
            super(message);
        }
    }


    /**
     * ============================================================
     * CLASS - BookingProcessor
     * ============================================================
     * Handles reservation processing and validation.
     */

    class BookingProcessor {

        public void processReservation(Reservation r) throws BookingException {

            // Simulated room availability check
            if (r.roomNumber == 101) {
                throw new BookingException("Room already booked");
            }

            System.out.println("Processing booking for Guest: " + r.guestName +
                    ", Room: " + r.roomNumber);
        }
    }


    /**
     * ============================================================
     * MAIN CLASS - UseCase5BookingEngineDemo
     * ============================================================
     */

    public class UseCase5BookingEngineDemo {

        public static void main(String[] args) {

            BookingProcessor processor = new BookingProcessor();

            try {

                Reservation r1 = new Reservation(1, "Neha", 101);
                processor.processReservation(r1);

            } catch (BookingException e) {

                System.out.println("Booking failed: " + e.getMessage());

            }

            try {

                Reservation r2 = new Reservation(2, "Rahul", 102);
                processor.processReservation(r2);

            } catch (BookingException e) {

                System.out.println("Booking failed: " + e.getMessage());
            }
        }
    }








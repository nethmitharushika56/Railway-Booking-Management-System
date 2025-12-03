package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Mavenproject1 {

    // ------------------ Ticket Officer ------------------
    public class TicketOfficer {
        private int officerID;
        private String officerName;

        public int getOfficerID() { return officerID; }
        public String getOfficerName() { return officerName; }
        public void setOfficerID(int officerID) { this.officerID = officerID; }
        public void setOfficerName(String officerName) { this.officerName = officerName; }
    }

    // ------------------ Customer ------------------
    public class Customer {
        private int NIC;
        private String Name;
        private int ContactNo;
        private int NoofSeats;

        public int getNIC() { return NIC; }
        public String getName() { return Name; }
        public int getContactNo() { return ContactNo; }
        public int getNoofSeats() { return NoofSeats; }

        public void setNIC(int NIC) { this.NIC = NIC; }
        public void setName(String Name) { this.Name = Name; }
        public void setContactNo(int ContactNo) { this.ContactNo = ContactNo; }
        public void setNoofSeats(int NoofSeats) { this.NoofSeats = NoofSeats; }
    }

    // ------------------ Ticket Booking ------------------
    public class TicketBooking {
        private int bookingID;
        Customer customer;
        String route;
        String trainNo;
        String time;
        int noOfSeats;
    }

    // ------------------ Booking System ------------------
    public class BookingSystem extends TicketOfficer {
        List<String> timetables = new ArrayList<>();
        List<TicketBooking> bookings = new ArrayList<>();

        // Load timetable files
        public void loadFromFile(String fileName) throws Exception {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                timetables.add(line);
            }
            br.close();
        }

        public void loadThreeFiles() throws Exception {
            loadFromFile("src/main/resources/timetable1.txt");
            loadFromFile("src/main/resources/timetable2.txt");
            loadFromFile("src/main/resources/timetable3.txt");
        }

        // validation
        public boolean isTrainValid(String route, String time, String trainNo) {
        for (String line : timetables) {
        String[] p = line.trim().split("\\s+");
        if (p.length < 3) continue;

        String fileRoute = p[0].trim();
        String fileTime = p[1].trim();
        String fileTrain = p[2].trim();

        // Debug print to see what is being compared
        System.out.println("Comparing: [" + route + "] [" + time + "] [" + trainNo + "] WITH [" +
                           fileRoute + "] [" + fileTime + "] [" + fileTrain + "]");

        if (fileRoute.equalsIgnoreCase(route.trim()) &&
            fileTime.equalsIgnoreCase(time.trim()) &&
            fileTrain.equalsIgnoreCase(trainNo.trim())) {
            System.out.println("MATCH FOUND!");
            return true;
        }
        }
        System.out.println("No match found!");
        return false;
}

        // Create booking
        public void createBooking(String route, String time, String trainNo, int seats, int id) {
            TicketBooking b = new TicketBooking();
            b.bookingID = id;
            b.route = route;
            b.time = time;
            b.trainNo = trainNo;
            b.noOfSeats = seats;

            bookings.add(b);
            System.out.println("Booking Added! ID = " + id);
        }

        // View all bookings
        public void viewAllBookings() {
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }
            for (TicketBooking b : bookings) {
                System.out.println(
                    "ID: " + b.bookingID +
                    " | Route: " + b.route +
                    " | Time: " + b.time +
                    " | Train: " + b.trainNo +
                    " | Seats: " + b.noOfSeats
                );
            }
        }

        // Cancel booking
        public void cancelBooking(int id) {
            for (TicketBooking b : bookings) {
                if (b.bookingID == id) {
                    bookings.remove(b);
                    System.out.println("Booking cancelled!");
                    return;
                }
            }
            System.out.println("Booking ID not found!");
        }

        // Change booking
        public void changeBooking(int id, String route, String time, String trainNo, int seats) {

            for (TicketBooking b : bookings) {
                if (b.bookingID == id) {

                    if (!isTrainValid(route, time, trainNo)) {
                        System.out.println("Invalid train details!");
                        return;
                    }

                    b.route = route;
                    b.time = time;
                    b.trainNo = trainNo;
                    b.noOfSeats = seats;

                    System.out.println("Booking updated!");
                    return;
                }
            }
            System.out.println("Booking ID not found!");
        }
    }

    // ------------------------------------------------------
    // MAIN MENU
    // ------------------------------------------------------
    public static void main(String[] args) {
        Mavenproject1 outer = new Mavenproject1();
        BookingSystem system = outer.new BookingSystem();
        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\n====== TRAIN BOOKING SYSTEM ======");
            System.out.println("1. Load Timetables");
            System.out.println("2. Make Booking");
            System.out.println("3. View Timetables");
            System.out.println("4. View Bookings");
            System.out.println("5. Change Booking");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            System.out.print("Enter option: ");

            int choice = sc.nextInt(); sc.nextLine();

            try {
                switch (choice) {

                    case 1:
                        system.loadThreeFiles();
                        System.out.println("Timetables Loaded!");
                        break;

                    case 2:
                        System.out.print("Enter Route: ");
                        String route = sc.nextLine();

                        System.out.print("Enter Time: ");
                        String time = sc.nextLine();

                        System.out.print("Enter Train No: ");
                        String train = sc.nextLine();

                        System.out.print("Enter Seats: ");
                        int seats = sc.nextInt();
                        sc.nextLine();

                        if (!system.isTrainValid(route, time, train)) {
                            System.out.println("Invalid Train!");
                        } else {
                            int id = system.bookings.size() + 1;
                            system.createBooking(route, time, train, seats, id);
                        }
                        break;

                    case 3:
                        for (String t : system.timetables) {
                            System.out.println(t);
                        }
                        break;

                    case 4:
                        system.viewAllBookings();
                        break;

                    case 5:
                        System.out.print("Enter Booking ID: ");
                        int id2 = sc.nextInt(); sc.nextLine();

                        System.out.print("Enter New Route: ");
                        String nr = sc.nextLine();

                        System.out.print("Enter New Time: ");
                        String nt = sc.nextLine();

                        System.out.print("Enter New Train No: ");
                        String ntr = sc.nextLine();

                        System.out.print("Enter New Seats: ");
                        int ns = sc.nextInt();

                        system.changeBooking(id2, nr, nt, ntr, ns);
                        break;

                    case 6:
                        System.out.print("Enter Booking ID: ");
                        int id3 = sc.nextInt();
                        system.cancelBooking(id3);
                        break;

                    case 7:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
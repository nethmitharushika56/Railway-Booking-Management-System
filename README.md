# Railway-Booking-Management-System
Design &amp; develop a console-based booking management system for Sri Lanka Railways (initially for express trains on Colombo–Kankasanturai, Colombo–Galle, Colombo–Kandy routes).

🔎 Project Summary

This console-based Java application (NetBeans project) is a Ticketing Officer-facing Booking Management System for Sri Lanka Railways. It supports booking, altering, and cancelling bookings through ticketing officers (customers cannot access the system directly). The system stores timetables and booking details in CSV/TXT files and reads/writes those files at runtime. No payment processing is included.

Main features implemented:

View train timetables for the selected routes

Create a booking (up to 4 seats per booking) — requires customer ID, name, contact number

Alter an existing booking (change seats where available)

Cancel a booking (make seats available again)

Prevent booking when seats for a train are fully booked

View all bookings for a particular route/train

Simple text-based menu interface for ticketing officers

✅ Included in this repository
23_ENG_140_01/
│
├── README.md                         # (this file)
├── NetBeansProject/                  # NetBeans project folder (source code)
│   └── src/
│       └── main/
│           └── java/
│               └── com/railway/...
│                   └── Main.java
│                   └── model/*.java
│                   └── io/*.java
│                   └── service/*.java
└──timetables.csv                    # initial timetable data (txt)


Fields explanation:

train_id — unique train code used by the system

route — textual route name (used for filtering)

total_seats / available_seats — integers used to check seat availability

booking_id — unique booking identifier etc.

⚙️ Assumptions (made for implementation)

System will be used only by Ticketing Officers (no direct public UI).

Booking limit per customer is max 4 seats.

Seats are numbered within a train; the train's total_seats is fixed.

Timetables are static txt files loaded at startup; admin tools to change timetables are out of scope.

No payment handling — booking records are created without payment confirmation.

Time fields are stored as HH:MM strings; dates use ISO YYYY-MM-DD where required.

Concurrency (simultaneous ticketing officers writing the same txt) is not handled (single-user assumption).


🧭 How to run the application
Option A — In NetBeans (recommended)

Open NetBeans.

File → Open Project... → navigate to NetBeansProject directory and open it.

Ensure JDK is configured (Java 8+ recommended).

Inside the project, find Main.java (class with public static void main) and run the project (Right-click → Run).

When the console launches, follow the menu to: view timetables, create/alter/cancel bookings, view bookings, exit.

Make sure timetables.csv and bookings.csv are either in the project working directory or the program is configured with the correct relative path. The program will create bookings.csv if it doesn't exist.

Option B — From command line

Compile:

cd NetBeansProject/src
javac -d ../bin $(find . -name "*.java")


(or javac each file; adjust paths for Windows)

Run:

java -cp ../bin com.mavemproject1.Main

The console menu will appear. Provide inputs using the keyboard.

🔄 Possible future improvements (for report / future work)

Add payment gateway integration and reservation timeouts.

Add concurrency-safe storage (database: MySQL / PostgreSQL) with transactions.

Web or mobile UI for public bookings with authentication.

Administrator module to manage schedules and seat maps.

Email/SMS notifications for booking confirmation and cancellations.

Seat layout visualization and seat-class pricing.

Unit tests and continuous integration.

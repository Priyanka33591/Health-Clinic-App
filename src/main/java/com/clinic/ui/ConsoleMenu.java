package com.clinic.ui;

import com.clinic.dto.Patient;
import com.clinic.service.PatientService;
import com.clinic.service.PatientServiceImpl;

import java.sql.Date;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final PatientService patientService = new PatientServiceImpl();

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n========== HEALTH CLINIC ==========");
            System.out.println("1. Register Patient");
            System.out.println("2. View Patient");
            System.out.println("3. View All Patients");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");

            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    viewPatient();
                    break;

                case 3:
                    viewAllPatients();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    running = false;
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice");

            }

        }


    }

    private void registerPatient() {

        System.out.print("First Name : ");
        String first = scanner.nextLine();

        System.out.print("Last Name : ");
        String last = scanner.nextLine();

        System.out.print("DOB (yyyy-mm-dd): ");
        Date dob = Date.valueOf(scanner.nextLine());

        System.out.print("Gender : ");
        String gender = scanner.nextLine();

        System.out.print("Phone : ");
        String phone = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        Patient patient = new Patient(
                first,
                last,
                dob,
                gender,
                phone,
                email,
                true
        );

        int id = patientService.registerPatient(patient);

        if (id > 0)
            System.out.println("Patient Registered Successfully. ID : " + id);
        else
            System.out.println("Registration Failed");

    }

    private void viewPatient() {

        System.out.print("Enter Patient ID : ");

        int id = scanner.nextInt();

        Patient patient = patientService.getPatientById(id);

        if (patient != null)
            System.out.println(patient);
        else
            System.out.println("Patient Not Found");

    }

    private void viewAllPatients() {

        patientService
                .getAllPatients()
                .forEach(System.out::println);

    }

    private void updatePatient() {

        System.out.print("Patient ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patientService.getPatientById(id);

        if (patient == null) {

            System.out.println("Patient Not Found");
            return;

        }

        System.out.print("New Phone : ");
        patient.setPhoneNumber(scanner.nextLine());

        System.out.print("New Email : ");
        patient.setEmail(scanner.nextLine());

        if (patientService.updatePatient(patient))
            System.out.println("Updated Successfully");
        else
            System.out.println("Update Failed");

    }


    private void deletePatient() {

        System.out.print("Patient ID : ");

        int id = scanner.nextInt();

        if (patientService.deletePatient(id))
            System.out.println("Deleted Successfully");
        else
            System.out.println("Delete Failed");

    }

}






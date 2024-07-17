package application;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String departmentName = scan.next();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		scan.nextLine();
		String workerName = scan.nextLine();
		System.out.print("Level: ");
		String workerLevel = scan.next();
		System.out.print("Base Salary: ");
		double workerBaseSalary = scan.nextDouble();
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerBaseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int n = scan.nextInt();
		
		for(int i=1 ; i <= n; i++) {
			System.out.println("Enter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			LocalDate contractDate = LocalDate.parse(scan.next(), dtf1);
			System.out.print("Value per hour: ");
			double contractValuePerHour = scan.nextDouble();
			System.out.print("Duration (hours): ");
			int contractDuration = scan.nextInt();
			HourContract contract = new HourContract(contractDate, contractValuePerHour, contractDuration);
			worker.addContract(contract);
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		YearMonth monthAndYear = YearMonth.parse(scan.next(), dtf2);
		int month = 1 + monthAndYear.getMonthValue();
		int year = monthAndYear.getYear();
		System.out.println("Name: " +  worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
		
		scan.close();
	}

}

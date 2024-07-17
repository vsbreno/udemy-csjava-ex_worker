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

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/yyyy");

		System.out.print("Enter department's name: ");
		String departmentName = scan.next();
		Department department = new Department(departmentName);

		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.print("Level: ");
		String level = scan.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = scan.nextDouble();
		Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, department);

		System.out.print("How many contracts to this worker? ");
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.println("Enter the contract #" + (i + 1) + " data:");
			System.out.print("Date (dd/MM/yyyy): ");
			scan.nextLine();
			LocalDate ldt = LocalDate.parse(scan.nextLine(), dtf1);
			System.out.print("Value per hour: ");
			double valuePerHour = scan.nextDouble();
			System.out.print("Duration (hours): ");
			int duration = scan.nextInt();

			HourContract contract = new HourContract(ldt, valuePerHour, duration);
			worker.addContract(contract);
		}

		System.out.println();
		System.out.print("Enter the month and year to calculate income (MM/yyyy): ");
		scan.nextLine();
		String yM = scan.nextLine();
		YearMonth yearMonth = YearMonth.parse(yM, dtf2);
		int monthValue = 1 + yearMonth.getMonthValue();
		int yearValue = yearMonth.getYear();

		for (int i = 0; i < worker.getContracts().size(); i++) {
			if (yearValue == worker.getContracts().get(i).getDate().getYear()
					&& monthValue == 1 + worker.getContracts().get(i).getDate().getMonthValue()) {
				System.out.println(worker);
				System.out.print("Income for " + yearMonth.format(dtf2) + ": ");
				System.out.println(String.format("%.2f", worker.income(yearValue, monthValue)));
				break;
			}
		}

		scan.close();
	}

}

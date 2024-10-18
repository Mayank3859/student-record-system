/*CBA department is interested in keeping track of information about majors. 
 * Design a data structure that will maintain useful information for your department. 
 * The roster of majors, of course, should be ordered by last name (and then by first, 
 * if there are multiple students with the same last name)*/

package main;

import java.util.Scanner;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static Methods method = new Methods();
	static String ans;
	
	static void insert() {
		Student stud = new Student();
		System.out.println("\n------------------------------Add Student Record------------------------------");
		System.out.print("Enter Name: ");
		stud.name = scan.nextLine();
		System.out.print("Enter Address: ");
		stud.address = scan.nextLine();
		System.out.print("Enter Major: ");
		stud.major = scan.nextLine();
		System.out.print("Enter Marks: ");
		stud.grades = scan.nextInt();
		scan.nextLine();
		Methods.insertRec(stud);
		Methods.total++;
		System.out.println("Recorded...");
	}
	
	static void search() {
		String name;
		System.out.println("\n--------------------------------Search Record---------------------------------");
		System.out.print("Enter the student name: ");
		name = scan.nextLine();
		
		if(method.searchRec(name)) {
			display(name);
		} else {
			System.out.println("Record doesn't exist");
		}
	}
	
	static void delete() {
		String name;
		Methods.total--;
		System.out.println("\n--------------------------------Delete Record---------------------------------");
		System.out.print("Enter the student name: ");
		name = scan.nextLine();
		method.deleteRec(name);
		System.out.println("Record deleted...");
	}

	static void display(String ans) {
		System.out.println("\n------------------------------Student Records---------------------------------");
		System.out.println(" -----------------------------------------------------------------------------");
		System.out.printf("| %-20.20s| %-15.15s| %-15.15s| %-20.20s|%n", "NAME", "GRADES", "MAJOR", "ADDRESS");
		System.out.println(" -----------------------------------------------------------------------------");
		display(Methods.student, ans);
		System.out.println(" -----------------------------------------------------------------------------");
	}
	
	static void display(StudentTree stud, String n) {
		if(stud == null) return;
		display(stud.left, n);
		if((n.equalsIgnoreCase("list")&& stud.student != null)|| n.compareToIgnoreCase(stud.student.name)==0 || n.compareToIgnoreCase(stud.student.major)==0) {
			System.out.printf("| %-20.20s| %-15.15s| %-15.15s| %-20.20s|%n", 
			stud.student.name, stud.student.grades, stud.student.major, stud.student.address);
		} 
		display(stud.right, n);
	}
	
	static void reformat() {
		System.out.println("\nAre you sure you want to reformat records(YES/NO)?");
		ans = scan.nextLine();
		if(ans.equalsIgnoreCase("yes")) {
			Methods.student = null;
			Methods.total = 0;
			System.out.println("Records reformated...");
		}
	}
	
	public static void main(String[] args) {
		FileHandler.recover();
		boolean exit = false;
		while(!exit) {
			String choice;
			System.out.print("\n--------------------------Student Management System---------------------------\n"
					+"1. Add Student Record\n"
					+"2. Display Record\n"
					+"3. Search Record\n"
					+"4. Delete Record\n"
					+"5. Reformat\n"
					+"6. Save & Exit\n"
					+"Enter: ");
			choice = scan.nextLine();
			switch(choice) {
				case "1":
					insert();
					break;
				case "2":
					System.out.print("\nEnter Major(write \"list\" to display all records):");
					ans = scan.nextLine();
					display(ans);
					break;
				case "3":
					search();
					break;
				case "4":
					delete();
					break;
				case "5":
					reformat();
					break;
				case "6":
					FileHandler.save();
					exit = true;
					System.out.println("Saved...");
					break;
				default:
					System.out.println("Please enter a valid number");
			}
		}
		
	}	
}

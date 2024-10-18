package main;

public class StudentTree {
	Student student;
	StudentTree left;
	StudentTree right;
	
	StudentTree(Student student) {
		this.student = student;
		left = right = null;
	}
}

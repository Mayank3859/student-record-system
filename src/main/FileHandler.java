package main;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
	static int pos = 0;
	
	static File file = new File("StudentList.txt");
	
	static void ObjToArr(StudentTree stud, Student s[]) {
		if(stud == null || Methods.total == 0) return;
		ObjToArr(stud.left, s);
		s[pos] = stud.student;
		pos++;
		ObjToArr(stud.right, s);
	}

	static void save() {
		Student s[] = new Student[Methods.total];
		ObjToArr(Methods.student, s);
		try {
		    FileWriter myWriter = new FileWriter(file);
		    myWriter.write(Methods.total + "\n");
		    for(int i = 0; i < s.length; i++) {
		    	myWriter.write(s[i].name + "\n");
		    	myWriter.write(s[i].address + "\n");
		    	myWriter.write(s[i].grades + "\n");
		    	myWriter.write(s[i].major + "\n");
		    }
		   myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	static void recover() {
		int i = 1;
		try {
		      if(file.length() == 0) return;
		      Scanner myReader = new Scanner(file);
		      Methods.total = Integer.valueOf(myReader.nextLine());
		      String[] line = new String[Methods.total * 4 + 1];
		      while (myReader.hasNextLine()) {
		        	line[i] = myReader.nextLine();
		        	i++;
		      }
		      for(int x = 1; x < line.length; x+=4) {
		    	  	Student st = new Student();
					st.name = line[x];
					st.address = line[x+1];
					st.grades = Integer.valueOf(line[x+2]);
					st.major = line[x+3];
					Methods.insertRec(st);
		      }
		      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
}


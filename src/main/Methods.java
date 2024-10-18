package main;

public class Methods {
	static StudentTree student;
	static int total = 0;
 	
	static void insertRec(Student stud) {student = insertRec(student, stud);}
		
	static StudentTree insertRec(StudentTree tree, Student stud) {
		if (tree == null) {
			return new StudentTree(stud);
		}
		if(stud.name.compareToIgnoreCase(tree.student.name)<0)  {
			tree.left = insertRec(tree.left, stud);
		} else {
			tree.right = insertRec(tree.right, stud);
		}
		return tree;
	}
	
	boolean searchRec(String name) {return searchRec(student, name);}
	
	boolean searchRec(StudentTree tree, String name) {
		if(tree == null) return false;
		else if(name.compareToIgnoreCase(tree.student.name)==0) return true;
		else if(name.compareToIgnoreCase(tree.student.name)<0) return searchRec(tree.left, name);
		else return searchRec(tree.right, name);
	}
		
	void deleteRec(String name) { deleteRec(student, name); }
	
	StudentTree deleteRec(StudentTree tree, String name) {
		if(tree == null) return null;
	
		if(name.compareToIgnoreCase(tree.student.name)<0) {
			tree.left = deleteRec(tree.left, name);
		} else if(name.compareToIgnoreCase(tree.student.name)>0) {
			tree.right = deleteRec(tree.right, name);
		} else {
			
			if(tree.left == null && tree.right == null) { 
				tree = null;
            } else if(tree.right != null) { 
            	tree.student = successor(tree); 
            	tree.right = deleteRec(tree.right, tree.student.name);
            } else { 
                tree.student = predecessor(tree);
                tree.left = deleteRec(tree.left, tree.student.name);
            }
        }
        return tree;
	}
	
	private Student successor(StudentTree tree){
		tree = tree.right;
        while(tree.left != null){
        	tree = tree.left;
        }
        return tree.student;
    }
	private Student predecessor(StudentTree tree){
		tree = tree.left;
        while(tree.right != null){
        	tree = tree.right;
        }
        return tree.student;
    }
}

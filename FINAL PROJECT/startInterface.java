import javafx.scene.control.TextField;

//interface, helps me organize the huge class

public interface startInterface {
	
	public void login();
	public void redirect(Person person);
	public void teacherHome(Teacher teacher); 
	public void viewGrades();
	public double getAverage(int index);
	public double getClassAverage();
	public double getStudentAveragePercentage(int index);
	public String getStudentGradeFraction(int index);
	public void editGrades();
	public void applyGrades(TextField[][] editPoints);
	public void deleteStudent();
	public void addStudent();
	public void newStudent(String name, String username, String password);
	public void addAssignment();
	public void deleteAssignment();
	public void curve();
	public void addCurve(int index);
	public void studentHome(Student stdnt);
	public void studentView(Student stdnt);
	public String letterGrade(Double percent);
	public boolean hasValue(String str);
	public boolean notRepeat(String str);
	public boolean isDouble(String str);
}



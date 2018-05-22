
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;
//the driver, where everything is made and passed to the start class
public class GuiDriver extends Application{

    Stage window;
    public static void main(String args[]) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ArrayList<ArrayList<Grade>> table = new ArrayList<ArrayList<Grade>>();
        ArrayList<Person> people = new ArrayList<Person>();
        Teacher lee = new Teacher("Chas Lee", "100000", "password", "Chemistry");
        people.add(lee);

        ArrayList<Grade> as1 = new ArrayList<Grade>();
        ArrayList<Grade> as2 = new ArrayList<Grade>();
        ArrayList<String> assignmentNames = new ArrayList<String>();
        assignmentNames.add("Packet");
        assignmentNames.add("Homework");
        
        for (int i = 0; i < 4; i++){
            as1.add(new Grade(40 + i, 50));
            as2.add(new Grade(45 + i, 50));
        }
   
        table.add(as1);
        table.add(as2);

       ArrayList<Student> students = new ArrayList<Student>();
       Student sam = new Student("Sam Tomlinson", "100100", "password");
       Student suraj = new Student("Suraj Anand", "200100", "password");
       Student ander = new Student("Anderson Sutandinata", "300100", "password");
       Student josh = new Student("Josh Bae", "400100", "texas");
       students.add(sam); students.add(suraj); students.add(ander); students.add(josh);
       people.add(sam); people.add(suraj); people.add(ander); people.add(josh);
  
       start gui = new start(primaryStage, people, students, table, assignmentNames);

    }
}


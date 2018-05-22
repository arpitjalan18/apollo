//import a bunch of different things needed for JavaFX
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
import java.util.*;
import java.text.DecimalFormat;
import javafx.stage.Modality;

public class start implements startInterface{

    Stage window;
    ArrayList<ArrayList<Grade>> grades;
    ArrayList<Student> student;
    ArrayList<Person> peoples;
    ArrayList<String> asNames;
    
    //I make my GUI a constructor so its easier to juggle all the different things I pass through
    public start(Stage primaryStage, ArrayList<Person> people, ArrayList<Student> students, ArrayList<ArrayList<Grade>> table, ArrayList<String> assignmentNames){
        grades = table;
        window = primaryStage;
        student = students;
        peoples = people;
        asNames = assignmentNames;
        login();
        
    }
    //login method 
    public void login(){
        window.setTitle("Gradebook");
        window.setResizable(false);
        
        // Create grid pane, set size values, various things needed for login screen
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField nameInput = new TextField();
        nameInput.setPromptText("username");

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("password");
        Button loginButton = new Button("Login");

        Label error = new Label("Error! Please Try Again");

        GridPane.setConstraints(nameInput, 1, 0);
        GridPane.setConstraints(passwordInput, 1, 1);
        GridPane.setConstraints(loginButton, 1, 2);
        GridPane.setConstraints(error, 1, 3);
        loginButton.setOnAction(e -> 
            {
                int check = 0;
                for (int i = 0; i < peoples.size(); i++){
                        //checks the username/password combo using a loop
                    if (peoples.get(i).access(nameInput.getText(), passwordInput.getText())){
                        // if it works, redirects to another thing
                        redirect(peoples.get(i));
                        check++;

                    }
                }
                if (check == 0){
                    //If it doesnt worjk, it allows people to try again
                    GridPane grid2 = new GridPane();
                    grid2.setPadding(new Insets(10, 10, 10, 10));
                    grid2.setVgap(8);
                    grid2.setHgap(10);
                    grid2.getChildren().addAll(nameInput, passwordInput, loginButton, error);
                    Scene scene2 = new Scene(grid2);
                    window.setScene(scene2);
                    scene2.getStylesheets().add("Apollo.css");
                    window.show();

                }

            });

        grid.getChildren().addAll(nameInput, passwordInput, loginButton);
        Scene scene1 = new Scene(grid);
        window.setScene(scene1);
        //i add the css stylesheet to everything
        scene1.getStylesheets().add("Apollo.css");
        window.show();
    }
    //this method checks if the person is a teacher or student, and then redirects accordingly
    public void redirect(Person person){
        if (person.type().equals("teacher")){
            Teacher teacher = (Teacher)person;
            teacherHome(teacher);
        }
        if (person.type().equals("student")){
            Student student = (Student)person;
            studentHome(student);
        }
    }
    ToolBar studentBar;
   
    ToolBar topBar;
    //main method 1, home page
    public void teacherHome(Teacher teacher){

        window.setTitle(teacher + "'s Class");
        
        //makes the topbar for navigation
        Button home = new Button("Home");
       
        Button edit = new Button("Edit grades");
       
        Button view = new Button("View grades");
       
        Button leave = new Button("Logout");
        
        topBar = new ToolBar(home, edit, view, leave);
        topBar.getStyleClass().add(".toolBar");
        topBar.setStyle("-fx-background-color: #4261A1;");
        topBar.setPadding(new Insets(0, 0, 0, 0));
        
        //makes things glow when mouse goes over
        home.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    home.getStyleClass().add("button:hover");
                    home.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        home.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               home.setStyle("-fx-background-color: #4261A1;");
            }
        });
        edit.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    
                    edit.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        edit.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               edit.setStyle("-fx-background-color: #4261A1;");
            }
        });
        
        view.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    view.getStyleClass().add("button:hover");
                    view.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        view.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               view.setStyle("-fx-background-color: #4261A1;");
            }
        });
        leave.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    leave.getStyleClass().add("button:hover");
                    leave.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        leave.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               leave.setStyle("-fx-background-color: #4261A1;");
            }
        });
        
        
        
        //Make middle
        Label welcome = new Label("Welcome to " + teacher.getName() + "'s " + teacher.getClassroom() + " class!");
        welcome.getStyleClass().add("labelWelcome");
        VBox center = new VBox(20);
        center.getChildren().addAll(welcome);
         
        //puts in the completely original logo
        final ImageView logo = new ImageView();   
        Image apollo = new Image(start.class.getResourceAsStream("apollo.png"));
        logo.setImage(apollo);

        center.getChildren().addAll(logo);
        center.setAlignment(Pos.CENTER);
     
        BorderPane fullPane = new BorderPane();
        fullPane.setTop(topBar);
        fullPane.setCenter(center);  

        // Make Scene, Display Scene
        Scene homeScene = new Scene(fullPane);
        homeScene.getStylesheets().add("Apollo.css");
        window.setScene(homeScene);
      
        // Lambda Expression for home Button - just brings the view back to the home scene
        home.setOnAction(e -> 
            {   
          
                teacherHome(teacher);

            });
            
        // etc for different parts of page
        view.setOnAction(e ->
            {
           
                    viewGrades();
                    
            });

        edit.setOnAction(e -> {
        
                editGrades();

            });
        leave.setOnAction(e -> {
        
                login();

            });
    }
    
    //main method 2, view - shows all the grades with lots of differnet ways to make it very helpful
    public void viewGrades(){
        
        //decimal formatter for annoying doubles
        DecimalFormat fmt = new DecimalFormat("#.##");
        
        //everything is organzied into a giant grid
        GridPane viewPane = new GridPane();
        viewPane.getStyleClass().add("gridpane");
        viewPane.setPadding(new Insets(10, 10, 10, 10));
        viewPane.setVgap(8);
        viewPane.setHgap(10);
        
        //grid of labels that contain all the meat of the grid
        Label[][] labels = new Label[grades.size()][grades.get(0).size()];

        
        for (int i = 0; i < grades.size(); i++){
            
            
            for (int j = 0; j <  grades.get(i).size(); j++){
            
                    //Add each assignment name (multiple times to create a bold)
                    Label assignmentName = new Label(asNames.get(i));
                GridPane.setConstraints(assignmentName, 2*i+1, 0, 2*i+2, 1);
                viewPane.getChildren().add(assignmentName);
                
                if (i == 0){
                        //add the names of the students (only when i is zero so it prints only in the 0th column)
                    Label studentName = new Label(student.get(j).toString());
                    GridPane.setConstraints(studentName, 0, j+1);
                    viewPane.getChildren().addAll(studentName);
                   
                    // I purposely add the same Label AverageName multiple times to create a bold effect
                    Label averageName = new Label("Average Grade");
                    GridPane.setConstraints(averageName, 0, grades.get(i).size()+1); 
                    viewPane.getChildren().addAll(averageName);
                }

                // get the various things needed to show grade
                double pointsGiven = grades.get(i).get(j).getGrade();
                double maxPoints = grades.get(i).get(j).getMaxPoints();  
                double percentage = grades.get(i).get(j).getPercentage();
                
                //add it all to the grid pane
                String gradeFraction = fmt.format(pointsGiven) + "/" + fmt.format(maxPoints);
                labels[i][j] = new Label(gradeFraction);
                GridPane.setConstraints(labels[i][j], 2*i+1, j+1);
                viewPane.getChildren().add(labels[i][j]);
                
                Label percent = new Label(fmt.format(percentage) + "%");
                GridPane.setConstraints(percent, 2*i+2, j+1);
                viewPane.getChildren().add(percent);

               //this adds the average on the bottom
                if (j == grades.get(i).size() - 1){
                    Label average = new Label("" + fmt.format(getAverage(i)) + "/" + fmt.format(maxPoints));
                    GridPane.setConstraints(average, 2*i+1,  grades.get(i).size()+1);
                    viewPane.getChildren().add(average);
                    Label percentAverage = new Label(fmt.format(100*getAverage(i)/maxPoints) + "%");
                    GridPane.setConstraints(percentAverage, 2*i + 2, grades.get(i).size() + 1);
                    viewPane.getChildren().add(percentAverage);
                }
                //this adds the average on the right
                if (i == grades.size()-1){
                    Label studentAvg = new Label(fmt.format(getStudentAveragePercentage(j)) + "%");
                    GridPane.setConstraints(studentAvg, 2*i+4, j+1);
                    viewPane.getChildren().add(studentAvg);
                    
                    Label studentFraction = new Label(getStudentGradeFraction(j));
                    GridPane.setConstraints(studentFraction, 2*i + 3, j + 1);
                    viewPane.getChildren().add(studentFraction);
                    
                    if (j == 0){
                        Label avgLabel = new Label("Student Average");
                        GridPane.setConstraints(avgLabel,2*i+3, j, 2*i+4, j+1);
                        viewPane.getChildren().add(avgLabel);
                    }
                    //total class average
                    if (j==grades.get(1).size()-1) {
                            
                            Label classAverage = new Label(fmt.format(getClassAverage()*100) + "%");
                            GridPane.setConstraints(classAverage, 2*i + 3, j+2);
                            viewPane.getChildren().add(classAverage);
                    }
                }
            }

        }
        //puts it all together, adds CSS, shows
        BorderPane viewBorderPane = new BorderPane();
        viewBorderPane.setCenter(viewPane);
        viewBorderPane.setTop(topBar);
        viewBorderPane.setPrefSize(1250, 700);
        ScrollPane viewScrollPane = new ScrollPane(viewBorderPane);
        Scene viewGrades = new Scene(viewScrollPane, 1250, 700);
        viewGrades.getStylesheets().add("Apollo.css");
        window.setScene(viewGrades);     
        // window.show();

    }
    //extra method 1 for view grades, gets average for a certain assignment
    //all these 4 extra methods use various loops that all call back to values from Grade class
    // the reason they are separate is for less clutter in main view class
    public double getAverage(int index){
        double a = 0.0;
        
        for (int j = 0; j < grades.get(index).size(); j++)
            a += grades.get(index).get(j).getGrade();
            
        return (a/grades.get(index).size());   
    }
    //view extra method 2 gets average for whole class (iterator usage)
    public double getClassAverage() {
        
            double top = 0;
            double bottom = 0;
            for (int i = 0; i < grades.size(); i++) {
                Iterator<Grade> arrayIterator = (grades.get(i)).iterator();
                while(arrayIterator.hasNext())  {
                    Grade theGrade = arrayIterator.next();
                    top += theGrade.getGrade();
                    bottom += theGrade.getMaxPoints();
                }
            }
            return top/bottom;
        
    }
    //view extra method 3, gets the average for a student as a percent
     public double getStudentAveragePercentage(int index){

        double points = 0;
        double maxPoints = 0;
        for (int i = 0; i < grades.size(); i++){
            points += grades.get(i).get(index).getGrade();
            maxPoints += grades.get(i).get(index).getMaxPoints();
        }
        return ((points/maxPoints)*100);

    }
    //view extra method 4, gets average for student as fraction (String)
    public String getStudentGradeFraction(int index){
         DecimalFormat fmt = new DecimalFormat("#.##");
        double points = 0;
        double maxPoints = 0;
        for (int i = 0; i < grades.size(); i++){
            points += grades.get(i).get(index).getGrade();
            maxPoints += grades.get(i).get(index).getMaxPoints();
        }
        return (fmt.format(points) + "/" + fmt.format(maxPoints));
    }

    //main method 3, with lots auxillary methods to augment and edit gradebook in every conceivable way
    public void editGrades(){
        //again use a grid pane to organize it all
        GridPane editPane = new GridPane();
        editPane.setPadding(new Insets(10, 10, 10, 10));
        editPane.setVgap(1);
        editPane.setHgap(1);   
        
        //decimal formatter for annoying doubles
        DecimalFormat fmt = new DecimalFormat("#.##");
        
        //an array of textfields to enter text
        TextField[][] editPoints = new TextField[grades.size()][grades.get(0).size()];

        for (int i = 0; i < grades.size(); i++){
            //adds assignment names
            Label assignmentName = new Label(asNames.get(i));
            GridPane.setConstraints(assignmentName, i+1, 0);
            editPane.getChildren().add(assignmentName);

            for (int j = 0; j <  grades.get(i).size(); j++){
            
                //first just adds the student names
                if (i == 0){
                    Label studentName = new Label(student.get(j).toString());
                    GridPane.setConstraints(studentName, 0, j+1);
                    editPane.getChildren().add(studentName);
                }
                
                //makes two parts, pointsGiven and maxPoints (x/y)
                double pointsGiven = grades.get(i).get(j).getGrade();
                double maxPoints = grades.get(i).get(j).getMaxPoints();                
                
                editPoints[i][j] = new TextField("" + fmt.format(pointsGiven));
                Label maxPoint = new Label("/" + fmt.format(maxPoints));
        
                editPoints[i][j].setMaxWidth(45);
                
                //fuses them together in a box
                HBox together = new HBox(0);
                
                //adds them to the grid
                together.getChildren().addAll(editPoints[i][j], maxPoint);
                together.getStyleClass().add("hboxTogether");
                together.setAlignment(Pos.CENTER);
                
                GridPane.setConstraints(together, i+1, j+1);
                editPane.getChildren().add(together);
                editPoints[i][j].getStyleClass().add("textfield");
                    
            }
            
        }
        
        //the whole button bar on the bottom with LOTS of stuff
        Button applyChanges = new Button("Apply Changes");
        Button addStudent = new Button("Add Student");
        Button deleteStudent = new Button("Delete Student");
        Button addAssignment = new Button("Add Assignment");
        Button deleteAssignment = new Button("Delete Assignment");
        Button addCurve = new Button("Add Curve"); 
        ToolBar changeBar = new ToolBar(applyChanges, addStudent, deleteStudent, addAssignment, deleteAssignment, addCurve);
        changeBar.setStyle("-fx-background-color: #4261A1;");
        changeBar.setPadding(new Insets(0, 0, 0, 0));
        
        //all of the buttons lead to other methods so this one is not horribly bloated
        applyChanges.setOnAction(e -> {
  
                applyGrades(editPoints);
            });
        addStudent.setOnAction(e -> {
       
                addStudent(); 
                
            });    
        addAssignment.setOnAction(e -> {
     
                addAssignment();
            });
        deleteAssignment.setOnAction(e -> {
                
                // if you try to get rid of all the assignments array size 0 and everything breaks so slight safety net
                if (grades.size() > 1)
                    deleteAssignment();
                else
                    alertBox.display("Error", "Gradebook must have at least assignment");
            });
        deleteStudent.setOnAction(e -> {
      
                deleteStudent();
            });
        addCurve.setOnAction(e -> {
            
                curve();
            
        });
        
        //this huge block of code is just to make buttons light blue if the mouse hovers over
        applyChanges.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    applyChanges.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        applyChanges.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               applyChanges.setStyle("-fx-background-color: #4261A1;");
            }
        });
        addStudent.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    addStudent.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        addStudent.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               addStudent.setStyle("-fx-background-color: #4261A1;");
            }
        });
        deleteStudent.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    deleteStudent.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        deleteStudent.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    deleteStudent.setStyle("-fx-background-color: #4261A1;");
            }
        });
        addAssignment.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    addAssignment.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        addAssignment.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               addAssignment.setStyle("-fx-background-color: #4261A1;");
            }
        });
        deleteAssignment.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    deleteAssignment.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        deleteAssignment.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               deleteAssignment.setStyle("-fx-background-color: #4261A1;");
            }
        });
        addCurve.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    addCurve.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        addCurve.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               addCurve.setStyle("-fx-background-color: #4261A1;");
            }
        });
        
        //add everything together and boom - edit menu
        editPane.getStyleClass().add("gridpaneEdit");

        BorderPane editBPane = new BorderPane();
        editBPane.setTop(topBar);
        editBPane.setCenter(editPane);
        editBPane.setBottom(changeBar);
        editBPane.setPrefSize(1250, 700);
        ScrollPane editScroll = new ScrollPane(editBPane);
        Scene editGrades = new Scene(editScroll, 1250, 700);
        window.setResizable(false);
        editGrades.getStylesheets().add("Apollo.css");
        window.setScene(editGrades);
        
        //  window.sizeToScene();
        //window.show();
    }
    
    //this checks all the grades to make sure its a double and, if so sets the new grades
    public void applyGrades(TextField[][] editPoints){

        int check = 0;
        
        //loops to check
        for (int i = 0; i < grades.size();i++){
            for (int j = 0; j < grades.get(0).size(); j++){

                if(!isDouble(editPoints[i][j].getText())){
                    alertBox.display("Error!", "Please enter a numerical value for " + student.get(j).name +"'s grade on " + asNames.get(i));
                    check++;
                }

            } 
        }
        //loops to set
        if (check == 0){
            for (int i = 0; i < grades.size(); i++){
                for (int j = 0; j < grades.get(0).size(); j++){

                    grades.get(i).get(j).setGrade(Double.parseDouble(editPoints[i][j].getText()));

                } 
            }

            viewGrades();
        }
    }
    
    //class to delete a student, uses a listview and observable list
    public void deleteStudent(){
        Stage deleteStage = new Stage();
        // modality makes it so you cant just leave this window and spawn infinite, you have to fix it
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setTitle("Delete a Student");
        
        // so user can select multiple students
        ListView<Student> whichStudent = new ListView<>();
        whichStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //adds the student
        for (int i = 0; i < student.size(); i++)
            whichStudent.getItems().add(student.get(i));
        
      
        Button closeButton = new Button("Cancel Delete Student");
        Button applyButton = new Button("Delete This Student");

        closeButton.setOnAction(e-> deleteStage.close());

        applyButton.setOnAction(e->{
                ObservableList<Student> selections;
                selections = whichStudent.getSelectionModel().getSelectedItems();
                if (selections.size() == student.size())
                    alertBox.display("Error", "You cannot delete all your students");
                else{
                    deleteStage.close();
                    //complicated things to make sure that code doesnt break
                    for (int i = 0; i < selections.size(); i++){
                        for (int j = 0; j < student.size(); j++){
                            if (selections.get(i) == student.get(j)){
                                student.remove(selections.get(i));
                                peoples.remove(selections.get(i));
                                for (int k = 0; k < grades.size(); k++){
                                    grades.get(k).remove(j);
                                }
                            }
                        }

                    }
                    editGrades();
                }


            });
        
            //puts it all together
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(whichStudent, applyButton, closeButton);
        layout.setAlignment(Pos.CENTER);        

        Scene scene = new Scene(layout, 400, 400);
        deleteStage.setScene(scene);
        scene.getStylesheets().add("Apollo.css");
        deleteStage.show();

    }
    
    //add a student
    public void addStudent(){
        
        //very simple class just takes 3 textfields for the constructor of a new student
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.setTitle("Make New Student");

        TextField name = new TextField();
        TextField acctUsername = new TextField();
        TextField password = new TextField();
        name.setPromptText("Name");
        acctUsername.setPromptText("Account Username");
        password.setPromptText("Account Password");

        Button closeButton = new Button("Cancel New Student");
        Button applyButton = new Button("Make New Student");

        closeButton.setOnAction(e-> addStage.close());

        applyButton.setOnAction(e->{

                if (hasValue(name.getText()) && hasValue(acctUsername.getText()) && hasValue(password.getText())){
                    addStage.close();
                    newStudent(name.getText(), acctUsername.getText(), password.getText());
                }
                else{

                    alertBox.display("Error", "Please do not leave any option blank");
                }
            });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(name, acctUsername, password, closeButton, applyButton);
        layout.setAlignment(Pos.CENTER);        

        Scene scene = new Scene(layout);
        addStage.setScene(scene);
        scene.getStylesheets().add("Apollo.css");
        addStage.show();

    }
    public void newStudent(String name, String username, String password){
        
        //makes constructor and adds empty grades to match new student
        Student newStudent = new Student(name, username, password);
        peoples.add(newStudent);
        student.add(newStudent);

        for (int i = 0; i < grades.size(); i++){
            grades.get(i).add(new Grade(0, grades.get(i).get(0).getMaxPoints()));
        }

        editGrades();

    }

    //add an assignment
    public void addAssignment(){
        //again simple class that just takes the things needed for making an assignment

        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.setTitle("Make New Assignment");

        TextField asName = new TextField();
        TextField maxPoints = new TextField();
        asName.setPromptText("Name");
        maxPoints.setPromptText("Max Points");

        Button closeButton = new Button("Cancel New Assignment");
        Button applyButton = new Button("Make New Assignment");

        closeButton.setOnAction(e-> addStage.close());

        applyButton.setOnAction(e->{
                if (isDouble(maxPoints.getText()) && hasValue(asName.getText()) && notRepeat(asName.getText())){
                    ArrayList<Grade> as1 = new ArrayList<Grade>();
                    for (int i = 0; i < student.size(); i++)
                        as1.add(new Grade(0, Double.parseDouble(maxPoints.getText())));
                    grades.add(as1);
                    
                    asNames.add(asName.getText());
                    
                    addStage.close();
                    editGrades();
                }
                else{
                    String out = "";
                    if (!hasValue(asName.getText()))
                            out += "Please put something for the assignment name. \n";
                    if (!isDouble(maxPoints.getText())) {
                            out += "\nPlease put a number for the point value.\n";
                    }
                    if(!notRepeat(asName.getText())) {
                            out += "This assignment name is a repeat, \nplease choose something else.";
                    }
                    alertBox.display("Error", out);
                }
            });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(asName,maxPoints, closeButton, applyButton);
        layout.setAlignment(Pos.CENTER);        

        Scene scene = new Scene(layout);
        addStage.setScene(scene);
        scene.getStylesheets().add("Apollo.css");
        addStage.showAndWait();

    }

    //delete assignment
    public void deleteAssignment(){
        
        //more complicated method, very similar to deletestudent, uses listview and observable list
        Stage deleteStage = new Stage();
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setTitle("Delete an Assignment");

        ListView<String> whichAs = new ListView<>();
        whichAs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (int i = 0; i < grades.size(); i++)
            whichAs.getItems().add(asNames.get(i));

        Button closeButton = new Button("Cancel Delete Assignment");
        Button applyButton = new Button("Delete this Assignment");

        closeButton.setOnAction(e-> deleteStage.close());

        applyButton.setOnAction(e->{
                ObservableList<String> selections;
                selections = whichAs.getSelectionModel().getSelectedItems();
                if (selections.size() == grades.size())
                    alertBox.display("Error", "You cannot delete all the assignments");
                else{
                    deleteStage.close();
                    for (int i = 0; i < selections.size(); i++){
                        int index = asNames.indexOf(selections.get(i));
                        asNames.remove(index);
                        grades.remove(index);
                    }
                    editGrades();
                }

                //grades.table.remove(whichAs.getValue());

            });
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(whichAs, applyButton, closeButton);
        layout.setAlignment(Pos.CENTER);        

        Scene scene = new Scene(layout, 400, 400);
        deleteStage.setScene(scene);
        scene.getStylesheets().add("Apollo.css");
        deleteStage.show();

    }
    //curve an assignment
    public void curve() {
        //uses same methodology from delete student and delete assignment to display all the assingments and choose one
        Stage deleteStage = new Stage();
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setTitle("Add A Curve");

        ListView<String> whichAs = new ListView<>();
        
        for (int i = 0; i < grades.size(); i++)
            whichAs.getItems().add(asNames.get(i));

        Button closeButton = new Button("Cancel Curve");
        Button applyButton = new Button("Curve This Assignment");

        closeButton.setOnAction(e-> deleteStage.close());

        applyButton.setOnAction(e->{
                ObservableList<String> selections;
                selections = whichAs.getSelectionModel().getSelectedItems();
                    deleteStage.close();
                    for (int i = 0; i < selections.size(); i++){
                        //once you choose one, it goes to the other menu for actually selecting how to curve
                            int index = asNames.indexOf(selections.get(i));
                        addCurve(index);
                    }
                  
               

                //grades.table.remove(whichAs.getValue());

            });
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(whichAs, applyButton, closeButton);
        layout.setAlignment(Pos.CENTER);        

        Scene scene = new Scene(layout, 200, 200);
        deleteStage.setScene(scene);
        scene.getStylesheets().add("Apollo.css");
        deleteStage.show();

    }
    public void addCurve(int index) {
        //much simpler method, just how much you want to add
        Stage deleteStage = new Stage();
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setTitle("Add A Curve");

            Label welcome = new Label("How much would you like to \n add to " + asNames.get(index));
            TextField addPoints = new TextField("Points to Add");
            addPoints.setMaxWidth(100);
            Button closeButton = new Button("Cancel Curve");
            Button applyButton = new Button("Apply Curve");

            closeButton.setOnAction(e-> deleteStage.close());
           
         applyButton.setOnAction(e->{
                        if (hasValue(addPoints.getText()) && isDouble(addPoints.getText())){
                            
                             for (int i = 0; i<grades.get(index).size(); i++) {
                                 
                                 grades.get(index).get(i).setGrade(grades.get(index).get(i).getGrade() + Double.parseDouble(addPoints.getText()));
                                 
                             }
                             deleteStage.close();
                             viewGrades();
                        }
                        
                        else {
                            alertBox.display("Error", "Please put a number in the text box");
                        }
                   
                            
                    
                });
         VBox layout = new VBox(10);
         layout.setPadding(new Insets(10, 10, 10, 10));
         layout.getChildren().addAll(welcome, addPoints, closeButton, applyButton);
         layout.setAlignment(Pos.CENTER);
         Scene scene = new Scene(layout, 400, 400);
         deleteStage.setScene(scene);
         deleteStage.show();
        
    }
    //student home for students, the 2nd half of the program (but still much smaller)
    public void studentHome(Student stdnt){
        
        window.setTitle(stdnt + "'s home page");
        
        Label welcome = new Label("Welcome to your class, " + stdnt.getName());
        welcome.getStyleClass().add("labelWelcome");
        VBox center = new VBox(20);
        center.getChildren().addAll(welcome);
        
        final ImageView logo = new ImageView();   
        Image apollo = new Image(start.class.getResourceAsStream("apollo.png"));
        logo.setImage(apollo);
        center.getChildren().addAll(logo);
        
        center.setAlignment(Pos.CENTER);
        
        //the student top bar (student bar) - similar to teacher but student has no edit
        Button home = new Button("Home");

        Button view = new Button("View grades");
       
        Button leave = new Button("Logout");
        
        studentBar = new ToolBar(home, view, leave);
        studentBar.getStyleClass().add(".toolBar");
        studentBar.setStyle("-fx-background-color: #4261A1;");
        studentBar.setPadding(new Insets(0, 0, 0, 0));
        
        //makes things glow when mouse goes over
        home.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    home.getStyleClass().add("button:hover");
                    home.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        home.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               home.setStyle("-fx-background-color: #4261A1;");
            }
        });
       
        view.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    view.getStyleClass().add("button:hover");
                    view.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        view.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               view.setStyle("-fx-background-color: #4261A1;");
            }
        });
        leave.setOnMouseEntered(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
                    leave.getStyleClass().add("button:hover");
                    leave.setStyle("-fx-background-color: #dae7f3;");
                    
                
            }
        });

        leave.setOnMouseExited(new EventHandler<MouseEvent>
        () {

            @Override
            public void handle(MouseEvent t) {
               leave.setStyle("-fx-background-color: #4261A1;");
            }
        });
        
        
        // Make Scene, Display Scene
        BorderPane studentPane = new BorderPane();
        studentPane.setCenter(center);
        studentPane.setTop(studentBar);
        Scene homeScene = new Scene(studentPane);
        homeScene.getStylesheets().add("Apollo.css");
        window.setScene(homeScene);
        
        home.setOnAction(e -> 
        {   
      
            studentHome(stdnt);

        });
        
        // etc for different parts of page
        view.setOnAction(e ->
        {
       
                studentView(stdnt);
                
        });
    
    	leave.setOnAction(e -> {
    
            login();

        });
        
    }
    //student view, the view grades for the student - main method 2
    public void studentView(Student stdnt) {
    	
    	DecimalFormat fmt = new DecimalFormat("#.##");
    	
    	 int j = student.indexOf(stdnt);
         
         GridPane studentGrades = new GridPane();
         studentGrades.getStyleClass().add("gridpane");
         studentGrades.setPadding(new Insets(10, 10, 10, 10));
         studentGrades.setVgap(8);
         studentGrades.setHgap(10);
         
         for (int i = 0; i < grades.size(); i++) {
         	Label assignment = new Label(asNames.get(i));
         	GridPane.setConstraints(assignment, i, 0);
         	studentGrades.getChildren().add(assignment);
         	
         	//displays the grade as a fraction, percent, and letter for maximum info for student
         	Double grade = grades.get(i).get(j).getGrade();
         	Double max = grades.get(i).get(j).getMaxPoints();
         	String letterGrade = grades.get(i).get(j).letterGrade();
         	
         	Label fraction = new Label(fmt.format(grade) + "/"  + fmt.format(max));
         	GridPane.setConstraints(fraction, i, 1);
         	studentGrades.getChildren().add(fraction);
         	
         	Label percent = new Label (fmt.format(grade*100/max) + "%");
         	GridPane.setConstraints(percent, i, 2);
         	studentGrades.getChildren().add(percent);
         	
         	Label letter = new Label(letterGrade);
         	GridPane.setConstraints(letter, i, 3);
         	studentGrades.getChildren().add(letter);
         	
         	Label total = new Label("Total");
         	GridPane.setConstraints(total, grades.size()+1, 0);
         	studentGrades.getChildren().add(total);
         
         	Label averageFraction = new Label(getStudentGradeFraction(j));
         	GridPane.setConstraints(averageFraction, grades.size()+1, 1);
         	studentGrades.getChildren().add(averageFraction);
         	
         	Label averagePercent = new Label(fmt.format(getStudentAveragePercentage(j)) + "%");
         	GridPane.setConstraints(averagePercent, grades.size()+1, 2);
         	studentGrades.getChildren().add(averagePercent);
         	
         	Label letterAverage = new Label(letterGrade(getStudentAveragePercentage(j)/100));
         	GridPane.setConstraints(letterAverage, grades.size()+1, 3);
         	studentGrades.getChildren().add(letterAverage);
         	
         }
         BorderPane studentPane = new BorderPane();
         studentPane.setCenter(studentGrades);
         studentPane.setTop(studentBar);
         Scene viewScene = new Scene(studentPane);
         viewScene.getStylesheets().add("Apollo.css");
         window.setScene(viewScene);
    }
    public String letterGrade(Double percent) {
    	if (percent > .9) {
    		return "A";
    	}
    	else if(percent >.8) {
    		return "B";
    	}
    	else if(percent > .7) {
    		return "C";
    	}
    	else if(percent > .6) {
    		return "D";
    	}
    	else {
    		return "F";
    	}
    	
    	
    }
    //these last 3 just check to make sure the user entered the right type of data so the program doesnt break
    public boolean hasValue(String str){
        if (str.length() > 0)
            return true;
        else
            return false;
    }
    public boolean notRepeat(String name) {
        
            if (asNames.indexOf(name) >= 0) {
                return false;
            }
            return true;
        
    }
    //this one uses a try catch method to check if a string is a double and can be entered into the grand array
    //if it fails, the main method that calls it will show an error message
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

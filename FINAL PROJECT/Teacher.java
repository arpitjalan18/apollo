
/**
 * basic teacher class, name, username, password, classroom
 */
public class Teacher extends Person
{  
   String classroom;
   public Teacher(String name, String username, String password, String classroom){
    
       super(name, username, password);
       this.classroom = classroom;
    
    }
   public String type(){
    
       return "teacher";
       
    }
 
   public String getClassroom(){
    
       return classroom;
       
   }
}
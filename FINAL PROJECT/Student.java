
/**
 * basic student class, bare bones name, username, password
 */
public class Student extends Person
{
    
   
    public Student(String name, String username, String password)
    {
        super (name, username, password);
        
    }

    public String type(){
    
        return "student";
        
    }
    
    public String toString(){
        return name;
    }
    
}


/**
 * makes the abstract person class
 * basic name, password, username ,etc
 */
public abstract class Person
{
    String name;
    String username;
    String password;
    
    public Person(String name, String username, String password){
    
        for (int i = 0; i < name.length(); i++){
            if(name.substring(i, i+1).equals(" ")&& i < name.length())
                this.name = name.substring(0, 1).toUpperCase() + name.substring(1, i+1) + name.substring(i+1, i+2).toUpperCase() + name.substring(i+2,name.length());
            else if(i + 1 == name.length())
                this.name = name;
        }
            
                
        this.username = username;
        this.password = password;
        
    }
    public boolean access(String username, String password){
    
        if (username.equals(this.username) && password.equals(this.password))
            return true;
        
        return false;
    
    }
    public String getName(){
       return name;
    }
   public abstract String type();
   public String toString(){
       return name;
    }
    
    
}


//this class is the basic unit of the whole gradebook
//It is a constructor for a grade, containing the points given and points possible (grade and maxPoints)
//The constructor has various classes to access and edit these two variables
public class Grade{
    double grade;
    double maxPoints;

    public Grade(double grade, double maxPoints){

        this.grade = grade;
        this.maxPoints = maxPoints;
        
    }

	public void setGrade(double newGrade){
        grade = newGrade;
    }

    public double getGrade(){
        return grade;
    }

    public double getMaxPoints(){
        return maxPoints;
    }
    
    public double getPercentage(){
        return (grade/maxPoints * 100);
    }

    public void setMaxPoints(double newMaxPoints){
        maxPoints = newMaxPoints;
    }
    public String toString() {
    	
    	return grade + "/" + maxPoints;
    }
    public String letterGrade() {
    	if (grade/maxPoints > .9) {
    		return "A";
    	}
    	else if(grade/maxPoints >.8) {
    		return "B";
    	}
    	else if(grade/maxPoints > .7) {
    		return "C";
    	}
    	else if(grade/maxPoints > .6) {
    		return "D";
    	}
    	else {
    		return "F";
    	}
    	
    	
    }

	
}

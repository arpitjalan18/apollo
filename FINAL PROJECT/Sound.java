
/**
 * Write a description of class Sound here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sound
{
    private int[] samples = {0, 0 , 0 , 0 , 0, 3, 3, -20, 3, 4, 5, 10, 11, 12, 0 ,0};
    public Sound(){
        
        tirmSilenceFromBeginning();
    
    }
    
        
    
    
    public int limitAmplitude(int limit){
   int counter = 0;
   for (int i = 0; i < samples.length; i++){
       if (Math.abs(samples[i]) > limit){
           if(samples[i] > 0){
               samples[i] = limit;
            }
            else if(samples[i] < 0){
                samples[i] = -limit;
            }
            counter++;
}

}
return counter;
}

public void tirmSilenceFromBeginning(){
int stillZero = 0;
    for (int i = 0; i < samples.length; i++){

        if (samples[i] == 0 && stillZero==0){}
        else{
            stillZero++;
        }

    }
    int[] newSamples = new int[stillZero];  
    int otherCounter = 0;
    for (int i = samples.length - stillZero; i < samples.length; i++){
        newSamples[otherCounter] = samples[i];
        otherCounter++;
    }
    samples = newSamples;
    for (int i = 0; i < samples.length; i++){
        System.out.println(samples[i]);
    }
}
}

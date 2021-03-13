import java.io.File;
import java.io.IOException;
import java.util.*;

public class Shoulders implements Workout{
    /***
     * Fields
     * Exercise Count - Number of Exercises that train shoulders per workout
     * Shoulder Exercises - Array of array that stores all shoulder workouts from text file
     * week - Integer value representing which week the user is in in their program
     * workout - set that contains there workout
     */
    int exerciseCount;
    HashMap<String, ArrayList<String>> shoulderExercises = null;
    String shoulderTextFile;
    ArrayList<String> workout = null;
    int week;

    /***
     * Constructor for shoulder workout
     * @param exerciseCount Number of Exercises per workout
     * @param shoulderTextFile text file containing all shoulder workouts
     * @param week integer value from 1-12 representing which week in the cycle a user is on
     */
    public Shoulders(int exerciseCount, String shoulderTextFile, int week) throws IOException {
        this.exerciseCount = exerciseCount;
        this.shoulderTextFile = shoulderTextFile;
        this.week = week;
        shoulderExercises = new HashMap<>();
        workout = new ArrayList<>();
        this.create();

    }/***
     * Parses the text file that contains the workouts
     */
    @Override
    public void readInWorkout() throws IOException {
        ReadFromFile reader = new ReadFromFile(shoulderTextFile);
        reader.Read();
        shoulderExercises = reader.exercises;

    }


    @Override
    /***
     * Compiles the workout
     */
    public void compileWorkout() {
        List keys = new ArrayList(shoulderExercises.keySet());

        Random random = new Random();
        //ensuring that at least one compound movement is in the workout
        int k = random.nextInt(shoulderExercises.get("Compound").size());
        workout.add(shoulderExercises.get("Compound").get(k));
        System.out.println(shoulderExercises.size());
        //While loop which randomly chooses workouts from the workout list

        while (workout.size() < exerciseCount) {
            int i = random.nextInt(keys.size());
            int j = random.nextInt(shoulderExercises.get(keys.get(i)).size());
            String new_lift = shoulderExercises.get(keys.get(i)).get(j);
            if (!workout.contains(new_lift)) {
                workout.add(new_lift);
            }
        }
    }

    /***
     * ComputeRepScheme - Adds to the correct rep schemes to the list of compiled Exercises
     */
    @Override
    public void computeRepsScheme() {
        String repScheme = "";
        if (week % 4 == 0) repScheme = "3 x 8";
        else if (week % 4 == 3) repScheme = " 3 x 10";
        else if (week % 4 == 2) repScheme = " 3 x 12";
        else if (week % 4 == 1) repScheme = " 3 x 15";
        for (int i = 0; i < workout.size(); i++){
            workout.set(i, workout.get(i).concat(" " + repScheme));
        }
    }

    @Override
    public void create() throws IOException {

        this.readInWorkout();
        this.compileWorkout();
        this.computeRepsScheme();

    }
}

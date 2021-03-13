import java.io.IOException;
import java.util.*;

public class Legs implements Workout {
    int exerciseCount;
    HashMap<String, ArrayList<String>> LegExercises = null;
    String LegTextFile;
    ArrayList<String> workout = null;
    int week;


    public Legs(int exerciseCount, String LegTextFile, int week) throws IOException {
        this.exerciseCount = exerciseCount;
        this.LegTextFile = LegTextFile;
        this.week = week;
        LegExercises = new HashMap<>();
        workout = new ArrayList<>();
        this.create();
    }
    @Override
    public void readInWorkout() throws IOException {
        ReadFromFile reader = new ReadFromFile(LegTextFile);
        reader.Read();
        LegExercises = reader.exercises;

    }

    @Override
    public void compileWorkout() {
        List keys = new ArrayList(LegExercises.keySet());
        Random random = new Random();
        int k = random.nextInt(LegExercises.get("Compound").size());
        workout.add(LegExercises.get("Compound").get(k));
        while (workout.size() < exerciseCount) {
            int i = random.nextInt(keys.size());
            int j = random.nextInt(LegExercises.get(keys.get(i)).size());
            String new_lift = LegExercises.get(keys.get(i)).get(j);
            if (!workout.contains(new_lift)) {
                workout.add(new_lift);
            }
        }

    }

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

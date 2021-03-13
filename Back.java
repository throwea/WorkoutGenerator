import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Back implements Workout {
    int exerciseCount;
    HashMap<String, ArrayList<String>> BackExercises = null;
    String BackTextFile;
    ArrayList<String> workout = null;
    int week;

    public Back(int exerciseCount, String BackTextFile, int week) throws IOException {
        this.exerciseCount = exerciseCount;
        this.BackTextFile = BackTextFile;
        this.week = week;
        BackExercises = new HashMap<>();
        workout = new ArrayList<>();
        this.create();
    }


    @Override
    public void readInWorkout() throws IOException {
        ReadFromFile reader = new ReadFromFile(BackTextFile);
        reader.Read();
        BackExercises = reader.exercises;

    }

    @Override
    public void compileWorkout() {
        List keys = new ArrayList(BackExercises.keySet());
//        System.out.println(keys);

        Random random = new Random();
        int k = random.nextInt(BackExercises.get("Compound").size());
        workout.add(BackExercises.get("Compound").get(k));
//        System.out.println(LegExercises.size());
        while (workout.size() < exerciseCount) {
            int i = random.nextInt(keys.size());
            int j = random.nextInt(BackExercises.get(keys.get(i)).size());
            String new_lift = BackExercises.get(keys.get(i)).get(j);
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

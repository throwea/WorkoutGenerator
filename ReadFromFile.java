import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/***
 * Parses text files that have all the different workouts for each bodypart
 */
public class ReadFromFile {
    /**
     * Fields:
     * Exercises - array list which stores each exercise
     * Filename - path for the filename containing workoutsp
     */
    HashMap<String, ArrayList<String>> exercises = null;
    String fileName;
    public static void main(String[] args) throws Exception {
        String test = "C:\\Users\\elian\\Desktop\\textFiles\\LegWorkouts.txt";
        ReadFromFile reader = new ReadFromFile(test);
        reader.Read();
        //reader.printHashMap();
        //System.out.println(reader.exercises);
        Legs newDay = new Legs(6, test, 1);
        newDay.create();
        //System.out.println(newDay.LegExercises);
        System.out.println(newDay.workout);
    }

    /***
     * Constructor for the ReadFromFile object
     * @param fileName String representing the path of the file we wish to parse
     */
    public ReadFromFile(String fileName) {
        this.fileName = fileName;
        exercises = new HashMap<>();
    }

    /***
     * Reads in file which then calls our parser
     * @throws IOException
     */
    public void Read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br.readLine()) != null) {
            parseLine(s);
        }
    }

    /***
     * Parses data from the text files and adds it to exercises
     * @param s String that is parsed from the file
     */
    public void parseLine(String s) {
        char[] s_arr = s.toCharArray();
        String exercise = "";
        Boolean insideList = false;
        Boolean insideCategory = false;
        ArrayList<String> subMuscleWorkouts = new ArrayList<>();
        String category = "";
        for (char c : s_arr) {
            //parsing the category
            if (c == '<') {
                insideCategory = true;
                continue;
            }
            if (insideCategory && c != '>') category = category + c;
            if (c == '>'){
                insideCategory = false;

            }
            //cases for parsing exercises themselves
            if (c == ']'){
                exercises.put(category, subMuscleWorkouts);
                insideList = !insideList;
                subMuscleWorkouts = new ArrayList<>();
            }
            if (c == '[') {
                insideList = true;
            }
            if (c == ',' && insideList) {
                subMuscleWorkouts.add(exercise);
                exercise = "";
                continue;
            }
            if (insideList  && c != '[') exercise += c;
        }
    }

}


import java.io.IOException;
import java.util.*;

/***
 * Takes all the workouts and creates a week split
 * that trains all the body parts and puts them into a list
 */
public class WorkoutCompiler extends WeekLayout{
//    final String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
//    final static HashMap<String, String> textFiles = new HashMap<>();
//    LinkedHashMap<String, ArrayList<String>> template = null;
//    ArrayList<ArrayList<String>> configuredTemplate = null;
//    int count;   //these two fields will change when we allow for user input
//    int week;

    public static void main(String args[]) throws IOException {
//        Scanner in = new Scanner(System.in);
//        int freeDays = in.nextInt();
//        int level = in.nextInt();
//        System.out.println("Days per week you can train: " + freeDays);
//        System.out.println("Choose 1, 2, or 3. 1 is for beginners, 2 is for intermediate, 3 for advanced or above: " + level);
        WorkoutCompiler wog = new WorkoutCompiler(5, 1);
        System.out.println(wog.configuredTemplate.toString());

    }

    /***
     * Places all the text files that contain the workouts into the workout template
     * @throws IOException
     */
    public void createFiles() throws IOException {
        putTextFiles();


        template.put(days[0], new Shoulders(count, textFiles.get("Shoulders"), week).workout.);
        template.put(days[1], new Legs(count, textFiles.get("Legs"), week).workout);
        template.put(days[3], new Chest(count, textFiles.get("Chest"), week).workout);
        template.put(days[4], new Back(count, textFiles.get("Back"), week).workout);
        template.put(days[5], new Arms(count, textFiles.get("Arms"), week).workout);
    }

    /***
     * Constructs the weeks workout
     * @param count
     * @param week
     */
    public WorkoutCompiler(int count, int week) throws IOException {
        this.count = count;
        this.week = week;
        template = new LinkedHashMap<>();
        configuredTemplate = new ArrayList<>();

        this.create();
    }
    public void configure(){
        List keys = new ArrayList(template.keySet());
        ArrayList<String> days = new ArrayList<>();
        //for loop for just adding the keys
        for (int i = 0; i < keys.size(); i++) {
            days.add(keys.get(i).toString());
            days.add("");

        }

        configuredTemplate.add(days);
        System.out.println(configuredTemplate);
        ArrayList<String> row = new ArrayList<>();
        //System.out.println("+++++++++++++++" + template.get(keys.get(0).toString()));
        for (int j = 0; j < count; j++) {
            for (int k = 0; k < keys.size(); k++){
                //System.out.println("j, k: " + j + " " + k);
                //System.out.println(configuredTemplate);
                row.add(template.get(keys.get(k).toString()).get(j));
                row.add("");
            }
            configuredTemplate.add(row);
            row = new ArrayList<>();
        }


    }
    public void makeWeeksWorkout(){
        List keys = new ArrayList(template.keySet());

        for (int i = 0; i < keys.size(); i++) {
            System.out.print(keys.get(i) + " ");
            String day = keys.get(i).toString();
            System.out.println(template.get(day));

        }
    }
    public void create() throws IOException {
        this.putTextFiles();
        this.makeWeeksWorkout();
        this.configure();
    }

}

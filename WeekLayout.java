import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WeekLayout {
    final String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    final static HashMap<String, String> textFiles = new HashMap<>();
    LinkedHashMap<String, ArrayList<String>> template = null;
    ArrayList<ArrayList<String>> configuredTemplate = null;
    int count;   //these two fields will change when we allow for user input
    int week;

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
    public void putTextFiles() throws IOException{
        textFiles.put("Shoulders", "C:\\Users\\elian\\Desktop\\textFiles\\ShoulderWorkouts.txt");
        textFiles.put("Legs", "C:\\Users\\elian\\Desktop\\textFiles\\LegWorkouts.txt");
        textFiles.put("Arms", "C:\\Users\\elian\\Desktop\\textFiles\\ArmWorkouts.txt");
        textFiles.put("Back", "C:\\Users\\elian\\Desktop\\textFiles\\BackWorkouts.txt");
        textFiles.put("Chest", "C:\\Users\\elian\\Desktop\\textFiles\\ChestWorkouts.txt");

    }

    public void makeWeeksWorkout() {
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

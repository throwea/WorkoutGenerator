import java.io.File;
import java.io.IOException;

public interface Workout {
    public void readInWorkout() throws IOException;
    public void compileWorkout();
    public void computeRepsScheme();
    public void create() throws IOException;
}

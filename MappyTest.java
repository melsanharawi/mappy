import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class MappyTest {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        String sCurrentLine;
        String viewMode = null;
        int countViewModes = 0;
        Set<String> zooms = new HashSet<>();
        while ((sCurrentLine = f.readLine()) != null) {
            String[] splitLine = sCurrentLine.split("/map/1.0/slab/");
            if (splitLine.length == 2) { //check line is a "tuile" line
                String [] splitEndUrl =  splitLine[1].split("/");
                if (splitEndUrl.length == 5) {
                    if (splitEndUrl[0].equals(viewMode)) { //compare with previous line
                        countViewModes++;
                        zooms.add(splitEndUrl[2]);
                    } else {
                        printOutput(viewMode, countViewModes, zooms);
                        zooms.clear();
                        countViewModes = 1;
                        viewMode = splitEndUrl[0];
                        zooms.add(splitEndUrl[2]);
                    }
                }
            } else { // not a "tuile" line
                printOutput(viewMode, countViewModes, zooms);
                viewMode = null;
                countViewModes = 0;
                zooms.clear();
            }
        }
        printOutput(viewMode, countViewModes, zooms);
    }

    private static void printOutput(String viewMode, int countViewModes, Set<String> zooms) {
        if (viewMode != null) {
            System.out.println(viewMode + "\t" + countViewModes + "\t" + String.join(",", zooms));
        }
    }

}

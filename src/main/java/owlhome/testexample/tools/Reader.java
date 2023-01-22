package owlhome.testexample.tools;

import owlhome.testexample.utils.FilePath;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Reader {
    public static List<String> readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath.LOCATION_FILE.getFilePath() + file)));

        String readedLine = null;
        List<String> lines = new LinkedList<>();

        while ((readedLine = reader.readLine()) != null){
            lines.add(readedLine);
        }

        return lines;
    }
}

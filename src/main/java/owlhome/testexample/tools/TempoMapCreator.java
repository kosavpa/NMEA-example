package owlhome.testexample.tools;

import owlhome.testexample.utils.IdLine;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static owlhome.testexample.tools.Reader.readFile;

public class TempoMapCreator {
    public Map<Date, List<String>> createTempoMap(String fileName, Parser parser, TempoMapWatcher watcher) throws IOException {
        List<String> allLineList = readFile(fileName);
        Map<Date,List<String>> tempoDateListMap = new HashMap<>();

        allLineList.stream().forEach(line -> {
            switch (parser.parseAndReturnId(line)){
                case GPGGA:
                case BDVTG:
                case GPVTG:
                case GNVTG: watcher.addGpggaAndGnvtgInTempoMap(tempoDateListMap, line);
                    break;
                case GPZDA:
                case GNZDA: watcher.addGnzdaInTepmoMap(tempoDateListMap, parser.parseLineAndReturnDate(line));
                    break;
                case UNKNOWN:
                    break;
            }
        });

        return tempoDateListMap;
    }

    public Map<Date, List<String>> checkTempoMap(Map<Date, List<String>> dateListMap, Parser parser){
        if(dateListMap.containsKey(null)){
            Date currentDate = null;

            for(Date date : dateListMap.keySet()){
                if (currentDate == null){
                    currentDate = date;
                } else {
                    if(currentDate.after(date))
                        currentDate = date;
                }
            }

            dateListMap.get(currentDate).addAll(dateListMap.remove(null));
        }

        dateListMap.keySet().stream().forEach(data -> {
            List<String> lineList = dateListMap.get(data);
            if(parser.parseAndReturnId(lineList.get(0)).equals(IdLine.GPGGA))
                lineList.remove(0);
            if (parser.parseAndReturnId(lineList.get(lineList.size() - 1)).equals(IdLine.GNVTG) |
                    parser.parseAndReturnId(lineList.get(lineList.size() - 1)).equals(IdLine.BDVTG) |
                    parser.parseAndReturnId(lineList.get(lineList.size() - 1)).equals(IdLine.GPVTG))
                lineList.remove(lineList.size() - 1);
        });

        return dateListMap;
    }
}

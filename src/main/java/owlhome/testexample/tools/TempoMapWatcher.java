package owlhome.testexample.tools;


import java.util.*;


public class TempoMapWatcher {
    public void addGpggaAndGnvtgInTempoMap(Map<Date, List<String>> dateListMap, String line){
        if (dateListMap.isEmpty()){
            dateListMap.put(null, new ArrayList<String>(Arrays.asList(line)));
        } else {
            if(dateListMap.containsKey(null)){
                dateListMap.get(null).add(line);
            } else {
                dateListMap.put(null, new ArrayList<String>(Arrays.asList(line)));
            }
        }
    }

    public void addGnzdaInTepmoMap(Map<Date, List<String>>dateListMap, Date date){
        List<String> lineListDateKey = dateListMap.get(date);
        List<String> lineListNullKey = dateListMap.get(null);

        if(lineListDateKey == null) {
            dateListMap.put(date, dateListMap.remove(null));
        } else if (lineListNullKey != null) {
            lineListDateKey.addAll(lineListNullKey);
            dateListMap.remove(null);
        }
    }
}

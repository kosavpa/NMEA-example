package owlhome.testexample.tools;


import owlhome.testexample.models.IdCoordinateData;
import owlhome.testexample.utils.IdLine;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Parser {
    public IdCoordinateData parseAndReturnCoordinateData(String... arLine){
        IdLine id = null;
        String[] dataAr = new String[0];
        double speed = 0;
        for(String line : arLine) {
            String[] splitLine = line.split(",");

            if (splitLine[0].equals(IdLine.GPGGA.getId())) {
                id = IdLine.GPGGA;
                dataAr = new String[]{splitLine[2], splitLine[4]};
            } else if(splitLine[0].equals(IdLine.GNVTG.getId()) | splitLine[0].equals(IdLine.GPVTG.getId()) | splitLine[0].equals(IdLine.BDVTG.getId())){
                String lineFromDouble = splitLine[7];
                if(!lineFromDouble.isEmpty())
                    speed = Double.parseDouble(splitLine[7]);
            }
        }

        return new IdCoordinateData(id, dataAr, speed);
    }

    public IdLine parseAndReturnId(String line){
        IdLine id = null;

        try {
            id = IdLine.valueOf(IdLine.class, line.split(",")[0].substring(1));
        } catch (IllegalArgumentException e){
            System.out.println("Id not found");
            return id = IdLine.UNKNOWN;
        }

        switch (id){
            case GNVTG: return IdLine.GNVTG;
            case GPVTG: return IdLine.GPVTG;
            case BDVTG: return IdLine.BDVTG;
            case GPGGA: return IdLine.GPGGA;
            case GNZDA: return IdLine.GNZDA;
            case GPZDA: return IdLine.GPZDA;
            default: throw new IllegalArgumentException("Do not creating ID!!!");
        }
    }

    public Date parseLineAndReturnDate(String line){
        int gregorianCalendarOffset = 1;
        String[] splitLine = line.split(",");
        Calendar calendar = new GregorianCalendar(
                Integer.parseInt(splitLine[4]),
                Integer.parseInt(splitLine[3]) - gregorianCalendarOffset,
                Integer.parseInt(splitLine[2]));

        return calendar.getTime();
    }
}
package owlhome.testexample.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Day {
    private Coordinate firstCoordinate;
    private Coordinate secondCoordinate;
    private Date date;

    public String getFormattedDate(){
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (!firstCoordinate.equals(day.firstCoordinate)) return false;
        if (!secondCoordinate.equals(day.secondCoordinate)) return false;
        return date.equals(day.date);
    }

    @Override
    public int hashCode() {
        int result = firstCoordinate.hashCode();
        result = 31 * result + secondCoordinate.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}

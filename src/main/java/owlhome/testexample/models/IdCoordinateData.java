package owlhome.testexample.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import owlhome.testexample.utils.IdLine;


@Data
@AllArgsConstructor
public class IdCoordinateData {
    private IdLine id;
    private String[] dataArray;
    private double speed;

    public boolean checkData(){
        for(String line : dataArray){
            if(line.isEmpty())
                return false;
        }
        if (speed == 0d)
            return false;

        return true;
    }
}

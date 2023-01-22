package owlhome.testexample.tools;

import owlhome.testexample.models.Coordinate;
import owlhome.testexample.models.IdCoordinateData;

import java.util.List;

public class CoordinateCreator {
    public Coordinate createSecondCoordinate(List<String> lineList, Parser parser) {
        int numberOfCoordinateLine = lineList.size() - 1;
        int offsetSpeedLine = 1;
        int step = 2;


        IdCoordinateData data = parser.parseAndReturnCoordinateData(lineList.get(numberOfCoordinateLine), lineList.get(numberOfCoordinateLine - offsetSpeedLine));

        while (!data.checkData()){
            numberOfCoordinateLine = numberOfCoordinateLine - step;

            if (lineList.size() > numberOfCoordinateLine){
                parser.parseAndReturnCoordinateData(lineList.get(numberOfCoordinateLine), lineList.get(numberOfCoordinateLine - offsetSpeedLine));
            } else {
                throw new IllegalArgumentException("This data incorrect!");
            }
        }

        Coordinate firstCoordinate = Coordinate.builder()
                .latitude(Double.parseDouble(data.getDataArray()[0]))
                .longitude(Double.parseDouble(data.getDataArray()[1]))
                .speed(data.getSpeed())
                .build();

        return firstCoordinate;
    }

    public Coordinate createFirstCoordinate(List<String> lineList, Parser parser){
        int numberOfCoordinateLine = 1;
        int offsetSpeedLine = 1;
        int step = 2;


        IdCoordinateData data = parser.parseAndReturnCoordinateData(lineList.get(numberOfCoordinateLine), lineList.get(numberOfCoordinateLine - offsetSpeedLine));

        while (!data.checkData()){
            numberOfCoordinateLine = numberOfCoordinateLine + step;

            if (lineList.size() > numberOfCoordinateLine){
                parser.parseAndReturnCoordinateData(lineList.get(numberOfCoordinateLine), lineList.get(numberOfCoordinateLine - offsetSpeedLine));
            } else {
                throw new IllegalArgumentException("This data incorrect!");
            }
        }

        Coordinate firstCoordinate = Coordinate.builder()
                .latitude(Double.parseDouble(data.getDataArray()[0]))
                .longitude(Double.parseDouble(data.getDataArray()[1]))
                .speed(data.getSpeed())
                .build();

        return firstCoordinate;
    }
}

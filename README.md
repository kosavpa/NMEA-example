# NMEA-example

Используемые технологии: spring boot, spring web mvc, commons-io, commons-fileupload, lombok.
Веб интерфейс: представляет собой одиночную простую страницу для загрузки и отображения информации по пройденному пути. Сокрытие информации, при отсутствии таковой,
обеспечивается тегами thymeleafe.
Состав приложения:
- models: 
1. Coordinate - представляет собой координату, со значением широтой и долготой;
2. Day - представляет день по пройденному пути и содержит две координаты (в начале и конце дня);
3. DistanceInfo - представляет информацию о пройденной дистанции за день;
4. IdCoordinateData - представляет огрегацию связанных данных даты, скорости и данных об координаты в "сыром" виде, так же отвечает проверку корректности данных.
- tools:
1. CoordinateCreator - отвечает за создание координат;
2. DayBuilder - отвечает за создание дня и представляет из себя реализацию шаблона Mediator, так как знает о связях между другими классами;
3. DistanceInfoBuilder - отвечает за создании информации по пройденному пути (формула гаверсинуса) и средней скорости;
4. Parser - отвечвет за преобразование строк в определенные объекты;
5. Reader - отвечает за считывании из файла строк в список;
6. TempoMapCreator - отвечает за создание "среднего" результата который представляет из себя отображение даты на список координат.
7. 

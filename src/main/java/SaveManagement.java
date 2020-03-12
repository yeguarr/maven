import Exceptions.failedCheckException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Класс, оперирующий с файлами*/

public class SaveManagement {
    private static File file;

    public static void setFile(File file) {
        SaveManagement.file = file;
    }
     /** Сохранение файла в CSV формат*/
    public static void SaveToFile(Collect c)
    {
        if (file == null)
            file = new File("save.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(Route r : c.List) {
                fileWriter.write(r.toCSVfile()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Возвращает коллекцию из сохраненного файла*/
    public static Collect ListFromSave() {
        Collect collect = new Collect();
        try {
            Scanner scan = new Scanner(file);
            String[] Args;
            for (int lineNum=1; scan.hasNext(); lineNum++) {
                try {
                    String line = scan.nextLine();
                    Args = line.split(",", 14);

                    Route route = new Route();
                    int id = Route.idCheck.checker(Integer.parseInt(Args[0]));
                    if(collect.searchById(id)==null)
                        route.setId(id);
                    else
                    {
                        System.out.println("Получен неверный id");
                        throw new failedCheckException();
                    }

                    route.setName(Route.nameCheck.checker(Args[1]));

                    int Cx = Coordinates.XCheck.checker(Integer.parseInt(Args[2]));
                    Long Cy = Coordinates.YCheck.checker(Long.parseLong(Args[3]));
                    route.setCoordinates(new Coordinates(Cx, Cy));

                    ZonedDateTime dateTime = ZonedDateTime.parse(Args[4]);
                    route.setCreationDate(dateTime);
                    if (!Args[5].equals("null"))
                    {
                        Long FromX = Location.XYZCheck.checker(Long.parseLong(Args[5]));
                        Long FromY = Location.XYZCheck.checker(Long.parseLong(Args[6]));
                        long FromZ = Location.XYZCheck.checker(Long.parseLong(Args[7]));
                        route.setFrom(new Location(FromX, FromY, FromZ, Args[8]));
                    }

                    Long toX = Location.XYZCheck.checker(Long.parseLong(Args[9]));
                    Long toY = Location.XYZCheck.checker(Long.parseLong(Args[10]));
                    long toZ = Location.XYZCheck.checker(Long.parseLong(Args[11]));
                    route.setTo(new Location(toX, toY, toZ, Args[12]));
                    if (!Args[13].equals("null")) {

                        Long dis = Route.distanceCheck.checker(Long.parseLong(Args[13]));
                        route.setDistance(dis);
                    }
                    collect.List.add(route);
                }  catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException | failedCheckException e) {
                    System.out.println("Ошибка чтения файла, строка: " + lineNum);
                    //e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return collect;
    }
}

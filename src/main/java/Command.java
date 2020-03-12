import Exceptions.EndOfFile;
import Exceptions.IncorrectFileNameException;
import Exceptions.failedCheckException;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Класс - обработчик команд с консоли
 */

public class Command {
    /** Обработка команд, вводимых с консоли */
    public static boolean Switcher(AbstractReader reader, Collect c, String s1, String s2) throws EndOfFile {
        switch (s1)
        {
            case ("help"): Help();
                break;
            case ("info"): Info(c);
                break;
            case ("show"): Show(c);
                break;
            case ("add"): Add(reader, c, s2);
                break;
            case ("update"): Update(reader, c, s2);
                break;
            case ("remove_by_id"): Remove_by_id(reader, c, s2);
                break;
            case("clear"): Clear(c);
                break;
            case("save"): Save(c);
                break;
            case("execute_script"):
                return Execute_script(c, s2);
            case("exit"):
                return false;
            case("add_if_min"): Add_if_min(reader, c, s2);
                break;
            case("remove_greater"): Remove_greater(reader, c, s2);
                break;
            case("remove_lower"): Remove_lower(reader, c, s2);
                break;
            case("average_of_distance"): Average_of_distance(c);
                break;
            case("min_by_creation_date"): Min_by_creation_date(c);
                break;
            case("print_field_ascending_distance"): Command.Print_field_ascending_distance(c);
                break;
            default:
                System.out.println("Такой команды нет");
        }
        return true;
    }

    /** Показывает информацию по всем возможным командам*/
    public static void Help(){
        System.out.println(
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "average_of_distance : вывести среднее значение поля distance для всех элементов коллекции\n" +
                "min_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является минимальным\n" +
                "print_field_ascending_distance distance : вывести значения поля distance в порядке возрастания"
        );
    }

    /**Показывает информацию о коллекции*/
    public static void Info(Collect collect){
        System.out.println("Тип коллекции " + collect.List.getClass().getName());
        System.out.println("Колличество элементов " + collect.List.size());
        System.out.println("Коллеция создана " + collect.getDate());
    }
    /** Выводит значения поля distance в порядке возрастания */
    public static void Print_field_ascending_distance(Collect c) {
        if(c.List.size()>0) {
            LinkedList<Long> distances = new LinkedList<>();
            for (Route r : c.List) {
                if (r.getDistance() != null)
                    distances.add(r.getDistance());
            }
            Collections.sort(distances);
            for (Long dis : distances) {
                System.out.println(dis);
            }
            return;
        }
        System.out.println("В коллекции нет элементов");
    }

    /** выводит любой объект из коллекции, значение поля creationDate которого является минимальным */
    public static void Min_by_creation_date(Collect c) {
        if(c.List.size()>0) {
            Route minR = c.List.get(0);
            for (Route r : c.List) {
                if (r.getCreationDate().compareTo(minR.getCreationDate()) < 0)
                    minR = r;
            }
            System.out.println(minR.toString());
            return;
        }
        System.out.println("В коллекции нет элементов");
    }
    /** Выводит среднее значение поля distance */
    public static void Average_of_distance(Collect c) {
        if(c.List.size()>0)
        {
            long sum = 0L;
            int count = 0;
            for (Route r : c.List)
            {
                if (r.getDistance() != null)
                    sum+=r.getDistance();
                else
                    count++;
            }
            if (c.List.size() - count > 0)
                System.out.println("Среднее значение distance: " + sum/(c.List.size() - count));
            return;
        }
        System.out.println("В коллекции нет элементов");
    }

    /** Удаляет все элементы коллекции, которые меньше чем заданный*/
    public static void Remove_lower(AbstractReader reader,Collect c, String s) throws EndOfFile {
        int id = c.GetRandId();
        Route New = toAdd(reader, id, s);
        int size = c.List.size();
        int i = 0;
        while (i < size) {
            if (c.List.get(i).compareTo(New) < 0) {
                c.List.remove(c.List.get(i));
                size -= 1;
                i -= 1;
            }
            i++;
        }
    }

    /** Удаляет все элементы коллекции, которые больше чем заданный */
    public static void Remove_greater(AbstractReader reader, Collect c, String s) throws EndOfFile {
        int id = c.GetRandId();
        Route New = toAdd(reader, id, s);
        int size = c.List.size();
        int i = 0;
        while (i < size) {
            if (c.List.get(i).compareTo(New) > 0) {
                c.List.remove(c.List.get(i));
                size -= 1;
                i -= 1;
            }
            i++;
        }
    }

    /** Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции */
    public static void Add_if_min(AbstractReader reader, Collect c, String s) throws EndOfFile {
        int id = c.GetRandId();
        Route New = toAdd(reader, id, s);
        if (New.compareTo(c.List.getFirst()) < 0) {
            c.List.add(New);
        } else System.out.println("Элемент не является минимальным в списке");
        Collections.sort(c.List);
    }

    /** Считывает и исполняет скрипт из указанного файла.
     * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме*/
    public static boolean Execute_script(Collect c, String s) {
        boolean programIsWorking = true;
        Reader reader;
        try {
            reader = new Reader(s);
            if(RecursionHandler.SmartAdd(s)) {
                String[] com;
                String line = reader.read();
                while (line != null && programIsWorking) {
                    com = AbstractReader.Splitter(line);
                    programIsWorking = Command.Switcher(reader, c, com[0], com[1]);
                    line = reader.read();
                }
            }
            //else
                //RecursionHandler.resetCounter();
        } catch (IncorrectFileNameException e) {
            System.out.println("Неверное имя файла");
        } catch (EndOfFile e) {
            System.out.println("Конец файла " + s);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return programIsWorking;
    }

    /** Сохраняет коллекцию в фаил */
    public static void Save(Collect c) {
        SaveManagement.SaveToFile(c);
    }

    /** Удаляет все элементы из коллекции */
    public static void Clear(Collect c) {
        c.List.clear();
    }

    /** Удаляет все элементы по его id */
    public static void Remove_by_id(AbstractReader reader, Collect c, String s) {
        int id;
        try {
            id = Route.idCheck.checker(Integer.parseInt(s));
        } catch (NumberFormatException | failedCheckException e) {
            id = reader.HandlerI("Введите int id: ", Route.idCheck);
        }
        Route r = c.searchById(id);
        if (r == null)
        {
            System.out.println("Такого элемента нет");
            return;
        }
        c.List.remove(r);
    }

    /** Перезаписывает элемент списка с указанным id */
    public static void Update(AbstractReader reader, Collect c, String s) throws EndOfFile {

        int id;
        try {
            id = Route.idCheck.checker(Integer.parseInt(s));
        } catch (NumberFormatException | failedCheckException e) {
            id = reader.HandlerI("Введите int id: ", Route.idCheck);
        }
        Route r = c.searchById(id);
        if (r == null) {
            System.out.println("Такого элемента нет");
            return;
        }
        String name = reader.HandlerS("Введите name String: ", Route.nameCheck);
        c.List.set(c.List.indexOf(r), toAdd(reader, id, name));
    }

    /** Выводит все элементы списка*/
    public static void Show(Collect c)
    {
        for (Route r : c.List)
        {
            System.out.println(r.toString());
        }
    }

    /** Добавляет элемент в список*/
    public static void Add(AbstractReader reader, Collect c, String s) throws EndOfFile {
        int id = c.GetRandId();
        c.List.add(toAdd(reader, id, s));
        Collections.sort(c.List);
    }

    public static Route toAdd(AbstractReader reader, int id, String s) throws EndOfFile {

        Route route = new Route();
        route.setId(id);

        try {
            Route.nameCheck.checker(s);
        } catch (failedCheckException e) {
            s = reader.HandlerS("Введите name String: ", Route.nameCheck);
        }
        System.out.println("поле name: " + s);
        route.setName(s);

        System.out.println("Ввoд полей Coordinates");
        int Cx = reader.HandlerI("      Введите int x: ", Coordinates.XCheck);
        Long Cy = reader.HandlerL("     Введите long y: ", Coordinates.YCheck);
        route.setCoordinates(new Coordinates(Cx,Cy));

        ZonedDateTime creationTime = ZonedDateTime.now();
        route.setCreationDate(creationTime);

        System.out.println("Ввoд полей Location to");
        Long x = reader.HandlerL("     Введите Long x: ", Location.XYZCheck);
        long y = reader.HandlerL("     Введите long y: ", Location.XYZCheck);
        long z = reader.HandlerL("     Введите long z: ", Location.XYZCheck);
        String name = reader.HandlerS("     Введите поле name: ", Location.nameCheck);
        route.setTo(new Location(x,y,z,name));

        System.out.println("Введите Boolean isFromNull");
        if (!reader.HandlerB("     Введите Bool: ", boolCheck)) {
            System.out.println("Ввoд полей Location from");
            x = reader.HandlerL("     Введите Long x: ", Location.XYZCheck);
            y = reader.HandlerL("     Введите long y: ", Location.XYZCheck);
            z = reader.HandlerL("     Введите long z: ", Location.XYZCheck);
            name = reader.HandlerS("     Введите поле name: ", Location.nameCheck);
            route.setFrom(new Location(x, y, z, name));
        }
        else
            route.setFrom(null);


        Long distance = reader.HandlerL("Введите поле distance ", Route.distanceCheck);
        route.setDistance(distance);

        return route;
    }

    public static Checker<Boolean> boolCheck = (Boolean B) -> {if (B!=null) return B; else throw new failedCheckException();};
}

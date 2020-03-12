import Exceptions.failedCheckException;

import java.util.LinkedList;

/** Класс, предотвращающий рекурсии */
public class RecursionHandler {
    private static LinkedList<String> Files = new LinkedList<>();
    private static String second;
    private static int counter = -1;
    public static boolean SmartAdd(String S)
    {
        if (counter == -1) {
            for (String file : Files) {
                if (file.equals(S)) {
                    second = S;
                    break;
                }
            }
            if (second == null)
            {
                Files.add(S);
                return true;
            }
            else
                return StartCounter();//
        }
        else if (!second.equals(S))
            return true;
        else if (counter > 1)
        {
            counter--;
            return true;
        }
        return false;
    }
    public static boolean StartCounter()
    {
        System.out.println("Найдено повторение файла!");
        counter = Console.Console.HandlerI("Введите количество повторений:", Check);

        return counter > 0;
    }
    public static void resetIfChanged()
    {
        if (Files.size()>0)
        {
            Files.clear();
            second = null;
            counter = -1;
        }
    }
    public static void resetCounter()//
    {
        counter = -1;
    }
    public static Checker<Integer> Check = (Integer I) -> {if (I!=null&&I<=10) return I; else throw new failedCheckException();};
}
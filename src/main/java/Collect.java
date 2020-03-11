import java.io.File;
import java.util.Date;
import java.util.LinkedList;

// Сделал класс для хранения и обработки LinkedList

/**
 * Класс для хранения и обработки LinkedList
 */
public class Collect {

    /** Дата создания списка */
    private Date date = new Date();
    /** Список, в котором хранятся элементы типа Route */
    public LinkedList<Route> List = new LinkedList<>();

    /** Метод, возвращающий список, удобный для сохранения в формат CSV */
    public static Collect StartFromSave(String[] Args)
    {
        if (Args.length > 0) {
            File saveFile = new File(Args[0]);
            if (saveFile.exists()) {
                SaveManagement.setFile(saveFile);
                return SaveManagement.ListFromSave();
            }
        }
        return new Collect();
    }

    /** Метод, осуществляющий поиск элемента по id */
    public Route searchById(Integer id)
    {
        for (Route r : List)
        {
            if (r.getId().equals(id))
                return r;
        }
        return null;
    }

    public Date getDate() {
        return date;
    }

    /** Метод, возвращающий уникальный id */
    public int GetRandId()
    {
        int id;
        do
        {
            id = (int) (1+Math.random() * (Integer.MAX_VALUE-1));
        } while (this.searchById(id) != null);
        return id;
    }
}

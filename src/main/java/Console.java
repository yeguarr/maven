import java.util.Scanner;

/**
 * Класс, считывающий строки из файла
*/
public class Console extends AbstractReader{
    public static Console Console = new Console();
    Console() {
        scan = new Scanner(System.in);
    }
    @Override
    public String read() {
        return scan.nextLine();
    }
}

import Exceptions.EndOfFile;

import java.util.Scanner;

/**
 * Класс, считывающий строки из консоли
*/
public class Console extends AbstractReader{
    public static Console Console = new Console();
    Console() {
        scan = new Scanner(System.in);
    }
    @Override
    public String read() throws EndOfFile {
        if (scan.hasNextLine())
            return scan.nextLine();
        throw new EndOfFile("Конец ввода косоли!");
    }
}

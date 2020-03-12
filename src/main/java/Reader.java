import Exceptions.EndOfFile;
import Exceptions.IncorrectFileNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** класс для считывания команд с файла*/
public class Reader extends AbstractReader{
    public Reader(String file) throws IncorrectFileNameException, FileNotFoundException{

        File f = new File(file);
        if(!f.exists())
            throw new IncorrectFileNameException("Ошибка! Файл не найден!");
        scan = new Scanner(new File(file));
    }
    /** считывание строки */
    public String read() throws EndOfFile {
        if (scan.hasNextLine())
        {
            String line = scan.nextLine();
            System.out.print(line + "\n");
            return line;
        }
        throw new EndOfFile("Преждевременный конец файла!");
    }
}

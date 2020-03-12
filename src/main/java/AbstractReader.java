import Exceptions.EndOfFile;
import Exceptions.failedCheckException;

import java.util.Scanner;


/**
 *  Абстрактный класс, предназначенный для считаывания команд с консоли и считывания команд из файла
 */

public abstract class AbstractReader {
    /** protected конструктор*/
    protected AbstractReader() {}
    public abstract String read() throws EndOfFile;
    /** Поле типа Scanner, предназначенное для считывания строки либо из файла, либо из консоли */
    protected Scanner scan;

    public static String[] Splitter(String line)
    {
        String[] s = line.split(" ", 2);
        if (s.length == 2)
        {
            s[1] = s[1].trim();
            return s;
        }
        else
            return new String[]{s[0], ""};
    }

    /** Метод для парсинга Integer */
    public Integer HandlerI(String s, Checker<Integer> c) throws EndOfFile {
        String line;

        while(true){
            try{
                System.out.print(s);
                line = this.read();
                if (line == null)
                    throw new EndOfFile("Преждевременный конец файла!");
                else if (line.equals(""))
                    return c.checker(null);
                return c.checker(Integer.parseInt(line));
            } catch (NumberFormatException e){
                System.out.println("Ошибка ввода, попробуйте еще раз");
            } catch (failedCheckException e) {
                System.out.println("Условия не соблюдены, попробуйте еще раз");
            }
        }
    }

    /** Метод для парсинга Long*/
    public Long HandlerL(String s, Checker<Long> c) throws EndOfFile {
        String line;

        while(true){
            try{
                System.out.print(s);
                line = this.read();
                if (line == null)
                    throw new EndOfFile("Преждевременный конец файла!");
                else if (line.equals(""))
                    return c.checker(null);
                return c.checker(Long.parseLong(line));
            } catch (NumberFormatException e){
                System.out.println("Ошибка ввода, попробуйте еще раз");
            } catch (failedCheckException e) {
                System.out.println("Условия не соблюдены, попробуйте еще раз");
            }
        }
    }

    /** Метод для парсинга String*/
    public String HandlerS(String s, Checker<String> c) throws EndOfFile {
        String line;
        while(true){
            try{
                System.out.print(s);
                line = this.read();
                if (line == null)
                    throw new EndOfFile("Преждевременный конец файла!");
                else if (line.equals(""))
                    return c.checker(null);
                return c.checker(line);
            }  catch (failedCheckException e) {
                System.out.println("Условия не соблюдены, попробуйте еще раз");
            }
        }
    }

    /** Метод для парсинга Boolean*/
    public Boolean HandlerB(String s, Checker<Boolean> c) throws EndOfFile {
        String line;

        while (true) {
            try {
                System.out.print(s);
                line = this.read();
                if (line == null)
                    throw new EndOfFile("Преждевременный конец файла!");
                else if (line.equals(""))
                    return c.checker(null);
                return c.checker(parseBoolean(line));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода, попробуйте еще раз");
            } catch (failedCheckException e) {
                System.out.println("Условия не соблюдены, попробуйте еще раз");
            }
        }
    }

    public static Boolean parseBoolean(String s)
    {
        if (s.equalsIgnoreCase("true"))
            return true;
        else if (s.equalsIgnoreCase("false"))
            return false;
        throw new NumberFormatException();
    }
}




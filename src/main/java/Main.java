import Exceptions.EndOfFile;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Main {
    public static void main(String[] args) {
        final long start = System.nanoTime();
        Signal.handle(new Signal("INT"), new SignalHandler() {
            public void handle(Signal sig) {
                System.out.println("Программа завершает работу");
                //if (Console.Console.HandlerB("Введите Bool: ", Command.boolCheck)) {
                    System.out.format("\nПрограмм работала %f сек.\n", (System.nanoTime() - start) / 1e9f);//ctrl-c
                    System.exit(0);
                //}
            }
        });

        Collect collect = Collect.StartFromSave(args);

        try {
            boolean programIsWorking = true;
            String[] com;
            while (programIsWorking) {
                com = AbstractReader.Splitter(Console.Console.read());
                programIsWorking = Command.Switcher(Console.Console, collect, com[0], com[1]);
                RecursionHandler.resetIfChanged();
            }
        } catch (EndOfFile e)
        {
            System.out.println("Неожиданное завершение работы консоли");//ctrl-d
        }
    }
}

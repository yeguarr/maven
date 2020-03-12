import Exceptions.EndOfFile;

public class Main {
    public static void main(String[] args) {
        //final long start = System.nanoTime();
        //Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.format("\nProgram execution took %f seconds\n", (System.nanoTime() - start) / 1e9f)));


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
            System.out.println("Неожиданное завершение работы консоли");
        }
    }
}

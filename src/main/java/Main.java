
public class Main {
    public static void main(String[] args) {
            Collect collect = Collect.StartFromSave(args);

            boolean programIsWorking = true;
            String[] com;
            while (programIsWorking) {
                com = AbstractReader.Splitter(Console.Console.read());
                programIsWorking = Command.Switcher(Console.Console, collect, com[0], com[1]);
                RecursionHandler.resetIfChanged();
            }
    }
}

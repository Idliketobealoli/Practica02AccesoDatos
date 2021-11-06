import filterClasses.CreateHTML;
import filterClasses.ProcessData;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments.");
            System.out.println("Please, try inputting exactly two arguments: [city] [directory]");
            System.exit(991_107_999);
        }
        ProcessData pd = new ProcessData(args[0]);
        CreateHTML html = new CreateHTML(pd.desiredCity, args[1]);
    }
}

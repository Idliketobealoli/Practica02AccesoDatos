import filterClasses.CreateHTML;
import filterClasses.Jaxb;
import filterClasses.MarkDownGenerator;
import filterClasses.ProcessData;
import model.Result;
import javax.xml.bind.JAXBException;

public class App {
    public static void main(String[] args) {
        /*
        if (args.length != 2) {
            System.out.println("Invalid number of arguments.");
            System.out.println("Please, try inputting exactly two arguments: [city] [directory]");
            System.exit(991_107_999);
        }

         */
        ProcessData pd = new ProcessData("Majadahonda"); // args[0]
        CreateHTML html = new CreateHTML(pd.desiredCity, "C:\\Users\\User\\Documents"); // args[1] ---- mi carpeta de pruebas: C:\Users\User\Documents
        Result result = new Result(pd.desiredCity);
        try {
            Jaxb jaxb = Jaxb.getInstance();
            jaxb.saveIntoDB(result);

            MarkDownGenerator mdg = new MarkDownGenerator("Majadahonda"); // args[0]
        } catch (JAXBException e) {
            System.out.println("An error occurred while saving data in the database.");
            e.printStackTrace();
            System.exit(1707_1707);
        }
    }
}

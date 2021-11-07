package filterClasses;

import model.Result;
import model.Results;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Jaxb {
    private static final String PATH_TO_MEDICIONES_XML = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "main" + File.separator + "resources" + File.separator + "db" + File.separator + "mediciones.xml";
    private static Jaxb jaxb = null;

    private Jaxb (){}

    public static Jaxb getInstance() {
        if (jaxb == null) {
            jaxb = new Jaxb();
        }
        return jaxb;
    }

    public void saveIntoDB (Result result) throws JAXBException {
        /*
        File file = new File(PATH_TO_MEDICIONES_XML);
        if (!file.exists()) {

        }
        */
        JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Results results = (Results) unmarshaller.unmarshal(new File(PATH_TO_MEDICIONES_XML));
        if (!(results.getResults().contains(result))) {
            results.getResults().add(result);
        }
        resultsToXML(results);
    }

    private void resultsToXML(Results results) throws JAXBException {
        JAXBContext jaxbcontext = JAXBContext.newInstance(Results.class);
        Marshaller marshaller = jaxbcontext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(results, new File(PATH_TO_MEDICIONES_XML));
    }
}

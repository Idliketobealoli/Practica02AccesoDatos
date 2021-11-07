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
    private Results r = new Results();
    private final JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);

    private Jaxb () throws JAXBException {}

    public static Jaxb getInstance() throws JAXBException {
        if (jaxb == null) {
            jaxb = new Jaxb();
        }
        return jaxb;
    }

    public void saveIntoDB (Result result) throws JAXBException {
        File file = new File(PATH_TO_MEDICIONES_XML);
        if (!file.exists()) {
            jaxb.marshall(result);
        } else {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Results results = (Results) unmarshaller.unmarshal(new File(PATH_TO_MEDICIONES_XML));
            if (!(results.getResultList().contains(result))) {
                results.getResultList().add(result);
            }
            resultsToXML(results);
        }
    }

    private void marshall(Result result) throws JAXBException {
        Results results = new Results();
        results.getResultList().add(result);
        resultsToXML(results);
    }

    private void resultsToXML(Results results) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(results, new File(PATH_TO_MEDICIONES_XML));
    }
}

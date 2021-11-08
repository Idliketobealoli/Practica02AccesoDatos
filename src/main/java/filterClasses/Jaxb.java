package filterClasses;

import model.Result;
import model.Results;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Jaxb {
    private static final String PATH_TO_MEDICIONES_XML = System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml";
    private static Jaxb jaxb = null;
    private Results r = new Results();

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
            jaxb.createCosas(result);
        } else {
            JAXBContext jaxbContext = JAXBContext.newInstance(model.Results.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Results results = (Results) unmarshaller.unmarshal(new File(PATH_TO_MEDICIONES_XML));
            boolean duplicate = false;
            for (Result res : results.getResultList()) {
                if (res.getCreationDateResult().equalsIgnoreCase(result.getCreationDateResult()) &&
                res.getCityResult().getNameCity().equalsIgnoreCase(result.getCityResult().getNameCity())) {
                    duplicate = true;
                }
            }
            if (!duplicate) {
                results.getResultList().add(result);
            }
            resultsToXML(results);
        }
    }

    private void createCosas(Result result) throws JAXBException {
        Results results = new Results();
        results.getResultList().add(result);
        resultsToXML(results);
    }

    private void resultsToXML(Results results) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(model.Results.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(results, new File(PATH_TO_MEDICIONES_XML));
    }
}

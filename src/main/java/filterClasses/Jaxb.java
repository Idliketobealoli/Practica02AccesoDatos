package filterClasses;

import model.Result;
import model.Results;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Esta clase guarda los datos generados en esta ejecución del programa en la base de datos únicamente si no son duplicados
 * (duplicados == misma ciudad y mismo dia de creación)
 * @author Daniel Rodríguez Muñoz
 */
public class Jaxb {
    private static final String PATH_TO_MEDICIONES_XML = System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml";
    private static Jaxb jaxb = null;
    private Results r = new Results();

    private Jaxb () throws JAXBException {}

    /**
     * singleton de libro
     * @return instancia de la clase Jaxb
     * @throws JAXBException
     */
    public static Jaxb getInstance() throws JAXBException {
        if (jaxb == null) {
            jaxb = new Jaxb();
        }
        return jaxb;
    }

    /**
     * método principal de la clase. Guarda los datos en la base de datos.
     * Si no existe, la crea.
     * Para ello, lee la base de datos (si existe) y saca el objeto Results asociado.
     * A dicho objeto, le añade el objeto Result generado en esta ejecución del programa sólo si es distinto que
     * los otros que tiene guardados en su arraylist de objetos result (se entiende distintos si tienen diferente ciudad
     * y a la vez diferente día de generación.
     * @author Daniel Rodríguez Muñoz
     * @param result
     * @throws JAXBException
     */
    public void saveIntoDB (Result result) throws JAXBException {
        String dbDirectoryURI = System.getProperty("user.dir") + File.separator + "db";
        File dbDirectory = new File(dbDirectoryURI);
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();
        }
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

    /**
     * Si no existe la base de datos, este método es llamado, el cual crea un nuevo objeto Results,
     * le añade el objeto Result de esta ejecución y crea la base de datos a partir de dicho objeto.
     * @author Daniel Rodríguez Muñoz
     * @param result
     * @throws JAXBException
     */
    private void createCosas(Result result) throws JAXBException {
        Results results = new Results();
        results.getResultList().add(result);
        resultsToXML(results);
    }

    /**
     * Coge el objeto Results y lo mete en la base de datos.
     * @author Daniel Rodríguez Muñoz
     * @param results
     * @throws JAXBException
     */
    private void resultsToXML(Results results) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(model.Results.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(results, new File(PATH_TO_MEDICIONES_XML));
    }
}

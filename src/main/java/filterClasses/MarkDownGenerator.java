package filterClasses;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Esta clase genera el informe markdown y el informe por el terminal
 * @author Daniel Rodriguez Muñoz
 */
public class MarkDownGenerator {
    String cityName;
    String path;
    String pathToMedicionesXml = System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml";

    public MarkDownGenerator (String city){
        this.cityName = city;
        this.path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "informe-ciudad-" + cityName + ".md";

        generateMarkdown(path);
    }

    /**
     * A partir de un path, genera un documento md con el informe completo de la ciudad
     * que hayamos creado en esta ejecución del programa.
     * @author Daniel Rodríguez Muñoz
     * @param path
     */
    private void generateMarkdown(String path){
        Path pathAsPath = Path.of(path);
        File markdown = pathAsPath.toFile();
        if (!markdown.exists()) {
            try (FileWriter writer = new FileWriter(markdown)) {
                writer.write(writeMarkdown(cityName));
                Desktop.getDesktop().browse(pathAsPath.toUri());
            } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Este método genera el markdown.
     * @param cityName
     * @return markdown document's text
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    private String writeMarkdown(String cityName) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        System.out.println("MEDICIONES DE " + cityName + "\n\n" +
                "Medidas meteorológicas:\n");
        sb.append("#MEDICIONES DE " + cityName + "\n\n" +
                "##MEDIAS METEOROLOGICAS:\n");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(pathToMedicionesXml);
        XPathFactory xpfact = XPathFactory.newInstance();
        XPath xpath = xpfact.newXPath();
        XPathExpression mediasMeteo = xpath.compile("//result_list/resultList/cityResult[@name_city='"+cityName+"']/" +
                "meteo_measurements/meteoMeasurementsCity/averageValueMeasurement/text()");
        XPathExpression namesMeteo = xpath.compile("//result_list/resultList/cityResult[@name_city='"+cityName+"']/" +
                "meteo_measurements/meteoMeasurementsCity/@magnitude_name");
        Object resultMeteo = mediasMeteo.evaluate(doc, XPathConstants.NODESET);
        Object resultNamesMeteo = namesMeteo.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesMeteo = (NodeList) resultMeteo;
        NodeList nodesNamesMeteo = (NodeList) resultNamesMeteo;
        for (int i = 0; i < nodesMeteo.getLength(); i++) {
            System.out.println(" -> " + nodesNamesMeteo.item(i).getNodeValue() + "\n" +
                    " -> -> " + nodesMeteo.item(i).getNodeValue() + "\n");
            sb.append("###"+ nodesNamesMeteo.item(i).getNodeValue() + "\n" +
                    "- Media: " + nodesMeteo.item(i).getNodeValue() + "\n");
        }
        System.out.println("\n" +
                "Medidas contaminantes:\n");
        sb.append("\n" +
                "##MEDIAS CONTAMINANTES:\n");
        XPathExpression mediasCont = xpath.compile("//result_list/resultList/cityResult[@name_city='"+cityName+"']/" +
                "contamination_measurements/contaminationMeasurementsCity/averageValueMeasurement/text()");
        XPathExpression namesCont = xpath.compile("//result_list/resultList/cityResult[@name_city='"+cityName+"']/" +
                "contamination_measurements/contaminationMeasurementsCity/@magnitude_name");
        Object resultCont = mediasCont.evaluate(doc, XPathConstants.NODESET);
        Object resultNamesCont = namesCont.evaluate(doc, XPathConstants.NODESET);
        NodeList nodesCont = (NodeList) resultCont;
        NodeList nodesNamesCont = (NodeList) resultNamesCont;
        for (int i = 0; i < nodesCont.getLength(); i++) {
            System.out.println(" -> " + nodesNamesCont.item(i).getNodeValue() + "\n" +
                    " -> -> " + nodesCont.item(i).getNodeValue() + "\n");
            sb.append("###"+ nodesNamesCont.item(i).getNodeValue() + "\n" +
                    "- Media: " + nodesCont.item(i).getNodeValue() + "\n");
        }
        System.out.println("\n\n" +
                "*** Informe generado automáticamente por Jaime Salcedo y Daniel Rodríguez ***");
        sb.append("\n\n\n\n" +
                "####Informe generado automáticamente por Jaime Salcedo y Daniel Rodríguez.");
        return sb.toString();
    }
}

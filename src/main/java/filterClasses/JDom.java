package filterClasses;

import model.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encargará de coger las diferentes listas de objetos CalidadX y transformarlas en xmls, así como de
 * leer dichos XML para pasarlos de nuevo a listas CalidadX.
 * @author Jaime Salcedo Vallejo
 */
public class JDom {

    /**
     * Este método coge una lista de objetos Calidad_aire_datos y genera un xml en la carpeta xml con el nombre que le
     * pasemos por parámetro.xml
     * @author Jaime Salcedo Vallejo
     * @param list
     * @param name
     * @return uri al xml creado
     * @throws IOException
     */
    public String createXMLcad(List<Calidad_aire_datos> list, String name) throws IOException {
        String uri = System.getProperty("user.dir") + File.separator + "xml" + File.separator + name + ".xml";
        String xmlDirectoryURI = System.getProperty("user.dir") + File.separator + "xml";
        File xmlDirectory = new File(xmlDirectoryURI);
        if (!xmlDirectory.exists()) {
            xmlDirectory.mkdirs();
        }
        Document doc = new Document();
        Element root = new Element("lista_datos");
        list.forEach(x ->
                root.addContent(createElementsCAD(x)));
        doc.setRootElement(root);
        BufferedWriter bw = new BufferedWriter(new FileWriter(uri));
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        xmlOut.output(doc, bw);
        return uri;
    }

    /**
     * Este método crea los diferentes elementos que contendrá el elemento Calidad_aire_datos, y lo devuelve como un
     * elemento.
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return elementos hijos de Calidad_aire_datos
     */
    private Element createElementsCAD(Calidad_aire_datos x) {
        Element cad = new Element("calidad_aire_datos");
        cad.setAttribute("magnitud", String.valueOf(x.getMagnitud()));
        Element provincia = new Element("provincia");
        provincia.addContent(String.valueOf(x.getProvincia()));
        Element municipio = new Element("municipio");
        municipio.addContent(String.valueOf(x.getMunicipio()));
        Element estacion = new Element("estacion");
        estacion.addContent(String.valueOf(x.getEstacion()));
        Element punto_muestreo = new Element("punto_muestreo");
        punto_muestreo.addContent(x.getPunto_muestreo());
        Element fecha_medicion = new Element("fecha_medicion");
        fecha_medicion.addContent(String.valueOf(x.getFecha_medicion()));
        Element listH = new Element("lista_h");
        x.getListH().forEach(y -> listH.addContent(createElementsH(y)));
        Element listV = new Element("lista_v");
        x.getListV().forEach(y -> listV.addContent(createElementsV(y)));
        Element hours = new Element("horas");
        x.getHour().forEach(y -> hours.addContent(createElementsHour(y)));
        cad.addContent(provincia);
        cad.addContent(municipio);
        cad.addContent(estacion);
        cad.addContent(punto_muestreo);
        cad.addContent(fecha_medicion);
        cad.addContent(listH);
        cad.addContent(listV);
        cad.addContent(hours);

        return cad;
    }

    /**
     * Crea un elemento hora y le asigna el valor entero y.
     * @author Jaime Salcedo Vallejo
     * @param y
     * @return elemento hora.
     */
    private Element createElementsHour(Integer y) {
        Element hour = new Element("hora");
        hour.addContent(String.valueOf(y));
        return hour;
    }

    /**
     * lo mismo que el método createElementsHour, pero con doubles y genera elementos h.
     * @author Jaime Salcedo Vallejo
     * @param y
     * @return elemento h
     */
    private Element createElementsH(Double y) {
        Element h = new Element("h");
        h.addContent(String.valueOf(y));
        return h;
    }

    /**
     * lo mismo que el método createElementsHour, pero con characters y genera elementos v.
     * @author Jaime Salcedo Vallejo
     * @param y
     * @return elemento v
     */
    private Element createElementsV(Character y) {
        Element v = new Element("v");
        v.addContent(String.valueOf(y));
        return v;
    }


    /**
     * Igual que el método createXMLcad, pero para listas de Calidad_aire_estaciones.
     * @author Jaime Salcedo Vallejo
     * @param list
     * @param name
     * @return uri al xml creado
     * @throws IOException
     */
    public String createXMLcae(List<Calidad_aire_estaciones> list, String name) throws IOException {
        String uri = System.getProperty("user.dir") + File.separator +"xml" + File.separator + name + ".xml";
        Document doc = new Document();
        Element root = new Element("lista_datos");
        list.forEach(x ->
                root.addContent(createElementsCAE(x)));
        doc.setRootElement(root);
        BufferedWriter bw = new BufferedWriter(new FileWriter(uri));
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        xmlOut.output(doc, bw);
        return uri;
    }

    /**
     * Igual que createElementsCAD, pero para objetos Calidad_aire_estaciones.
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return elementos hijos de Calidad_aire_estaciones
     */
    private Element createElementsCAE(Calidad_aire_estaciones x) {
        Element cae = new Element("calidad_aire_estaciones");
        cae.setAttribute("codigo_estacion", String.valueOf(x.getEstacion_codigo()));
        Element zcad = new Element("zona_calidad_aire_descripcion");
        zcad.addContent(x.getZona_calidad_aire_descripcion());
        Element em = new Element("estacion_municipio");
        em.addContent(x.getEstacion_municipio());
        Element efa = new Element("estacion_fecha_alta");
        efa.addContent(x.getEstacion_fecha_alta());
        Element eta = new Element("estacion_tipo_area");
        eta.addContent(x.getEstacion_tipo_area());
        Element ete = new Element("estacion_tipo_estacion");
        ete.addContent(x.getEstacion_tipo_estacion());
        Element esr = new Element("estacion_subarea_rural");
        esr.addContent(x.getEstacion_subarea_rural());
        Element edp = new Element("estacion_direccion_postal");
        edp.addContent(x.getEstacion_direccion_postal());
        Element ecUEx = new Element("estacion_coord_UTM_ETRS89_x");
        ecUEx.addContent(String.valueOf(x.getEstacion_coord_UTM_ETRS89_x()));
        Element ecUEy = new Element("estacion_coord_UTM_ETRS89_y");
        ecUEy.addContent(String.valueOf(x.getEstacion_coord_UTM_ETRS89_y()));
        Element eclong = new Element("estacion_coord_longitud");
        eclong.addContent(x.getEstacion_coord_longitud());
        Element eclat = new Element("estacion_coord_latitud");
        eclat.addContent(x.getEstacion_coord_latitud());
        Element ealt = new Element("estacion_altitud");
        ealt.addContent(String.valueOf(x.getEstacion_altitud()));
        Element eaNO = new Element("estacion_analizador_NO");
        eaNO.addContent(x.getEstacion_analizador_NO());
        Element eaNO2 = new Element("estacion_analizador_NO2");
        eaNO2.addContent(x.getEstacion_analizador_NO2());
        Element eaPM10 = new Element("estacion_analizador_PM10");
        eaPM10.addContent(x.getEstacion_analizador_PM10());
        Element eaPM2_5 = new Element("estacion_analizador_PM2_5");
        eaPM2_5.addContent(x.getEstacion_analizador_PM2_5());
        Element eaO3 = new Element("estacion_analizador_O3");
        eaO3.addContent(x.getEstacion_analizador_O3());
        Element eaTOL = new Element("estacion_analizador_TOL");
        eaTOL.addContent(x.getEstacion_analizador_TOL());
        Element eaBEN = new Element("estacion_analizador_BEN");
        eaBEN.addContent(x.getEstacion_analizador_BEN());
        Element eaXIL = new Element("estacion_analizador_XIL");
        eaXIL.addContent(x.getEstacion_analizador_XIL());
        Element eaCO = new Element("estacion_analizador_CO");
        eaCO.addContent(x.getEstacion_analizador_CO());
        Element eaSO2 = new Element("estacion_analizador_SO2");
        eaSO2.addContent(x.getEstacion_analizador_NO());
        Element eaHCT = new Element("estacion_analizador_HCT");
        eaHCT.addContent(x.getEstacion_analizador_HCT());
        Element eaHNM = new Element("estacion_analizador_HNM");
        eaHNM.addContent(x.getEstacion_analizador_HNM());
        cae.addContent(zcad);
        cae.addContent(em);
        cae.addContent(efa);
        cae.addContent(eta);
        cae.addContent(ete);
        cae.addContent(esr);
        cae.addContent(edp);
        cae.addContent(ecUEx);
        cae.addContent(ecUEy);
        cae.addContent(eclong);
        cae.addContent(eclat);
        cae.addContent(ealt);
        cae.addContent(eaNO);
        cae.addContent(eaNO2);
        cae.addContent(eaPM10);
        cae.addContent(eaPM2_5);
        cae.addContent(eaO3);
        cae.addContent(eaTOL);
        cae.addContent(eaBEN);
        cae.addContent(eaXIL);
        cae.addContent(eaCO);
        cae.addContent(eaSO2);
        cae.addContent(eaHCT);
        cae.addContent(eaHNM);

        return cae;
    }

    /**
     * Igual que el método createXMLcad, pero para listas de Calidad_aire_zonas.
     * @author Jaime Salcedo Vallejo
     * @param list
     * @param name
     * @return uri al xml creado
     * @throws IOException
     */
    public String createXMLcaz(List<Calidad_aire_zonas> list, String name) throws IOException {
        String uri = System.getProperty("user.dir") + File.separator +"xml" + File.separator + name + ".xml";
        Document doc = new Document();
        Element root = new Element("lista_datos");
        list.forEach(x ->
                root.addContent(createElementsCAZ(x)));
        doc.setRootElement(root);
        BufferedWriter bw = new BufferedWriter(new FileWriter(uri));
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        xmlOut.output(doc, bw);
        return uri;
    }

    /**
     * Igual que createElementsCAD, pero para objetos Calidad_aire_zonas.
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return elementos hijos de Calidad_aire_zonas
     */
    private Element createElementsCAZ(Calidad_aire_zonas x) {
        Element caz = new Element("calidad_aire_zonas");
        caz.setAttribute("codigo", String.valueOf(x.getZona_calidad_aire_codigo()));
        Element zcad = new Element("zona_calidad_aire_descripcion");
        zcad.addContent(x.getZona_calidad_aire_descripcion());
        Element zcam = new Element("zona_calidad_aire_municipio");
        zcam.addContent(x.getZona_calidad_aire_municipio());
        caz.addContent(zcad);
        caz.addContent(zcam);

        return caz;
    }

    /**
     * Este método lee el xml indicado por la uri y devuelve una lista de objetos Calidad_aire_datos a partir de él.
     * @author Jaime Salcedo Vallejo
     * @param uri
     * @return lista de Calidad_aire_datos
     * @throws IOException
     * @throws JDOMException
     */
    public List<Calidad_aire_datos> readCadXML(String uri) throws IOException, JDOMException {
        List<Calidad_aire_datos> cad = new ArrayList<>();
        Document doc = null;
        doc = getSaxParsedDocument(uri);
        Element root = doc.getRootElement();
        root.getChildren().forEach(x -> cad.add(elementsToCAD(x)));
        return cad;
    }

    /**
     * Convierte un elemento x pasado por parámetro en un objeto Calidad_aire_datos
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return Calidad_aire_datos
     */
    private Calidad_aire_datos elementsToCAD(Element x) {
        Calidad_aire_datos cad = new Calidad_aire_datos();
        cad.setMagnitud(Integer.parseInt(x.getAttributeValue("magnitud")));
        cad.setProvincia(Integer.parseInt(x.getChildText("provincia")));
        cad.setMunicipio(Integer.parseInt(x.getChildText("municipio")));
        cad.setEstacion(Integer.parseInt(x.getChildText("estacion")));
        cad.setPunto_muestreo(x.getChildText("punto_muestreo"));

        return cad;
    }

    /**
     * Igual que readCadXML, pero para el xml de Cae y devolviendo una lista de objetos Cae.
     * @author Jaime Salcedo Vallejo
     * @param uri
     * @return lista de cae
     * @throws IOException
     * @throws JDOMException
     */
    public List<Calidad_aire_estaciones> readCaeXML(String uri) throws IOException, JDOMException {
        List<Calidad_aire_estaciones> cae = new ArrayList<>();
        Document doc = null;
        doc = getSaxParsedDocument(uri);
        Element root = doc.getRootElement();
        root.getChildren().forEach(x -> cae.add(elementsToCAE(x)));
        return cae;
    }

    /**
     * Igual que elementsToCAD, pero para CAE.
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return cae
     */
    private Calidad_aire_estaciones elementsToCAE(Element x) {
        Calidad_aire_estaciones cae = new Calidad_aire_estaciones();
        cae.setEstacion_codigo(Integer.parseInt(x.getAttributeValue("codigo_estacion")));
        cae.setZona_calidad_aire_descripcion(x.getChildText("zona_calidad_aire_descripcion"));
        cae.setEstacion_municipio(x.getChildText("estacion_municipio"));
        cae.setEstacion_fecha_alta(x.getChildText("estacion_feccha alta"));
        cae.setEstacion_tipo_area(x.getChildText("estacion_tipo_area"));
        cae.setEstacion_tipo_estacion(x.getChildText("estacion_tipo_estacion"));
        cae.setEstacion_subarea_rural(x.getChildText("estacion_subarea_rural"));
        cae.setEstacion_direccion_postal(x.getChildText("estacion_direccion_postal"));
        cae.setEstacion_coord_UTM_ETRS89_x(Integer.parseInt(x.getChildText("estacion_coord_UTM_ETRS89_x")));
        cae.setEstacion_coord_UTM_ETRS89_y(Integer.parseInt(x.getChildText("estacion_coord_UTM_ETRS89_y")));
        cae.setEstacion_coord_longitud(x.getChildText("estacion_coord_longitud"));
        cae.setEstacion_coord_latitud(x.getChildText("estacion_coord_latitud"));
        cae.setEstacion_altitud(Integer.parseInt(x.getChildText("estacion_altitud")));
        cae.setEstacion_analizador_NO(x.getChildText("estacion_analizador_NO"));
        cae.setEstacion_analizador_NO2(x.getChildText("estacion_analizador_NO2"));
        cae.setEstacion_analizador_PM10(x.getChildText("estacion_analizador_PM10"));
        cae.setEstacion_analizador_PM2_5(x.getChildText("estacion_analizador_PM2_5"));
        cae.setEstacion_analizador_O3(x.getChildText("estacion_analizador_O3"));
        cae.setEstacion_analizador_TOL(x.getChildText("estacion_analizador_TOL"));
        cae.setEstacion_analizador_BEN(x.getChildText("estacion_analizador_BEN"));
        cae.setEstacion_analizador_XIL(x.getChildText("estacion_analizador_XIL"));
        cae.setEstacion_analizador_CO(x.getChildText("estacion_analizador_CO"));
        cae.setEstacion_analizador_SO2(x.getChildText("estacion_analizador_SO2"));
        cae.setEstacion_analizador_HCT(x.getChildText("estacion_analizador_HCT"));
        cae.setEstacion_analizador_HNM(x.getChildText("estacion_analizador_HNM"));

        return cae;

    }

    /**
     * Igual que readCadXML, pero para CAZ.
     * @author Jaime Salcedo Vallejo
     * @param uri
     * @return lista de CAZ
     * @throws IOException
     * @throws JDOMException
     */
    public List<Calidad_aire_zonas> readCazXML(String uri) throws IOException, JDOMException {
        List<Calidad_aire_zonas> caz = new ArrayList<>();
        Document doc = null;
        doc = getSaxParsedDocument(uri);
        Element root = doc.getRootElement();
        root.getChildren().forEach(x -> caz.add(elementsToCAZ(x)));
        return caz;
    }

    /**
     * Igual que elementsToCAD, pero para CAZ.
     * @author Jaime Salcedo Vallejo
     * @param x
     * @return CAZ
     */
    private Calidad_aire_zonas elementsToCAZ(Element x) {
        Calidad_aire_zonas caz = new Calidad_aire_zonas();
        caz.setZona_calidad_aire_codigo(Integer.parseInt(x.getAttributeValue("codigo")));
        caz.setZona_calidad_aire_descripcion(x.getChildText("zona_calidad_aire_descripcion"));
        caz.setZona_calidad_aire_municipio(x.getChildText("zona_calidad_aire_municipio"));

        return caz;
    }

    /**
     * Este método crea un documento y mediante un SAXBuilder y una uri lo deja preparado para ser operado con JDom.
     * @author Jaime Salcedo Vallejo
     * @param uri
     * @return document
     * @throws IOException
     * @throws JDOMException
     */
    private Document getSaxParsedDocument(String uri) throws IOException, JDOMException {
        Document document = null;
        SAXBuilder saxBuilder = new SAXBuilder();
        document = saxBuilder.build(uri);
        return document;
    }
}

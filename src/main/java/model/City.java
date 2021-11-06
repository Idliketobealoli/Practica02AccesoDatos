package model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase será la que utilicemos para plasmarla en el html. Se compone del nombre de la ciudad,
 * las fechas en las que fueron registradas la primera y la última medición en dicha ciudad,
 * una lista de nombres de estaciones asociadas, una lista de measurements meteorológicas y una
 * lista de measurements de contaminantes.
 * @author Daniel Rodríguez Muñoz
 * @see City
 * @see filterClasses.ProcessData
 * @see filterClasses.CreateHTML
 */
@ToString
@XmlType(propOrder={"name", "firstMeasurementDate", "lastMeasurementDate", "associatedStationList", "meteoMeasurements", "contaminationMeasurements"})
public class City {
    private String name;
    private Date firstMeasurementDate; // fecha primera medicion registrada
    private Date lastMeasurementDate; // fecha ultima medicion registrada
    private ArrayList<String> associatedStationList = new ArrayList<>();
    private ArrayList<Measurement> meteoMeasurements = new ArrayList<>();
    private ArrayList<Measurement> contaminationMeasurements = new ArrayList<>();

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstMeasurementDate() {
        return firstMeasurementDate;
    }

    public void setFirstMeasurementDate(Date firstMeasurementDate) {
        this.firstMeasurementDate = firstMeasurementDate;
    }

    public Date getLastMeasurementDate() {
        return lastMeasurementDate;
    }

    public void setLastMeasurementDate(Date lastMeasurementDate) {
        this.lastMeasurementDate = lastMeasurementDate;
    }

    public ArrayList<String> getAssociatedStationList() {
        return associatedStationList;
    }

    public void setAssociatedStationList(ArrayList<String> associatedStationList) {
        this.associatedStationList = associatedStationList;
    }

    public ArrayList<Measurement> getMeteoMeasurements() {
        return meteoMeasurements;
    }

    public void setMeteoMeasurements(ArrayList<Measurement> meteoMeasurements) {
        this.meteoMeasurements = meteoMeasurements;
    }

    public ArrayList<Measurement> getContaminationMeasurements() {
        return contaminationMeasurements;
    }

    public void setContaminationMeasurements(ArrayList<Measurement> contaminationMeasurements) {
        this.contaminationMeasurements = contaminationMeasurements;
    }
}


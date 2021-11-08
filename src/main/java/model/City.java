package model;

import lombok.ToString;

import javax.xml.bind.annotation.*;
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
@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {
    @XmlAttribute(name = "name_city")
    private String nameCity;
    private Date firstMeasurementDateCity; // fecha primera medicion registrada
    private Date lastMeasurementDateCity; // fecha ultima medicion registrada
    @XmlElementWrapper(name = "associated_stations")
    private ArrayList<String> associatedStationListCity = new ArrayList<>();
    @XmlElementWrapper(name = "meteo_measurements")
    private ArrayList<Measurement> meteoMeasurementsCity = new ArrayList<>();
    @XmlElementWrapper(name = "contamination_measurements")
    private ArrayList<Measurement> contaminationMeasurementsCity = new ArrayList<>();

    public City(){}

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Date getFirstMeasurementDateCity() {
        return firstMeasurementDateCity;
    }

    public void setFirstMeasurementDateCity(Date firstMeasurementDateCity) {
        this.firstMeasurementDateCity = firstMeasurementDateCity;
    }

    public Date getLastMeasurementDateCity() {
        return lastMeasurementDateCity;
    }

    public void setLastMeasurementDateCity(Date lastMeasurementDateCity) {
        this.lastMeasurementDateCity = lastMeasurementDateCity;
    }

    public ArrayList<String> getAssociatedStationListCity() {
        return associatedStationListCity;
    }

    public void setAssociatedStationListCity(ArrayList<String> associatedStationListCity) {
        this.associatedStationListCity = associatedStationListCity;
    }

    public ArrayList<Measurement> getMeteoMeasurementsCity() {
        return meteoMeasurementsCity;
    }

    public void setMeteoMeasurementsCity(ArrayList<Measurement> meteoMeasurementsCity) {
        this.meteoMeasurementsCity = meteoMeasurementsCity;
    }

    public ArrayList<Measurement> getContaminationMeasurementsCity() {
        return contaminationMeasurementsCity;
    }

    public void setContaminationMeasurementsCity(ArrayList<Measurement> contaminationMeasurementsCity) {
        this.contaminationMeasurementsCity = contaminationMeasurementsCity;
    }
}


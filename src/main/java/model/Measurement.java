package model;

import lombok.ToString;
import org.jfree.chart.JFreeChart;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase será la que agrupe cada objeto Calidad_aire_datos en función de su magnitud,
 * así como contendrá la media, máxima y mínima de los datos que tenga.
 * Tendrá tambien el jFreeChart de dichos datos y la unidad de medida de estos.
 * Si son datos de lluvia, en lugar de tener máxima, mínima y media, tendrá una lista de días
 * en los que ha llovido y las medidas de dicha lluvia.
 * Estos objetos pojo serán almacenados en la clase City.
 * @author Jaime Salcedo Vallejo
 * @see Calidad_aire_datos
 * @see City
 * @see filterClasses.ProcessData
 */
@ToString
@XmlRootElement(name = "measurement")
@XmlAccessorType()
public class Measurement {
    private int magnitudeMeasurement;
    private String magnitudeNameMeasurement;
    private String measurementUnitName;
    private double averageValueMeasurement;
    private JFreeChart chart;
    private Date momentMinValueMeasurement;
    private double minValueMeasurement;
    private Date momentMaxValueMeasurement;
    private double maxValueMeasurement;
    private List<Calidad_aire_datos> data = new ArrayList<>();
    private List<Date> daysOnWhichRained = new ArrayList<>();
    private List<Double> rainMeasurements = new ArrayList<>();

    public Measurement(){}

    @XmlAttribute(name = "magnitude_id")
    public int getMagnitudeMeasurement() {
        return magnitudeMeasurement;
    }

    public void setMagnitudeMeasurement(int magnitudeMeasurement) {
        this.magnitudeMeasurement = magnitudeMeasurement;
    }

    @XmlAttribute(name = "magnitude_name")
    public String getMagnitudeNameMeasurement() {
        return magnitudeNameMeasurement;
    }

    public void setMagnitudeNameMeasurement(String magnitudeNameMeasurement) {
        this.magnitudeNameMeasurement = magnitudeNameMeasurement;
    }

    @XmlTransient
    public String getMeasurementUnitName() {
        return measurementUnitName;
    }

    public void setMeasurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }

    public double getAverageValueMeasurement() {
        return averageValueMeasurement;
    }

    public void setAverageValueMeasurement(double averageValueMeasurement) {
        this.averageValueMeasurement = averageValueMeasurement;
    }

    @XmlTransient
    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public Date getMomentMinValueMeasurement() {
        return momentMinValueMeasurement;
    }

    public void setMomentMinValueMeasurement(Date momentMinValueMeasurement) {
        this.momentMinValueMeasurement = momentMinValueMeasurement;
    }

    public double getMinValueMeasurement() {
        return minValueMeasurement;
    }

    public void setMinValueMeasurement(double minValueMeasurement) {
        this.minValueMeasurement = minValueMeasurement;
    }


    public Date getMomentMaxValueMeasurement() {
        return momentMaxValueMeasurement;
    }

    public void setMomentMaxValueMeasurement(Date momentMaxValueMeasurement) {
        this.momentMaxValueMeasurement = momentMaxValueMeasurement;
    }

    public double getMaxValueMeasurement() {
        return maxValueMeasurement;
    }

    public void setMaxValueMeasurement(double maxValueMeasurement) {
        this.maxValueMeasurement = maxValueMeasurement;
    }

    @XmlTransient
    public List<Calidad_aire_datos> getData() {
        return data;
    }

    public void setData(List<Calidad_aire_datos> data) {
        this.data = data;
    }

    @XmlTransient
    public List<Date> getDaysOnWhichRained() {
        return daysOnWhichRained;
    }

    public void setDaysOnWhichRained(List<Date> daysOnWhichRained) {
        this.daysOnWhichRained = daysOnWhichRained;
    }

    @XmlTransient
    public List<Double> getRainMeasurements() {
        return rainMeasurements;
    }

    public void setRainMeasurements(List<Double> rainMeasurements) {
        this.rainMeasurements = rainMeasurements;
    }
}

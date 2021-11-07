package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {"magnitude", "magnitudeName", "averageValue", "minValue",
        "momentMinValue", "maxValue", "momentMaxValue"})
public class Measurement {
    @XmlAttribute(name = "magnitude_id")
    private int magnitude;
    @XmlAttribute(name = "magnitude")
    private String magnitudeName;
    @XmlTransient
    private String measurementUnitName;
    @XmlElement(name = "average")
    private double averageValue;
    @XmlTransient
    private JFreeChart chart;
    @XmlElement(name = "moment_min_value")
    private Date momentMinValue;
    @XmlElement(name = "min")
    private double minValue;
    @XmlElement(name = "moment_max_value")
    private Date momentMaxValue;
    @XmlElement(name = "max")
    private double maxValue;
    @XmlTransient
    private List<Calidad_aire_datos> data = new ArrayList<>();
    @XmlTransient
    private List<Date> daysOnWhichRained = new ArrayList<>();
    @XmlTransient
    private List<Double> rainMeasurements = new ArrayList<>();

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public String getMagnitudeName() {
        return magnitudeName;
    }

    public void setMagnitudeName(String magnitudeName) {
        this.magnitudeName = magnitudeName;
    }

    public String getMeasurementUnitName() {
        return measurementUnitName;
    }

    public void setMeasurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public Date getMomentMinValue() {
        return momentMinValue;
    }

    public void setMomentMinValue(Date momentMinValue) {
        this.momentMinValue = momentMinValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }


    public Date getMomentMaxValue() {
        return momentMaxValue;
    }

    public void setMomentMaxValue(Date momentMaxValue) {
        this.momentMaxValue = momentMaxValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public List<Calidad_aire_datos> getData() {
        return data;
    }

    public void setData(List<Calidad_aire_datos> data) {
        this.data = data;
    }

    public List<Date> getDaysOnWhichRained() {
        return daysOnWhichRained;
    }

    public void setDaysOnWhichRained(List<Date> daysOnWhichRained) {
        this.daysOnWhichRained = daysOnWhichRained;
    }

    public List<Double> getRainMeasurements() {
        return rainMeasurements;
    }

    public void setRainMeasurements(List<Double> rainMeasurements) {
        this.rainMeasurements = rainMeasurements;
    }
}

package model;

import lombok.ToString;
import utils.Util;

import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * Esta clase pojo sirve para almacenar los datos de los informes generados,
 * almacenando un objeto city, su fecha de creación y un UUID.
 * @author Daniel Rodríguez Muñoz
 * @see City
 */
@ToString
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
    @XmlAttribute(name = "id")
    private UUID uuidResult;
    private String creationDateResult;
    private City cityResult;

    public Result(){}

    public Result(City city){
        this.cityResult = city;
        this.creationDateResult = Util.giveMeDateNow();
        this.uuidResult = UUID.randomUUID();
    }

    public UUID getUuidResult() {
        return uuidResult;
    }

    public void setUuidResult(UUID uuidResult) {
        this.uuidResult = uuidResult;
    }

    public String getCreationDateResult() {
        return creationDateResult;
    }

    public void setCreationDateResult(String creationDateResult) {
        this.creationDateResult = creationDateResult;
    }

    public City getCityResult() {
        return cityResult;
    }

    public void setCityResult(City city) {
        this.cityResult = city;
    }

}

package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlType(propOrder = {"uuid", "creationDate", "city"})
public class Result {
    @XmlAttribute(name = "id")
    private UUID uuid;
    @XmlElement(name = "creation_date")
    private String creationDate;
    @XmlElement(name = "city")
    private City city;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}

package model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@ToString
@XmlRootElement(name="results")
@XmlAccessorType(XmlAccessType.FIELD)
public class Results {
    @XmlElementWrapper(name = "results")
    ArrayList<Result> results;

    public Results() {
        results = new ArrayList<>();
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}

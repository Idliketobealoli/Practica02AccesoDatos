package model;

import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@ToString
@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
public class Results {
    @XmlElementWrapper(name = "result_list")
    private ArrayList<Result> resultList = new ArrayList<>();

    public Results(){}

    public ArrayList<Result> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<Result> resultList) {
        this.resultList = resultList;
    }
}

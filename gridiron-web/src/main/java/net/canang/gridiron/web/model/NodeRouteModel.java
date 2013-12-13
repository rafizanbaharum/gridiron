package net.canang.gridiron.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class NodeRouteModel {

    private Long id;
    private Double length;
    private List<CoordinateModel> path = new ArrayList<CoordinateModel>();


    public NodeRouteModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CoordinateModel> getPath() {
        return path;
    }

    public void setPath(List<CoordinateModel> path) {
        this.path = path;
    }

    public void addPath(CoordinateModel coordinateModel) {
        this.path.add(coordinateModel);
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}

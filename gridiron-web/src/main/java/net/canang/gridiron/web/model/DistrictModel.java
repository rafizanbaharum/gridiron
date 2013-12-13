package net.canang.gridiron.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class DistrictModel {

    private Long id;
    private String name;
    private int headCount;
    private CoordinateModel center;
    private List<CoordinateModel> bounds = new ArrayList<CoordinateModel>();

    public DistrictModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public CoordinateModel getCenter() {
        return center;
    }

    public void setCenter(CoordinateModel center) {
        this.center = center;
    }

    public List<CoordinateModel> getBounds() {
        return bounds;
    }

    public void setBounds(List<CoordinateModel> bounds) {
        this.bounds = bounds;
    }

    public void addBound(CoordinateModel coordinateModel) {
        this.bounds.add(coordinateModel);
    }
}

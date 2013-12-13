package net.canang.gridiron.web.model;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class NodeModel {

    private Long id;
    private String name;
    private String phone;
    private double x;
    private double y;

    public NodeModel(Long id, String name, String phone, double x, double y) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.x = x;
        this.y = y;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}

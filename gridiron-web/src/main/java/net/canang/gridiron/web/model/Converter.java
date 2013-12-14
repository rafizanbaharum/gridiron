package net.canang.gridiron.web.model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;
import net.canang.gridiron.core.model.District;
import net.canang.gridiron.core.model.Fence;
import net.canang.gridiron.core.model.Node;
import net.canang.gridiron.core.model.NodeRoute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */

@Component("converter")
public class Converter {

    public NodeModel convert(Node node) {
        return new NodeModel(
                node.getId(),
                node.getName(),
                node.getPhone(),
                node.getLocation().getCoordinate().x,
                node.getLocation().getCoordinate().y);
    }

    public FenceModel convert(Fence fence) {
        FenceModel fenceModel = new FenceModel(fence.getId(), fence.getCode(), fence.getDescription());
        fenceModel.setHeadCount(fence.getHeadCount());
        Polygon bound = fence.getBound();
        if (null != bound) {
            fenceModel.setCenter(convert(bound.getCentroid().getCoordinate()));
            Coordinate[] coordinates = bound.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                fenceModel.addBound(new CoordinateModel(coordinate.x, coordinate.y));
            }
        }
        return fenceModel;
    }


    public NodeRouteModel convert(NodeRoute route) {
        NodeRouteModel routeModel = new NodeRouteModel(route.getId());
        routeModel.setDateCreated(route.getDateCreated());
        LineString path = route.getPath();
        if (null != path) {
            routeModel.setCenter(convert(route.getPath().getCentroid().getCoordinate()));
            Coordinate[] coordinates = path.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                routeModel.addPath(new CoordinateModel(coordinate.x, coordinate.y));
            }
        }
        return routeModel;
    }

    public DistrictModel convert(District district) {
        DistrictModel districtModel = new DistrictModel(district.getId(), district.getDescription());
        districtModel.setHeadCount(district.getHeadCount());
        Polygon bound = district.getBound();
        if (null != bound) {
            districtModel.setCenter(convert(bound.getCentroid().getCoordinate()));
            Coordinate[] coordinates = bound.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                districtModel.addBound(new CoordinateModel(coordinate.x, coordinate.y));
            }
        }
        return districtModel;
    }

    public List<DistrictModel> convertDistricts(List<District> districts) {
        List<DistrictModel> models = new ArrayList<DistrictModel>();
        for (District district : districts) {
            if (null != district.getBound()) {
                DistrictModel model = convert(district);
                models.add(model);
            }
        }
        return models;
    }

    public List<FenceModel> convertFences(List<Fence> fences) {
        List<FenceModel> models = new ArrayList<FenceModel>();
        for (Fence fence : fences) {
            if (null != fence.getBound()) {
                FenceModel model = convert(fence);
                models.add(model);
            }
        }
        return models;
    }

    public List<NodeRouteModel> convertNodeRoutes(List<NodeRoute> NodeRoutes) {
        List<NodeRouteModel> models = new ArrayList<NodeRouteModel>();
        for (NodeRoute nodeRoute : NodeRoutes) {
            if (null != nodeRoute.getPath()) {
                NodeRouteModel model = convert(nodeRoute);
                model.setLength(nodeRoute.getPath().getLength());
                models.add(model);
            }
        }
        return models;
    }

    public List<NodeModel> convertNodes(List<Node> nodes) {
        List<NodeModel> nodeModels = new ArrayList<NodeModel>();
        for (Node node : nodes) {
            nodeModels.add(convert(node));
        }
        return nodeModels;
    }

    public CoordinateModel convert(Coordinate coordinate) {
        return new CoordinateModel(coordinate.x, coordinate.y);
    }
}

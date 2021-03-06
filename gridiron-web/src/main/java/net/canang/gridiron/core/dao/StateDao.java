package net.canang.gridiron.core.dao;

import net.canang.gridiron.core.model.State;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface StateDao {

    State findById(Long id);

    State findByCode(String code);

    List<State> find();

    void save(State state);

    void update(State state);

    void remove(State state);

}

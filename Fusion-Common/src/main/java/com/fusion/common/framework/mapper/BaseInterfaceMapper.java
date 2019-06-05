package com.fusion.common.framework.mapper;

import com.fusion.common.framework.domain.TrackableEntity;

import java.util.List;
import java.util.Map;

/**
 * 持久化接口
 *
 * @param <Entity>
 * @author daowan.hu
 */
public interface BaseInterfaceMapper<Entity extends TrackableEntity> {

    void save(Entity entity);

    Integer update(Entity entity);

    Integer deleteById(String id);

    Entity findById(String id);

    List<Entity> findAll();

    List<Entity> findByMap(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);

    List<Entity> findByPage(Map<String, Object> map);

    List<Entity> findByName(Map<String, Object> map);
}

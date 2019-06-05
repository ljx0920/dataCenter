package com.fusion.common.framework.dubbointerface.impl;

import com.fusion.common.framework.domain.TrackableEntity;
import com.fusion.common.framework.dubbointerface.BaseDubboInterface;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.common.framework.rest.Page;
import com.fusion.common.utils.IDGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author daowan.hu
 */
@Transactional
public abstract class BaseDubboInterfaceImpl<Entity extends TrackableEntity> implements BaseDubboInterface<Entity> {

    private static Logger log = LogManager.getLogger(BaseDubboInterfaceImpl.class);

    public static final String EXISTS = "exists";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    @Override
    public Page<Entity> findByPage(Page<Entity> page, Map<String, Object> map) throws DubboProviderException {
        try {
            page.setTotal(this.getBaseInterfaceMapper().getCount(map));
            page = Page.newInstance(page.getPageSize(), page.getTotal(), page.getPageNum());
            map.put("startRowNum", page.getStartRowNum());
            map.put("pageSize", page.getPageSize());
            page.setRows(this.getBaseInterfaceMapper().findByPage(map));
        } catch (Exception e) {
            log.error("BaseDubboInterfaceImpl findByPage ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return page;
    }

    @Override
    public JsonViewObject save(Entity entity) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            entity.setId(IDGenerator.get16UUID());
            entity.setCreateDate(new Date());
            this.getBaseInterfaceMapper().save(entity);
            jsonViewObject.success(entity.getId(),"数据保存成功！");
        } catch (Exception e) {
            jsonViewObject.fail("数据保存失败！");
            log.error("BaseDubboInterfaceImpl save ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return jsonViewObject;
    }

    @Override
    public JsonViewObject update(Entity entity) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            entity.setCreateDate(new Date());
            Integer updateCount = this.getBaseInterfaceMapper().update(entity);
            jsonViewObject.success(updateCount,"数据修改成功！");
        } catch (Exception e) {
            jsonViewObject.fail("数据修改失败！");
            log.error("BaseDubboInterfaceImpl update ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return jsonViewObject;
    }

    @Override
    public JsonViewObject deleteById(String id) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            Integer deleteCount = this.getBaseInterfaceMapper().deleteById(id);
            jsonViewObject.success(deleteCount,"数据删除成功！");
        } catch (Exception e) {
            jsonViewObject.fail("数据删除失败！");
            log.error("BaseDubboInterfaceImpl deleteById ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return jsonViewObject;
    }

    @Override
    public Entity findById(String id) throws DubboProviderException {
        Entity entity;
        try {
            entity = this.getBaseInterfaceMapper().findById(id);
        } catch (Exception e) {
            log.error("BaseDubboInterfaceImpl findById ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return entity;
    }

    @Override
    public List<Entity> findAll() throws DubboProviderException {
        List<Entity> list;
        try {
            list = this.getBaseInterfaceMapper().findAll();
        } catch (Exception e) {
            log.error("BaseDubboInterfaceImpl findAll ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Entity> findByMap(Map<String, Object> map) throws DubboProviderException {
        List<Entity> list;
        try {
            list = this.getBaseInterfaceMapper().findByMap(map);
        } catch (Exception e) {
            log.error("BaseDubboInterfaceImpl findByMap", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 抽象方法需要实现，得到基础服务接口
     *
     * @return
     */
    public abstract BaseInterfaceMapper<Entity> getBaseInterfaceMapper();
}

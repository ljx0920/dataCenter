package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.BaseDubboInterface;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.rest.Node;
import com.fusion.manage.entity.console.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by yaju.jiao on 2019/3/4.
 */
public interface MenuProvider extends BaseDubboInterface<Menu> {
    /**
     * 新建菜单时获取可能的上级菜单列表
     *
     * @param id
     * @return
     * @throws DubboProviderException
     */
    List<Menu> getLowerMenu(String id) throws DubboProviderException;

    /**
     * 获取树形菜单
     *
     * @return
     * @throws DubboProviderException
     */
    List<Node> getTreeMenu()throws DubboProviderException;

    /**
     * 批量更新菜单
     *
     * @param mapList
     * @throws DubboProviderException
     */
    void updateBatch(List<Map<String, Object>> mapList) throws DubboProviderException;
}

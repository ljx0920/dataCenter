package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.impl.BaseDubboInterfaceImpl;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.common.framework.rest.Node;
import com.fusion.common.framework.rest.Page;
import com.fusion.manage.entity.console.Menu;
import com.fusion.manage.mapper.console.MenuMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

import static com.fusion.common.framework.rest.Node.flatTreeNode;
import static com.fusion.common.framework.rest.Node.transformNodes;

/**
 * Created by yaju.jiao on 2019/3/4.
 */
public class MenuProviderImpl extends BaseDubboInterfaceImpl<Menu> implements MenuProvider{

    private static Logger log = LogManager.getLogger(MenuProviderImpl.class);

    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;

    @Override
    public BaseInterfaceMapper<Menu> getBaseInterfaceMapper() {
        return menuMapper;
    }

    @Override
    public JsonViewObject save(Menu entity) throws DubboProviderException {
        if(entity.getParentId() == null){
            entity.setParentId(String.valueOf(0));
        }
        setLevel(entity);
        return super.save(entity);
    }

    /**
     * 填充菜单级别
     *
     * @param menu
     */
    private void setLevel(Menu menu) {
        Map<String, Object> param = new HashMap<>();
        param.put("parentId", menu.getParentId());
        List<Menu> menuList = menuMapper.findByMap(param);
        int maxLevel = menuList.isEmpty() ? 0 : menuList.stream().mapToInt(Menu::getLevel).max().getAsInt();
        menu.setLevel(maxLevel + 1);
    }

    @Override
    public List<Menu> getLowerMenu(String id) throws DubboProviderException {
        List<Menu> allMenuList = menuMapper.findAll();
        List<Node> allMenuNodeList = new LinkedList<>();
        allMenuList.forEach(menu ->{
            allMenuNodeList.add(new Node(menu.getId(), menu.getName(), menu, menu.getParentId()));
        });
        List<Node> allMenuTree = transformNodes(allMenuNodeList);
        Node nowMenuTree = null;
        for (Node node : allMenuTree) {
            if (node.getId().equals(id)) {
                nowMenuTree = node;
                break;
            }
        }
        List<Menu> menuList = flatTreeNode(nowMenuTree);
        return menuList;
    }

    @Override
    public List<Node> getTreeMenu() throws DubboProviderException {
        List<Menu> allMenuList = menuMapper.findAll();
        List<Node> allMenuNodeList = new LinkedList<>();
        allMenuList.forEach(menu -> {
            allMenuNodeList.add(new Node(menu.getId(), menu.getName(), menu, menu.getParentId()));
        });
        List<Node> allMenuTree = transformNodes(allMenuNodeList);
        return allMenuTree;
    }

    @Override
    public Page<Menu> findByPage(Page<Menu> page, Map<String, Object> map) throws DubboProviderException {
        String keyword = (String) map.remove("keyword");
        String id = (String) map.remove("id");
        try {
            List<Menu> menuList = getLowerMenu(id).stream().filter(x -> !Objects.equals(x.getId(), id)).collect(Collectors.toList());
            if (keyword != null || "".equals(keyword)) {
                menuList.stream().filter( x -> x.getName().contains(keyword)).collect(Collectors.toList());
            }
            page.setTotal(menuList.size());
            page = Page.newInstance(page.getPageSize(), page.getTotal(), page.getPageNum());
            List<Menu> pageList = menuList.subList(page.getStartRowNum(),
                    page.getStartRowNum() + page.getPageSize() > page.getTotal() ? page.getTotal() : page.getStartRowNum() + page.getPageSize());
            pageList.forEach(menu -> {
                if (!Objects.equals(menu.getParentId(), "0")) {
                    menu.setParentName(menuMapper.findById(menu.getParentId()).getName());
                }
            });
            page.setRows(pageList);
        } catch (DubboProviderException e) {
            log.error("MenuProviderImpl findByPage error", e);
            throw new DubboProviderException("MenuProviderImpl findByPage error");
        }
        return page;
    }

    @Override
    public void updateBatch(List<Map<String, Object>> mapList) throws DubboProviderException {
        Menu menu = Menu.newInstance();
        try {
            mapList.forEach(map -> {
                menu.setId((String) map.get("id"));
                menu.setLevel((Integer) map.get("level"));
                menuMapper.update(menu);
            });
        } catch (Exception e) {
            throw new DubboProviderException("批量更新失败");
        }
    }

    @Override
    public JsonViewObject deleteById(String id) throws DubboProviderException {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", id);
        List<Menu> systemParamList = menuMapper.findByMap(map);
        if (!systemParamList.isEmpty()) {
            throw new DubboProviderException("被删除菜单下仍有子菜单");
        }
        return super.deleteById(id);
    }
}

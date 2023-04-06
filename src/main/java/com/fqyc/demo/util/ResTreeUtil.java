package com.fqyc.demo.util;//package com.fqyc.quality.util;
//
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.*;
//
///**
// * @author yanggl-2478
// * @version 1.0
// * @date 2019/6/21 11:05
// */
//public class ResTreeUtil {
//
//    private ResTreeUtil() {
//
//    }
//
//    /**
//     * list转树形结构，用于登录后，用户左侧菜单树形结构
//     *
//     * @param dtoList list
//     * @return java.util.List<cn.hydee.middle.baseinfo.dto.resp.ResourceDTO>
//     * @author yanggl-2478
//     * @date 17:32 2019/7/29
//     **/
//    public static List<ResourceQueryRspDTO> generateListToTree(List<ResourceQueryRspDTO> dtoList) {
//        if (dtoList == null || dtoList.isEmpty()) {
//            return new ArrayList<>();
//        }
//        Map<String, ResourceQueryRspDTO> map = new HashMap<>(dtoList.size());
//        dtoList.forEach(resourceDTO -> map.put(resourceDTO.getId(), resourceDTO));
//        List<ResourceQueryRspDTO> result = new ArrayList<>();
//        dtoList.forEach(resourceDTO -> {
//            if (StringUtils.isEmpty(resourceDTO.getReParent())) {
//                result.add(resourceDTO);
//            } else {
//                ResourceQueryRspDTO parent = map.get(resourceDTO.getReParent());
//                if (parent != null) {
//                    if (parent.getChildren() == null) {
//                        parent.setChildren(new ArrayList<>());
//                    }
//                    parent.getChildren().add(resourceDTO);
//                }
//            }
//        });
//        return result;
//    }
//
//    /**
//     * list转树形结构,用于角色设置
//     *
//     * @param dtoList list
//     * @return java.util.List<cn.hydee.middle.baseinfo.dto.resp.ResourceDTO>
//     * @author yanggl-2478
//     * @date 17:32 2019/7/29
//     **/
//    public static Collection<ResourceTreeDTO> generatePageResToTree(List<ResourceQueryRspDTO> dtoList) {
//        Map<String, ResourceTreeDTO> sysMap = new HashMap<>();
//        Map<String, ResourceTreeDTO.ModulesBean> moduleMap = new HashMap<>();
//        Map<String, ResourceTreeDTO.ModulesBean.MenuBean> menuMap = new HashMap<>();
//        dtoList.forEach(resourceDTO -> {
//            // 判断系统层是否存在，不存在则创建
//            String sysKey = resourceDTO.getReSystem();
//            ResourceTreeDTO sys = sysMap.get(sysKey);
//            if (sys == null) {
//                sys = new ResourceTreeDTO();
//                sys.setReSystem(resourceDTO.getReSystem());
//                sysMap.put(resourceDTO.getReSystem(), sys);
//            }
//            // 判断模块是否存在，不存在则创建
//            String moduleKey = sysKey + resourceDTO.getReModule();
//            ResourceTreeDTO.ModulesBean modulesBean = moduleMap.get(moduleKey);
//            if (modulesBean == null) {
//                modulesBean = new ResourceTreeDTO.ModulesBean();
//                modulesBean.setReModule(resourceDTO.getReModule());
//                moduleMap.put(moduleKey, modulesBean);
//                sys.addModule(modulesBean);
//            }
//            // 判断菜单是否存在，不存在则创建
//            String menuKey = moduleKey + resourceDTO.getReMenu();
//            ResourceTreeDTO.ModulesBean.MenuBean menuBean = menuMap.get(menuKey);
//            if (menuBean == null && !StringUtils.isEmpty(resourceDTO.getReMenu())) {
//                menuBean = new ResourceTreeDTO.ModulesBean.MenuBean();
//                menuBean.setReMenu(resourceDTO.getReMenu());
//                if (StringUtils.isEmpty(resourceDTO.getReButton())) {
//                    menuBean.setId(resourceDTO.getId());
//                }
//                menuMap.put(menuKey, menuBean);
//                modulesBean.addMenu(menuBean);
//            }
//            if (menuBean != null &&
//                    !StringUtils.isEmpty(resourceDTO.getReMenu())
//                    && StringUtils.isEmpty(resourceDTO.getReButton())) {
//                menuBean.setId(resourceDTO.getId());
//            }
//            if (!StringUtils.isEmpty(resourceDTO.getReMenu()) &&
//                    !StringUtils.isEmpty(resourceDTO.getReButton())) {
//                // 增加资源到模块
//                ResourceTreeDTO.ModulesBean.MenuBean.ResListBean resListBean =
//                        new ResourceTreeDTO.ModulesBean.MenuBean.ResListBean();
//                resListBean.setId(resourceDTO.getId());
//                resListBean.setReButton(resourceDTO.getReButton());
//                resListBean.setReMenu(resourceDTO.getReMenu());
//                resListBean.setReMethod(resourceDTO.getReMethod());
//                resListBean.setRePath(resourceDTO.getRePath());
//                resListBean.setReType(resourceDTO.getReType());
//                menuBean.addResToList(resListBean);
//            }
//        });
//        return sysMap.values();
//    }
//
//    /**
//     * 根据reModule,reMenu,reButton三个属性构建资源树
//     *
//     * @param dtoList dtoList
//     * @return 资源树
//     */
//    public static Collection<ResourceTreeDTO.ModulesBean> generateNewPageResToTree(List<ResourceQueryRspDTO> dtoList) {
//        // 创建moduleMap用于保存module,menuMap用户保存menu
//        Map<String, ResourceTreeDTO.ModulesBean> moduleMap = new LinkedHashMap<>();
//        Map<String, ResourceTreeDTO.ModulesBean.MenuBean> menuMap = new HashMap<>();
//
//        // 依次遍历每个资源 将其添加到对应的map
//        // 每个资源都有module,menu,button属性
//        // 资源层级 module > menu1 > menu2 > button
//        // menu层级下可以为menu 也可以为button button一定是最后一级
//        dtoList.forEach(resourceDTO -> {
//            // 判断模块是否存在，不存在则创建
//            String moduleKey = resourceDTO.getReModule();
//            ResourceTreeDTO.ModulesBean modulesBean = moduleMap.get(moduleKey);
//            if (modulesBean == null) {
//                modulesBean = new ResourceTreeDTO.ModulesBean();
//                modulesBean.setReModule(resourceDTO.getReModule());
//                moduleMap.put(moduleKey, modulesBean);
//            }
//            // 判断菜单是否存在，不存在则创建
//            String menuKey = moduleKey + resourceDTO.getReMenu();
//            ResourceTreeDTO.ModulesBean.MenuBean menuBean = menuMap.get(menuKey);
//            if (menuBean == null && !StringUtils.isEmpty(resourceDTO.getReMenu())) {
//                // menu不存在则创建
//                menuBean = new ResourceTreeDTO.ModulesBean.MenuBean();
//                menuBean.setReMenu(resourceDTO.getReMenu());
//                if (StringUtils.isEmpty(resourceDTO.getReButton())) {
//                    menuBean.setId(resourceDTO.getId());
//                    menuBean.setParentId(resourceDTO.getReParent());
//                }
//                // 将此菜单添加到menuMap
//                menuMap.put(menuKey, menuBean);
//                // 将此菜单添加到当前module下
//                modulesBean.addMenu(menuBean);
//            }
//            if (menuBean != null &&
//                    !StringUtils.isEmpty(resourceDTO.getReMenu())
//                    && StringUtils.isEmpty(resourceDTO.getReButton())) {
//                // menu存在 且下一级button为空 则保存当前菜单的Id和父Id
//                menuBean.setId(resourceDTO.getId());
//                menuBean.setParentId(resourceDTO.getReParent());
//            }
//            if (!StringUtils.isEmpty(resourceDTO.getReMenu()) &&
//                    !StringUtils.isEmpty(resourceDTO.getReButton())) {
//                // 增加资源到模块
//                // 如果下一级button不为空 则将下一级button添加到当前菜单的下一级列表 换句话说 就是将button与menu绑定
//                ResourceTreeDTO.ModulesBean.MenuBean.ResListBean resListBean =
//                        new ResourceTreeDTO.ModulesBean.MenuBean.ResListBean();
//                resListBean.setId(resourceDTO.getId());
//                resListBean.setReButton(resourceDTO.getReButton());
//                resListBean.setReMenu(resourceDTO.getReMenu());
//                resListBean.setReMethod(resourceDTO.getReMethod());
//                resListBean.setRePath(resourceDTO.getRePath());
//                resListBean.setReType(resourceDTO.getReType());
//                menuBean.addResToList(resListBean);
//            }
//        });
//
//        Collection<ResourceTreeDTO.ModulesBean> collection = moduleMap.values();
//        collection.forEach(modulesBean -> {
//            // 依次遍历所有module
//            Set<String> idSet = new HashSet<>();
//            Set<String> parentSet = new HashSet<>();
//
//            //获取当前module的所有menu菜单
//            List<ResourceTreeDTO.ModulesBean.MenuBean> menuBeans = new ArrayList<>();
//            if (modulesBean != null) {
//                menuBeans = modulesBean.getMenus();
//            }
//            if (!CollectionUtils.isEmpty(menuBeans)) {
//                // 如果当前module的菜单不为空 则遍历当前所有菜单
//                menuBeans.forEach(menuBean -> {
//                    if (menuBean != null && !StringUtils.isEmpty(menuBean.getId())) {
//                        // 第一次遍历将menu的所有id保存到set
//                        idSet.add(menuBean.getId());
//                    }
//                });
//                List<ResourceTreeDTO.ModulesBean.MenuBean> subMenuTotalList = new ArrayList<>();
//                for (int i = 0; i < menuBeans.size(); i++) {
//                    // 再次遍历查找当前遍历的menu的父menu
//                    if (!StringUtils.isEmpty(menuBeans.get(i).getParentId()) && idSet.contains(menuBeans.get(i).getParentId())) {
//                        // 将父Id存到父Set
//                        parentSet.add(menuBeans.get(i).getParentId());
//                        // 并将当前menu添加到list保存
//                        subMenuTotalList.add(menuBeans.get(i));
//                    }
//
//                }
//                if (!CollectionUtils.isEmpty(subMenuTotalList)) {
//                    for (ResourceTreeDTO.ModulesBean.MenuBean menuBean : subMenuTotalList) {
//                        // 将子menu从二级菜单中移除, 子menu一定是三级menu
//                        menuBeans.remove(menuBean);
//                    }
//                    for (ResourceTreeDTO.ModulesBean.MenuBean menuBean : menuBeans) {
//                        List<ResourceTreeDTO.ModulesBean.MenuBean> subMenuList = new ArrayList<>();
//                        for (ResourceTreeDTO.ModulesBean.MenuBean subMenu : subMenuTotalList) {
//                            if (menuBean.getId().equals(subMenu.getParentId())) {
//                                // 将三级menu绑定到二级menu下
//                                subMenuList.add(subMenu);
//                                menuBean.setResMenuList(subMenuList);
//                            }
//                        }
//                    }
//                }
//            }
//        });
//        return moduleMap.values();
//    }
//}

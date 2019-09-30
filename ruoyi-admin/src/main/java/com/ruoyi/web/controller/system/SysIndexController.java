package com.ruoyi.web.controller.system;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.goal.common.GoalConstants;
import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.domain.GoalFilterCondition;
import com.ruoyi.goal.domain.GoalStatic;
import com.ruoyi.goal.service.IGmGoalService;
import com.ruoyi.goal.service.MonthGoalService;
import com.ruoyi.system.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private DictService dictService;
    @Autowired
    private MonthGoalService monthGoalService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) throws ParseException {
        List<SysDictData> goalTypeList = dictService.getType("goal_type");
        GoalFilterCondition goalFilterCondition = monthGoalService.buildFilterGmGoal(GoalConstants.TAG_CURRENT,null, GoalConstants.TAG_ALL,null);
        Map<String, List<GmGoal>> goalMap = monthGoalService.getGoalTypeGoalListMap(goalFilterCondition);
        mmap.put("goalTypeList", goalTypeList);
        mmap.put("goalMap", goalMap);
        //查询统计数据
        GoalStatic goalStat = monthGoalService.getGoalStat();
        mmap.put("goalStatic", goalStat);

        //想法
        mmap.put("ideaList",monthGoalService.getIdeas(GoalConstants.TAG_CURRENT,null,null));
        //总结
        mmap.put("summaryList",monthGoalService.getSummaryList(GoalConstants.TAG_CURRENT,null,null));
        //热点
        mmap.put("hotspotList",monthGoalService.getHotspotList(GoalConstants.TAG_CURRENT,null,null));

        //当前显示的月份
        mmap.put("currentShowDate",DateUtils.parseDateToStr(DateUtils.YYYY_MM,DateUtils.getNowDate()));
        return "main";
    }
}

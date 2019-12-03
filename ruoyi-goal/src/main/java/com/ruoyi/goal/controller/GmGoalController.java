package com.ruoyi.goal.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.goal.common.GoalConstants;
import com.ruoyi.goal.domain.GmHotspot;
import com.ruoyi.goal.domain.GmIdea;
import com.ruoyi.goal.domain.GmSummary;
import com.ruoyi.goal.domain.GoalFilterCondition;
import com.ruoyi.goal.domain.GoalStatic;
import com.ruoyi.goal.service.IGmHotspotService;
import com.ruoyi.goal.service.IGmIdeaService;
import com.ruoyi.goal.service.IGmSummaryService;
import com.ruoyi.goal.service.MonthGoalService;
import com.ruoyi.system.domain.SysDictData;
import org.apache.commons.collections4.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.service.IGmGoalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 目标Controller
 *
 * @author mason
 * @date 2019-09-18
 */
@Controller
@RequestMapping("/goal/month")
public class GmGoalController extends BaseController {
    private String prefix = "goal/month";

    @Autowired
    private IGmGoalService gmGoalService;

    @Autowired
    private DictService dictService;

    @Autowired
    private MonthGoalService monthGoalService;

    @Autowired
    private IGmIdeaService gmIdeaService;

    @Autowired
    private IGmSummaryService gmSummaryService;

    @Autowired
    private IGmHotspotService gmHotspotService;

    @RequiresPermissions("goal:month:view")
    @GetMapping()
    public String month(ModelMap mmap) throws ParseException {
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
        return prefix + "/month";
    }

    @RequiresPermissions("goal:month:list")
    @GetMapping("/goaltab")
    public String monthGoalTab(String monthTag, String timeRange, String doneTag,String currentShowDate, ModelMap mmap) throws ParseException {
        List<SysDictData> goalTypeList = dictService.getType("goal_type");
        GoalFilterCondition goalFilterCondition = monthGoalService.buildFilterGmGoal(monthTag, timeRange, doneTag,currentShowDate);
        Map<String, List<GmGoal>> goalMap = monthGoalService.getGoalTypeGoalListMap(goalFilterCondition);
        mmap.put("goalTypeList", goalTypeList);
        mmap.put("goalMap", goalMap);
        //当前显示的月份
        Date dateByMonthTag = monthGoalService.getDateByMonthTag(monthTag, currentShowDate);
        mmap.put("currentShowDate",DateUtils.parseDateToStr(DateUtils.YYYY_MM,dateByMonthTag));
        return prefix + "/month::goal-tab-fragment";
    }

    @GetMapping("/ideatab")
    public String monthIdeaTab(String monthTag, String timeRange,String currentShowDate, ModelMap mmap) throws ParseException {
        //想法
        mmap.put("ideaList",monthGoalService.getIdeas(monthTag,timeRange,currentShowDate));
        //当前显示的月份
        Date dateByMonthTag = monthGoalService.getDateByMonthTag(monthTag, currentShowDate);
        mmap.put("currentShowDate",DateUtils.parseDateToStr(DateUtils.YYYY_MM,dateByMonthTag));
        return prefix + "/month::idea-tab-fragment";
    }

    @GetMapping("/summarytab")
    public String monthSummaryTab(String monthTag, String timeRange, String currentShowDate, ModelMap mmap) throws ParseException {
        //想法
        mmap.put("summaryList",monthGoalService.getSummaryList(monthTag,timeRange,currentShowDate));
        //当前显示的月份
        Date dateByMonthTag = monthGoalService.getDateByMonthTag(monthTag, currentShowDate);
        mmap.put("currentShowDate",DateUtils.parseDateToStr(DateUtils.YYYY_MM,dateByMonthTag));
        return prefix + "/month::summary-tab-fragment";
    }

    @GetMapping("/hotspottab")
    public String monthHotspotTab(String monthTag, String timeRange,String currentShowDate, ModelMap mmap) throws ParseException {
        //热点
        mmap.put("hotspotList",monthGoalService.getHotspotList(monthTag,timeRange,currentShowDate));
        //当前显示的月份
        Date dateByMonthTag = monthGoalService.getDateByMonthTag(monthTag, currentShowDate);
        mmap.put("currentShowDate",DateUtils.parseDateToStr(DateUtils.YYYY_MM,dateByMonthTag));
        return prefix + "/month::hotspot-tab-fragment";
    }

    /**
     * 查询目标列表
     */
    @RequiresPermissions("goal:month:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GmGoal gmGoal) {
        startPage();
        List<GmGoal> list = gmGoalService.selectGmGoalList(gmGoal);
        return getDataTable(list);
    }

    /**
     * 导出目标列表
     */
    @RequiresPermissions("goal:month:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GmGoal gmGoal) {
        List<GmGoal> list = gmGoalService.selectGmGoalList(gmGoal);
        ExcelUtil<GmGoal> util = new ExcelUtil<GmGoal>(GmGoal.class);
        return util.exportExcel(list, "month");
    }

    /**
     * 新增目标
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存目标
     */
    @RequiresPermissions("goal:month:add")
    @Log(title = "目标", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GmGoal gmGoal) {
        gmGoal.setSysCreateBy(ShiroUtils.getLoginName());
        gmGoal.setSysUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gmGoalService.insertGmGoal(gmGoal));
    }

    /**
     * 修改目标
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        GmGoal gmGoal = gmGoalService.selectGmGoalById(id);
        mmap.put("gmGoal", gmGoal);
        return prefix + "/edit";
    }

    /**
     * 修改保存目标
     */
    @RequiresPermissions("goal:month:edit")
    @Log(title = "目标", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GmGoal gmGoal) {
        gmGoal.setSysUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gmGoalService.updateGmGoal(gmGoal));
    }

    /**
     * 删除目标
     */
    @RequiresPermissions("goal:month:remove")
    @Log(title = "目标", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(gmGoalService.deleteGmGoalByIds(ids));
    }


    /**
     * 复制月数据
     * @return
     */
    @Log(title = "目标", businessType = BusinessType.INSERT)
    @PostMapping("/copy-last-month")
    @ResponseBody
    public AjaxResult copyLastMonth(String copyType,String currentShowDate) throws ParseException {
        Date currentDate = DateUtils.parseDate(currentShowDate, DateUtils.YYYY_MM);
        switch (copyType){
            case GoalConstants.TABTYPE_GOAL:
                monthGoalService.copyLastMonthGoal(currentShowDate, currentDate);
                break;
            case GoalConstants.TABTYPE_IDEA:
                monthGoalService.copyLastMonthIdea(currentDate);
                Date lastMonth;
                break;
            case GoalConstants.TABTYPE_SUMMARY:
                monthGoalService.copyLastMonthSummary(currentDate);
                break;
            case GoalConstants.TABTYPE_HOTSPOT:
                monthGoalService.copylastMonthHotspot(currentDate);
                break;
        }

        return success();
    }



}

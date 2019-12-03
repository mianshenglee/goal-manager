package com.ruoyi.goal.service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.goal.common.GoalConstants;
import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.domain.GmHotspot;
import com.ruoyi.goal.domain.GmIdea;
import com.ruoyi.goal.domain.GmSummary;
import com.ruoyi.goal.domain.GoalFilterCondition;
import com.ruoyi.goal.domain.GoalStatic;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 月目标服务类
 *
 * @author mason
 * @since 2019/9/27
 */
@Service
public class MonthGoalService {

    @Autowired
    private IGmIdeaService gmIdeaService;

    @Autowired
    private IGmGoalService gmGoalService;

    @Autowired
    private IGmHotspotService gmHotspotService;

    @Autowired
    private IGmSummaryService gmSummaryService;

    @Autowired
    private IGmHotspotService hotspotService;

    public List<GmHotspot> getHotspotList(String monthTag,String timeRange,String currentShowDate) throws ParseException {
        //想法
        GmHotspot gmHotspot = new GmHotspot();
        gmHotspot.setSysCreateBy(ShiroUtils.getLoginName());
        if(StringUtils.isNotEmpty(monthTag)){
            gmHotspot.setHotTime(getDateByMonthTag(monthTag, currentShowDate));
        }
        //时间范围
        if (StringUtils.isNotEmpty(timeRange)) {
            gmHotspot.setParams(getTimeRangeParam(timeRange));
        }
        List<GmHotspot> gmHotspots = hotspotService.selectGmHotspotList(gmHotspot);

        return gmHotspots;
    }


    public List<GmSummary> getSummaryList(String monthTag,String timeRange,String currentShowDate) throws ParseException {
        //想法
        GmSummary gmSummary = new GmSummary();
        gmSummary.setSysCreateBy(ShiroUtils.getLoginName());

        if(StringUtils.isNotEmpty(monthTag)){
            gmSummary.setSummaryTime(getDateByMonthTag(monthTag, currentShowDate));
        }
        //时间范围
        if (StringUtils.isNotEmpty(timeRange)) {
            gmSummary.setParams(getTimeRangeParam(timeRange));
        }
        return gmSummaryService.selectGmSummaryList(gmSummary);
    }

    private Map<String, Object> getTimeRangeParam(String timeRange) throws ParseException {
        String[] monthRange = timeRange.split(GoalConstants.TAG_TIMERANGE);
        String monthStartStr = monthRange[0];
        String monthEndStr = monthRange[1];

        Date monthStartDate = DateUtils.parseDate(monthStartStr, DateUtils.YYYY_MM);
        Date monthEndDate = DateUtils.parseDate(monthEndStr, DateUtils.YYYY_MM);
        //设置月最后一天
        Calendar instance = Calendar.getInstance();
        instance.setTime(monthEndDate);
        DateUtils.setDays(monthEndDate, instance.getActualMaximum(Calendar.DAY_OF_MONTH));

        Map<String,Object> params = new HashedMap();
        params.put("startTime",monthStartDate);
        params.put("endTime",monthEndDate);
        return params;
    }

    public List<GmIdea> getIdeas(String monthTag,String timeRange,String currentShowDate) throws ParseException {
        //想法
        GmIdea gmIdea = new GmIdea();
        gmIdea.setSysCreateBy(ShiroUtils.getLoginName());
        if(StringUtils.isNotEmpty(monthTag)){
            gmIdea.setIdeaTime(getDateByMonthTag(monthTag, currentShowDate));
        }
        //时间范围
        if (StringUtils.isNotEmpty(timeRange)) {
            gmIdea.setParams(getTimeRangeParam(timeRange));
        }
        List<GmIdea> gmIdeas = gmIdeaService.selectGmIdeaList(gmIdea);

        return gmIdeas;
    }

    public Date getDateByMonthTag(String monthTag, String currentShowDate) throws ParseException {
        Date monthDate = DateUtils.getNowDate();
        if (StringUtils.isNotEmpty(currentShowDate)) {
            monthDate = DateUtils.parseDate(currentShowDate, DateUtils.YYYY_MM);
        }
        //按月过滤
        if (StringUtils.isNotEmpty(monthTag)) {
            //月份标识
            switch (monthTag) {
                case GoalConstants.TAG_PREV:
                    monthDate = DateUtils.addMonths(monthDate, -1);
                    break;
                case GoalConstants.TAG_NEXT:
                    monthDate = DateUtils.addMonths(monthDate, 1);
                    break;
                case GoalConstants.TAG_CURRENT:
                    monthDate = DateUtils.getNowDate();
                    break;
                case GoalConstants.TAG_CURRENT_SHOW:
                    if (StringUtils.isNotEmpty(currentShowDate)) {
                        monthDate = DateUtils.parseDate(currentShowDate, DateUtils.YYYY_MM);
                    }
            }
        }
        return monthDate;
    }

    /**
     * 获取统计数据
     *
     * @return
     */
    public GoalStatic getGoalStat() {
        //目标统计数据
        GmGoal filter = new GmGoal();
        filter.setSysCreateBy(ShiroUtils.getLoginName());
        filter.setGoalPhase("month");
        filter.setGoalTime(DateUtils.getNowDate());
        List<GmGoal> goalList = gmGoalService.selectGmGoalList(filter);

        GoalStatic.GoalStaticBuilder builder = GoalStatic.builder();
        int doneCount = 0;
        int deleteCount = 0;
        int notStartCount = 0;
        for (GmGoal goal : goalList) {
            if (goal.getProcess() == 100L) {
                doneCount++;
            } else if (goal.getProcess() == 0L) {
                notStartCount++;
            }
            if (goal.getIsDeleted().equals("1")) {
                deleteCount++;
            }
        }
        //想法数
        GmIdea gmIdea = new GmIdea();
        gmIdea.setSysCreateBy(ShiroUtils.getLoginName());
        gmIdea.setIdeaTime(DateUtils.getNowDate());
        int ideaCount = gmIdeaService.selectGmIdeaList(gmIdea).size();
        //热点数
        GmHotspot gmHotspot = new GmHotspot();
        gmHotspot.setSysCreateBy(ShiroUtils.getLoginName());
        gmHotspot.setHotTime(DateUtils.getNowDate());
        int hotspotCount = gmHotspotService.selectGmHotspotList(gmHotspot).size();

        return builder.totalGoal(goalList.size())
                .doneGoal(doneCount)
                .deletedGoal(deleteCount)
                .notStartGoal(notStartCount)
                .ideaCount(ideaCount)
                .hotspotCount(hotspotCount)
                .build();
    }

    /**
     * 根据条件获取目标类型和目标列表Map
     *
     * @param goalFilterCondition 条件
     * @return
     */
    public Map<String, List<GmGoal>> getGoalTypeGoalListMap(GoalFilterCondition goalFilterCondition) {
        List<GmGoal> list = gmGoalService.selectGmGoalListByCondition(goalFilterCondition);
        Map<String, List<GmGoal>> goalMap = new HashMap<>(20);
        for (GmGoal goal : list) {
            String goalType = goal.getGoalType();
            if (goalMap.containsKey(goalType)) {
                List<GmGoal> gmGoalList = goalMap.get(goalType);
                gmGoalList.add(goal);
            } else {
                List<GmGoal> goalList = new ArrayList<>();
                goalList.add(goal);
                goalMap.put(goalType, goalList);
            }
        }

        return goalMap;
    }


    /**
     * 构建查询参数
     *
     * @param monthTag
     * @param timeRange
     * @param doneTag all,done,notDone
     * @return
     */
    public GoalFilterCondition buildFilterGmGoal(String monthTag, String timeRange, String doneTag, String currentShowDate) throws ParseException {
        GoalFilterCondition goalFilter = new GoalFilterCondition();
        goalFilter.setSysCreateBy(ShiroUtils.getLoginName());
        goalFilter.setGoalPhase("month");
        goalFilter.setIsDeleted("0");
        //按月过滤
        if (StringUtils.isNotEmpty(monthTag)) {
            goalFilter.setGoalTime(getDateByMonthTag(monthTag, currentShowDate));
        }
        //时间范围
        if (StringUtils.isNotEmpty(timeRange)) {
            String[] monthRange = timeRange.split(GoalConstants.TAG_TIMERANGE);
            String monthStartStr = monthRange[0];
            String monthEndStr = monthRange[1];

            Date monthStartDate = DateUtils.parseDate(monthStartStr, DateUtils.YYYY_MM);
            Date monthEndDate = DateUtils.parseDate(monthEndStr, DateUtils.YYYY_MM);
            //设置月最后一天
            Calendar instance = Calendar.getInstance();
            instance.setTime(monthEndDate);
            DateUtils.setDays(monthEndDate, instance.getActualMaximum(Calendar.DAY_OF_MONTH));

            goalFilter.setGoalStartTime(monthStartDate);
            goalFilter.setGoalEndTime(monthEndDate);
        }
        //完成标识
        if (StringUtils.isNotEmpty(doneTag)) {
            if (!doneTag.equals(GoalConstants.TAG_ALL) && !doneTag.equals(GoalConstants.TAG_DONE)
                    && !doneTag.equals(GoalConstants.TAG_NOTDONE)) {
                throw new RuntimeException("完成标识错误");
            }
            goalFilter.setDoneTag(doneTag);
        }

        return goalFilter;
    }

    public void copylastMonthHotspot(Date currentDate) {
        Date lastMonth;GmHotspot gmHotspot = new GmHotspot();
        lastMonth = DateUtils.addMonths(currentDate, -1);
        gmHotspot.setSysCreateBy(ShiroUtils.getLoginName());
        gmHotspot.setHotTime(lastMonth);
        List<GmHotspot> gmHotspots = gmHotspotService.selectGmHotspotList(gmHotspot);
        for (GmHotspot hotspot : gmHotspots) {
            hotspot.setId(null);
            hotspot.setRecordVersion(1L);
            hotspot.setHotTime(currentDate);
            hotspot.setSysCreateTime(DateUtils.getNowDate());
            hotspot.setSysUpdateTime(DateUtils.getNowDate());
            gmHotspotService.insertGmHotspot(hotspot);
        }
    }

    public void copyLastMonthSummary(Date currentDate) {
        Date lastMonth;GmSummary gmSummary = new GmSummary();
        lastMonth = DateUtils.addMonths(currentDate, -1);
        gmSummary.setSysCreateBy(ShiroUtils.getLoginName());
        gmSummary.setSummaryTime(lastMonth);
        List<GmSummary> gmSummaries = gmSummaryService.selectGmSummaryList(gmSummary);
        for (GmSummary summary : gmSummaries) {
            summary.setId(null);
            summary.setRecordVersion(1L);
            summary.setSummaryTime(currentDate);
            summary.setSysCreateTime(DateUtils.getNowDate());
            summary.setSysUpdateTime(DateUtils.getNowDate());
            gmSummaryService.insertGmSummary(summary);
        }
    }

    public void copyLastMonthIdea(Date currentDate) {
        GmIdea gmIdea = new GmIdea();
        Date lastMonth = DateUtils.addMonths(currentDate, -1);
        gmIdea.setSysCreateBy(ShiroUtils.getLoginName());
        gmIdea.setIdeaTime(lastMonth);
        List<GmIdea> gmIdeas = gmIdeaService.selectGmIdeaList(gmIdea);
        for (GmIdea idea : gmIdeas) {
            idea.setId(null);
            idea.setRecordVersion(1L);
            idea.setIdeaTime(currentDate);
            idea.setSysCreateTime(DateUtils.getNowDate());
            idea.setSysUpdateTime(DateUtils.getNowDate());
            gmIdeaService.insertGmIdea(idea);
        }
    }

    public void copyLastMonthGoal(String currentShowDate, Date currentDate) throws ParseException {
        GoalFilterCondition goalFilterCondition = buildFilterGmGoal(GoalConstants.TAG_PREV, null, GoalConstants.TAG_NOTDONE,currentShowDate);
        List<GmGoal> gmGoals = gmGoalService.selectGmGoalListByCondition(goalFilterCondition);
        for (GmGoal goal : gmGoals) {
            goal.setId(null);
            goal.setRecordVersion(1L);
            goal.setGoalTime(currentDate);
            goal.setSysCreateTime(DateUtils.getNowDate());
            goal.setSysUpdateTime(DateUtils.getNowDate());
            gmGoalService.insertGmGoal(goal);
        }
    }
}

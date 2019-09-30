package com.ruoyi.goal.service;

import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.domain.GoalFilterCondition;
import com.ruoyi.goal.domain.GoalStatic;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 目标Service接口
 * 
 * @author mason
 * @date 2019-09-18
 */
public interface IGmGoalService 
{
    /**
     * 查询目标
     * 
     * @param id 目标ID
     * @return 目标
     */
    public GmGoal selectGmGoalById(Long id);

    /**
     * 查询目标列表
     * 
     * @param gmGoal 目标
     * @return 目标集合
     */
    public List<GmGoal> selectGmGoalList(GmGoal gmGoal);

    /**
     * 根据条件查询目标列表
     * @param goalFilterCondition
     * @return
     */
    public List<GmGoal> selectGmGoalListByCondition(GoalFilterCondition goalFilterCondition);

    /**
     * 新增目标
     * 
     * @param gmGoal 目标
     * @return 结果
     */
    public int insertGmGoal(GmGoal gmGoal);

    /**
     * 修改目标
     * 
     * @param gmGoal 目标
     * @return 结果
     */
    public int updateGmGoal(GmGoal gmGoal);

    /**
     * 批量删除目标
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGmGoalByIds(String ids);

    /**
     * 删除目标信息
     * 
     * @param id 目标ID
     * @return 结果
     */
    public int deleteGmGoalById(Long id);

}

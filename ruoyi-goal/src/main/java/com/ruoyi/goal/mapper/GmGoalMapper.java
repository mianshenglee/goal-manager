package com.ruoyi.goal.mapper;

import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.domain.GoalFilterCondition;

import java.util.List;

/**
 * 目标Mapper接口
 * 
 * @author mason
 * @date 2019-09-18
 */
public interface GmGoalMapper 
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
     * 根据条件查询目标
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
     * 删除目标
     * 
     * @param id 目标ID
     * @return 结果
     */
    public int deleteGmGoalById(Long id);

    /**
     * 批量删除目标
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGmGoalByIds(String[] ids);


}

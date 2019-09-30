package com.ruoyi.goal.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.goal.domain.GmGoal;
import com.ruoyi.goal.domain.GoalFilterCondition;
import com.ruoyi.goal.domain.GoalStatic;
import com.ruoyi.goal.mapper.GmGoalMapper;
import com.ruoyi.goal.service.IGmGoalService;
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
 * 目标Service业务层处理
 * 
 * @author mason
 * @date 2019-09-18
 */
@Service
public class GmGoalServiceImpl implements IGmGoalService 
{
    @Autowired
    private GmGoalMapper gmGoalMapper;

    /**
     * 查询目标
     * 
     * @param id 目标ID
     * @return 目标
     */
    @Override
    public GmGoal selectGmGoalById(Long id)
    {
        return gmGoalMapper.selectGmGoalById(id);
    }

    /**
     * 查询目标列表
     * 
     * @param gmGoal 目标
     * @return 目标
     */
    @Override
    public List<GmGoal> selectGmGoalList(GmGoal gmGoal)
    {
        return gmGoalMapper.selectGmGoalList(gmGoal);
    }

    /**
     * 根据条件查询
     * @param goalFilterCondition
     * @return
     */
    @Override
    public List<GmGoal> selectGmGoalListByCondition(GoalFilterCondition goalFilterCondition) {
        return gmGoalMapper.selectGmGoalListByCondition(goalFilterCondition);
    }

    /**
     * 新增目标
     * 
     * @param gmGoal 目标
     * @return 结果
     */
    @Override
    public int insertGmGoal(GmGoal gmGoal)
    {
        return gmGoalMapper.insertGmGoal(gmGoal);
    }

    /**
     * 修改目标
     * 
     * @param gmGoal 目标
     * @return 结果
     */
    @Override
    public int updateGmGoal(GmGoal gmGoal)
    {
        return gmGoalMapper.updateGmGoal(gmGoal);
    }

    /**
     * 删除目标对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGmGoalByIds(String ids)
    {
        return gmGoalMapper.deleteGmGoalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除目标信息
     * 
     * @param id 目标ID
     * @return 结果
     */
    @Override
    public int deleteGmGoalById(Long id)
    {
        return gmGoalMapper.deleteGmGoalById(id);
    }

}

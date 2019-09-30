package com.ruoyi.goal.domain;

import lombok.Data;

import java.util.Date;

/**
 * 查询条件类
 *
 * @author mason
 * @since 2019/9/26
 */
@Data
public class GoalFilterCondition extends GmGoal{

    /**
     * 完成标识：all,done,notDone
     */
    String doneTag;
    /**
     * 目标开始时间
     */
    Date goalStartTime;
    /**
     * 目标结束时间
     */
    Date goalEndTime;
}

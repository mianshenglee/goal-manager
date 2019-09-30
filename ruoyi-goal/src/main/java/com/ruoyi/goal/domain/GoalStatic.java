package com.ruoyi.goal.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 目标统计数据
 *
 * @author mason
 * @since 2019/9/25
 */
@Data
@Builder
public class GoalStatic {
    private int totalGoal;
    private int doneGoal;
    private int deletedGoal;
    private int notStartGoal;
    private int ideaCount;
    private int hotspotCount;
}

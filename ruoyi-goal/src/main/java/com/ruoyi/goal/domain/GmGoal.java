package com.ruoyi.goal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 目标对象 gm_goal
 * 
 * @author mason
 * @date 2019-09-18
 */
public class GmGoal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 记录 */
    @Excel(name = "记录")
    private Long recordVersion;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sysCreateTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String sysCreateBy;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sysUpdateTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String sysUpdateBy;

    /** 目标阶段（见字典表：年、月、日、时） */
    @Excel(name = "目标阶段", readConverterExp = "见=字典表：年、月、日、时")
    private String goalPhase;

    /** 目标所属时间 */
    @Excel(name = "目标所属时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date goalTime;

    /** 目标类型（见字典表：技能、学习...） */
    @Excel(name = "目标类型", readConverterExp = "见=字典表：技能、学习...")
    private String goalType;

    /** 目标内容 */
    @Excel(name = "目标内容")
    private String goalContent;

    /** 目标完成进度 */
    @Excel(name = "目标完成进度")
    private Long process;

    /** 是否删除：1是，0否 */
    @Excel(name = "是否删除：1是，0否")
    private String isDeleted;

    /** 是否计划外的：1是，0否 */
    @Excel(name = "是否计划外的：1是，0否")
    private String isExtra;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRecordVersion(Long recordVersion) 
    {
        this.recordVersion = recordVersion;
    }

    public Long getRecordVersion() 
    {
        return recordVersion;
    }
    public void setSysCreateTime(Date sysCreateTime) 
    {
        this.sysCreateTime = sysCreateTime;
    }

    public Date getSysCreateTime() 
    {
        return sysCreateTime;
    }

    public String getSysCreateBy() {
        return sysCreateBy;
    }

    public void setSysCreateBy(String sysCreateBy) {
        this.sysCreateBy = sysCreateBy;
    }

    public String getSysUpdateBy() {
        return sysUpdateBy;
    }

    public void setSysUpdateBy(String sysUpdateBy) {
        this.sysUpdateBy = sysUpdateBy;
    }

    public void setSysUpdateTime(Date sysUpdateTime)
    {
        this.sysUpdateTime = sysUpdateTime;
    }

    public Date getSysUpdateTime() 
    {
        return sysUpdateTime;
    }
    public void setGoalPhase(String goalPhase) 
    {
        this.goalPhase = goalPhase;
    }

    public String getGoalPhase() 
    {
        return goalPhase;
    }
    public void setGoalTime(Date goalTime) 
    {
        this.goalTime = goalTime;
    }

    public Date getGoalTime() 
    {
        return goalTime;
    }
    public void setGoalType(String goalType) 
    {
        this.goalType = goalType;
    }

    public String getGoalType() 
    {
        return goalType;
    }
    public void setGoalContent(String goalContent) 
    {
        this.goalContent = goalContent;
    }

    public String getGoalContent() 
    {
        return goalContent;
    }
    public void setProcess(Long process) 
    {
        this.process = process;
    }

    public Long getProcess() 
    {
        return process;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }
    public void setIsExtra(String isExtra) 
    {
        this.isExtra = isExtra;
    }

    public String getIsExtra() 
    {
        return isExtra;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordVersion", getRecordVersion())
            .append("sysCreateTime", getSysCreateTime())
            .append("sysCreateBy", getSysCreateBy())
            .append("sysUpdateTime", getSysUpdateTime())
            .append("sysUpdateBy", getSysUpdateBy())
            .append("goalPhase", getGoalPhase())
            .append("goalTime", getGoalTime())
            .append("goalType", getGoalType())
            .append("goalContent", getGoalContent())
            .append("process", getProcess())
            .append("isDeleted", getIsDeleted())
            .append("isExtra", getIsExtra())
            .toString();
    }
}

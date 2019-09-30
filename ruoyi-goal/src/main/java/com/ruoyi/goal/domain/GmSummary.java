package com.ruoyi.goal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 总结对象 gm_summary
 * 
 * @author mason
 * @date 2019-09-27
 */
public class GmSummary extends BaseEntity
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

    /** 总结的时间 */
    @Excel(name = "总结的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date summaryTime;

    /** 总结内容 */
    @Excel(name = "总结内容")
    private String summary;

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
    public void setSysUpdateTime(Date sysUpdateTime)
    {
        this.sysUpdateTime = sysUpdateTime;
    }

    public Date getSysUpdateTime() 
    {
        return sysUpdateTime;
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

    public void setSummaryTime(Date summaryTime)
    {
        this.summaryTime = summaryTime;
    }

    public Date getSummaryTime() 
    {
        return summaryTime;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
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
            .append("summaryTime", getSummaryTime())
            .append("summary", getSummary())
            .toString();
    }
}

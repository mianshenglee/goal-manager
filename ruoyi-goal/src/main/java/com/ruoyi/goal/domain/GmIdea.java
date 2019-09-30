package com.ruoyi.goal.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 想法对象 gm_idea
 * 
 * @author mason
 * @date 2019-09-27
 */
public class GmIdea extends BaseEntity
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

    /** 想法内容 */
    @Excel(name = "想法内容")
    private String content;

    /** 想法类型 */
    @Excel(name = "想法类型")
    private String type;

    public Date getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(Date ideaTime) {
        this.ideaTime = ideaTime;
    }

    /** 想法所属时间 */
    @Excel(name = "想法所属时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ideaTime;

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

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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
            .append("content", getContent())
            .append("type", getType())
            .toString();
    }
}

package com.ruoyi.goal.mapper;

import com.ruoyi.goal.domain.GmSummary;
import java.util.List;

/**
 * 总结Mapper接口
 * 
 * @author mason
 * @date 2019-09-27
 */
public interface GmSummaryMapper 
{
    /**
     * 查询总结
     * 
     * @param id 总结ID
     * @return 总结
     */
    public GmSummary selectGmSummaryById(Long id);

    /**
     * 查询总结列表
     * 
     * @param gmSummary 总结
     * @return 总结集合
     */
    public List<GmSummary> selectGmSummaryList(GmSummary gmSummary);

    /**
     * 新增总结
     * 
     * @param gmSummary 总结
     * @return 结果
     */
    public int insertGmSummary(GmSummary gmSummary);

    /**
     * 修改总结
     * 
     * @param gmSummary 总结
     * @return 结果
     */
    public int updateGmSummary(GmSummary gmSummary);

    /**
     * 删除总结
     * 
     * @param id 总结ID
     * @return 结果
     */
    public int deleteGmSummaryById(Long id);

    /**
     * 批量删除总结
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGmSummaryByIds(String[] ids);
}

package com.ruoyi.goal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goal.mapper.GmSummaryMapper;
import com.ruoyi.goal.domain.GmSummary;
import com.ruoyi.goal.service.IGmSummaryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 总结Service业务层处理
 * 
 * @author mason
 * @date 2019-09-27
 */
@Service
public class GmSummaryServiceImpl implements IGmSummaryService 
{
    @Autowired
    private GmSummaryMapper gmSummaryMapper;

    /**
     * 查询总结
     * 
     * @param id 总结ID
     * @return 总结
     */
    @Override
    public GmSummary selectGmSummaryById(Long id)
    {
        return gmSummaryMapper.selectGmSummaryById(id);
    }

    /**
     * 查询总结列表
     * 
     * @param gmSummary 总结
     * @return 总结
     */
    @Override
    public List<GmSummary> selectGmSummaryList(GmSummary gmSummary)
    {
        return gmSummaryMapper.selectGmSummaryList(gmSummary);
    }

    /**
     * 新增总结
     * 
     * @param gmSummary 总结
     * @return 结果
     */
    @Override
    public int insertGmSummary(GmSummary gmSummary)
    {
        return gmSummaryMapper.insertGmSummary(gmSummary);
    }

    /**
     * 修改总结
     * 
     * @param gmSummary 总结
     * @return 结果
     */
    @Override
    public int updateGmSummary(GmSummary gmSummary)
    {
        return gmSummaryMapper.updateGmSummary(gmSummary);
    }

    /**
     * 删除总结对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGmSummaryByIds(String ids)
    {
        return gmSummaryMapper.deleteGmSummaryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除总结信息
     * 
     * @param id 总结ID
     * @return 结果
     */
    public int deleteGmSummaryById(Long id)
    {
        return gmSummaryMapper.deleteGmSummaryById(id);
    }
}

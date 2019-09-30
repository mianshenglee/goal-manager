package com.ruoyi.goal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goal.mapper.GmHotspotMapper;
import com.ruoyi.goal.domain.GmHotspot;
import com.ruoyi.goal.service.IGmHotspotService;
import com.ruoyi.common.core.text.Convert;

/**
 * 热点Service业务层处理
 * 
 * @author mason
 * @date 2019-09-27
 */
@Service
public class GmHotspotServiceImpl implements IGmHotspotService 
{
    @Autowired
    private GmHotspotMapper gmHotspotMapper;

    /**
     * 查询热点
     * 
     * @param id 热点ID
     * @return 热点
     */
    @Override
    public GmHotspot selectGmHotspotById(Long id)
    {
        return gmHotspotMapper.selectGmHotspotById(id);
    }

    /**
     * 查询热点列表
     * 
     * @param gmHotspot 热点
     * @return 热点
     */
    @Override
    public List<GmHotspot> selectGmHotspotList(GmHotspot gmHotspot)
    {
        return gmHotspotMapper.selectGmHotspotList(gmHotspot);
    }

    /**
     * 新增热点
     * 
     * @param gmHotspot 热点
     * @return 结果
     */
    @Override
    public int insertGmHotspot(GmHotspot gmHotspot)
    {
        return gmHotspotMapper.insertGmHotspot(gmHotspot);
    }

    /**
     * 修改热点
     * 
     * @param gmHotspot 热点
     * @return 结果
     */
    @Override
    public int updateGmHotspot(GmHotspot gmHotspot)
    {
        return gmHotspotMapper.updateGmHotspot(gmHotspot);
    }

    /**
     * 删除热点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGmHotspotByIds(String ids)
    {
        return gmHotspotMapper.deleteGmHotspotByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除热点信息
     * 
     * @param id 热点ID
     * @return 结果
     */
    public int deleteGmHotspotById(Long id)
    {
        return gmHotspotMapper.deleteGmHotspotById(id);
    }
}

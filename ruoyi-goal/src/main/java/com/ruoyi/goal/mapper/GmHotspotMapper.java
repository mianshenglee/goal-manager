package com.ruoyi.goal.mapper;

import com.ruoyi.goal.domain.GmHotspot;
import java.util.List;

/**
 * 热点Mapper接口
 * 
 * @author mason
 * @date 2019-09-27
 */
public interface GmHotspotMapper 
{
    /**
     * 查询热点
     * 
     * @param id 热点ID
     * @return 热点
     */
    public GmHotspot selectGmHotspotById(Long id);

    /**
     * 查询热点列表
     * 
     * @param gmHotspot 热点
     * @return 热点集合
     */
    public List<GmHotspot> selectGmHotspotList(GmHotspot gmHotspot);

    /**
     * 新增热点
     * 
     * @param gmHotspot 热点
     * @return 结果
     */
    public int insertGmHotspot(GmHotspot gmHotspot);

    /**
     * 修改热点
     * 
     * @param gmHotspot 热点
     * @return 结果
     */
    public int updateGmHotspot(GmHotspot gmHotspot);

    /**
     * 删除热点
     * 
     * @param id 热点ID
     * @return 结果
     */
    public int deleteGmHotspotById(Long id);

    /**
     * 批量删除热点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGmHotspotByIds(String[] ids);
}

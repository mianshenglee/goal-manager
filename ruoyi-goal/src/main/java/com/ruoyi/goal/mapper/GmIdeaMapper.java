package com.ruoyi.goal.mapper;

import com.ruoyi.goal.domain.GmIdea;
import java.util.List;

/**
 * 想法Mapper接口
 * 
 * @author mason
 * @date 2019-09-27
 */
public interface GmIdeaMapper 
{
    /**
     * 查询想法
     * 
     * @param id 想法ID
     * @return 想法
     */
    public GmIdea selectGmIdeaById(Long id);

    /**
     * 查询想法列表
     * 
     * @param gmIdea 想法
     * @return 想法集合
     */
    public List<GmIdea> selectGmIdeaList(GmIdea gmIdea);

    /**
     * 新增想法
     * 
     * @param gmIdea 想法
     * @return 结果
     */
    public int insertGmIdea(GmIdea gmIdea);

    /**
     * 修改想法
     * 
     * @param gmIdea 想法
     * @return 结果
     */
    public int updateGmIdea(GmIdea gmIdea);

    /**
     * 删除想法
     * 
     * @param id 想法ID
     * @return 结果
     */
    public int deleteGmIdeaById(Long id);

    /**
     * 批量删除想法
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGmIdeaByIds(String[] ids);
}

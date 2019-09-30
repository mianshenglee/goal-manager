package com.ruoyi.goal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goal.mapper.GmIdeaMapper;
import com.ruoyi.goal.domain.GmIdea;
import com.ruoyi.goal.service.IGmIdeaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 想法Service业务层处理
 * 
 * @author mason
 * @date 2019-09-27
 */
@Service
public class GmIdeaServiceImpl implements IGmIdeaService 
{
    @Autowired
    private GmIdeaMapper gmIdeaMapper;

    /**
     * 查询想法
     * 
     * @param id 想法ID
     * @return 想法
     */
    @Override
    public GmIdea selectGmIdeaById(Long id)
    {
        return gmIdeaMapper.selectGmIdeaById(id);
    }

    /**
     * 查询想法列表
     * 
     * @param gmIdea 想法
     * @return 想法
     */
    @Override
    public List<GmIdea> selectGmIdeaList(GmIdea gmIdea)
    {
        return gmIdeaMapper.selectGmIdeaList(gmIdea);
    }

    /**
     * 新增想法
     * 
     * @param gmIdea 想法
     * @return 结果
     */
    @Override
    public int insertGmIdea(GmIdea gmIdea)
    {
        return gmIdeaMapper.insertGmIdea(gmIdea);
    }

    /**
     * 修改想法
     * 
     * @param gmIdea 想法
     * @return 结果
     */
    @Override
    public int updateGmIdea(GmIdea gmIdea)
    {
        return gmIdeaMapper.updateGmIdea(gmIdea);
    }

    /**
     * 删除想法对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGmIdeaByIds(String ids)
    {
        return gmIdeaMapper.deleteGmIdeaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除想法信息
     * 
     * @param id 想法ID
     * @return 结果
     */
    public int deleteGmIdeaById(Long id)
    {
        return gmIdeaMapper.deleteGmIdeaById(id);
    }
}

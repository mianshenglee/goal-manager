package com.ruoyi.goal.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.goal.domain.GmIdea;
import com.ruoyi.goal.service.IGmIdeaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 想法Controller
 * 
 * @author mason
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/goal/idea")
public class GmIdeaController extends BaseController
{
    private String prefix = "goal/idea";

    @Autowired
    private IGmIdeaService gmIdeaService;

    @GetMapping()
    public String idea()
    {
        return prefix + "/idea";
    }

    /**
     * 查询想法列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GmIdea gmIdea)
    {
        startPage();
        List<GmIdea> list = gmIdeaService.selectGmIdeaList(gmIdea);
        return getDataTable(list);
    }

    /**
     * 导出想法列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GmIdea gmIdea)
    {
        List<GmIdea> list = gmIdeaService.selectGmIdeaList(gmIdea);
        ExcelUtil<GmIdea> util = new ExcelUtil<GmIdea>(GmIdea.class);
        return util.exportExcel(list, "idea");
    }

    /**
     * 新增想法
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存想法
     */
    @Log(title = "想法", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GmIdea gmIdea)
    {
        gmIdea.setSysCreateBy(ShiroUtils.getLoginName());
        gmIdea.setSysUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gmIdeaService.insertGmIdea(gmIdea));
    }

    /**
     * 修改想法
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GmIdea gmIdea = gmIdeaService.selectGmIdeaById(id);
        mmap.put("gmIdea", gmIdea);
        return prefix + "/edit";
    }

    /**
     * 修改保存想法
     */
    @Log(title = "想法", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GmIdea gmIdea)
    {
        return toAjax(gmIdeaService.updateGmIdea(gmIdea));
    }

    /**
     * 删除想法
     */
    @Log(title = "想法", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gmIdeaService.deleteGmIdeaByIds(ids));
    }
}

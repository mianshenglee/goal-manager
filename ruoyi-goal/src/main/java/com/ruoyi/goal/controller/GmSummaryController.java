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
import com.ruoyi.goal.domain.GmSummary;
import com.ruoyi.goal.service.IGmSummaryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 总结Controller
 * 
 * @author mason
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/goal/summary")
public class GmSummaryController extends BaseController
{
    private String prefix = "goal/summary";

    @Autowired
    private IGmSummaryService gmSummaryService;

    @RequiresPermissions("goal:summary:view")
    @GetMapping()
    public String summary()
    {
        return prefix + "/summary";
    }

    /**
     * 查询总结列表
     */
    @RequiresPermissions("goal:summary:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GmSummary gmSummary)
    {
        startPage();
        List<GmSummary> list = gmSummaryService.selectGmSummaryList(gmSummary);
        return getDataTable(list);
    }

    /**
     * 导出总结列表
     */
    @RequiresPermissions("goal:summary:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GmSummary gmSummary)
    {
        List<GmSummary> list = gmSummaryService.selectGmSummaryList(gmSummary);
        ExcelUtil<GmSummary> util = new ExcelUtil<GmSummary>(GmSummary.class);
        return util.exportExcel(list, "summary");
    }

    /**
     * 新增总结
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存总结
     */
    @RequiresPermissions("goal:summary:add")
    @Log(title = "总结", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult addSave(GmSummary gmSummary)
    {
        int affectNum = 0;
        gmSummary.setSysCreateBy(ShiroUtils.getLoginName());
        if(gmSummary.getId() == null){
            affectNum = gmSummaryService.insertGmSummary(gmSummary);
        }else{
            affectNum = gmSummaryService.updateGmSummary(gmSummary);
        }
        return toAjax(affectNum);
    }

    /**
     * 修改总结
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GmSummary gmSummary = gmSummaryService.selectGmSummaryById(id);
        mmap.put("gmSummary", gmSummary);
        return prefix + "/edit";
    }

    /**
     * 修改保存总结
     */
    @RequiresPermissions("goal:summary:edit")
    @Log(title = "总结", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GmSummary gmSummary)
    {
        gmSummary.setSysCreateBy(ShiroUtils.getLoginName());
        return toAjax(gmSummaryService.updateGmSummary(gmSummary));
    }

    /**
     * 删除总结
     */
    @RequiresPermissions("goal:summary:remove")
    @Log(title = "总结", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gmSummaryService.deleteGmSummaryByIds(ids));
    }
}

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
import com.ruoyi.goal.domain.GmHotspot;
import com.ruoyi.goal.service.IGmHotspotService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 热点Controller
 * 
 * @author mason
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/goal/hotspot")
public class GmHotspotController extends BaseController
{
    private String prefix = "goal/hotspot";

    @Autowired
    private IGmHotspotService gmHotspotService;

    @RequiresPermissions("goal:hotspot:view")
    @GetMapping()
    public String hotspot()
    {
        return prefix + "/hotspot";
    }

    /**
     * 查询热点列表
     */
    @RequiresPermissions("goal:hotspot:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GmHotspot gmHotspot)
    {
        startPage();
        List<GmHotspot> list = gmHotspotService.selectGmHotspotList(gmHotspot);
        return getDataTable(list);
    }

    /**
     * 导出热点列表
     */
    @RequiresPermissions("goal:hotspot:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GmHotspot gmHotspot)
    {
        List<GmHotspot> list = gmHotspotService.selectGmHotspotList(gmHotspot);
        ExcelUtil<GmHotspot> util = new ExcelUtil<GmHotspot>(GmHotspot.class);
        return util.exportExcel(list, "hotspot");
    }

    /**
     * 新增热点
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存热点
     */
    @RequiresPermissions("goal:hotspot:add")
    @Log(title = "热点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GmHotspot gmHotspot)
    {
        gmHotspot.setSysCreateBy(ShiroUtils.getLoginName());
        gmHotspot.setSysUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gmHotspotService.insertGmHotspot(gmHotspot));
    }

    /**
     * 修改热点
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GmHotspot gmHotspot = gmHotspotService.selectGmHotspotById(id);
        mmap.put("gmHotspot", gmHotspot);
        return prefix + "/edit";
    }

    /**
     * 修改保存热点
     */
    @RequiresPermissions("goal:hotspot:edit")
    @Log(title = "热点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GmHotspot gmHotspot)
    {
        gmHotspot.setSysCreateBy(ShiroUtils.getLoginName());
        gmHotspot.setSysUpdateBy(ShiroUtils.getLoginName());
        return toAjax(gmHotspotService.updateGmHotspot(gmHotspot));
    }

    /**
     * 删除热点
     */
    @RequiresPermissions("goal:hotspot:remove")
    @Log(title = "热点", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gmHotspotService.deleteGmHotspotByIds(ids));
    }
}

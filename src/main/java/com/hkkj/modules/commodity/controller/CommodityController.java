package com.hkkj.modules.commodity.controller;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.annotation.OperationLog;
import com.hkkj.common.base.controller.BaseController;
import com.hkkj.common.security.token.FormToken;
import com.hkkj.common.util.ExcelUtil;
import com.hkkj.modules.commodity.model.Commodity;
import com.hkkj.modules.commodity.model.CommodityVo;
import com.hkkj.modules.commodity.service.CommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ldw
 * @create 2019-06-05 14:35
 */
@Controller
@RequestMapping("/admin/commodity")
public class CommodityController extends BaseController {

    private static final String BASE_PATH = "admin/commodity/";

    private static final String DATA_PATH = "D:\\商品数据列表.xlsx";

    @Resource
    private CommodityService commodityService;

    @RequiresPermissions("commodity:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String model, String name, String spareNo, String drawingNumber, String specification, String supplier, ModelMap modelMap) {
        log.info("分页查询内容列表参数! pageNum = {}", pageNum);
        PageInfo<Commodity> pageInfo = commodityService.findPage(pageNum, PAGESIZE, model, name, spareNo, drawingNumber, specification, supplier);
        log.info("分页查询内容列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("model", model);
        modelMap.put("name", name);
        modelMap.put("spareNo", spareNo);
        modelMap.put("drawingNumber", drawingNumber);
        modelMap.put("specification", specification);
        modelMap.put("supplier", supplier);
        return BASE_PATH + "commodity-list";
    }

    /**
     * 跳转到商品添加页面
     *
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("commodity:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap) {
        log.info("跳转到商品添加页面!");
        return BASE_PATH + "commodity-add";
    }

    /**
     * 添加商品
     *
     * @param commodity
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加商品")
    @RequiresPermissions("commodity:create")
    @ResponseBody
    @PostMapping("/save")
    public ModelMap saveContent(Commodity commodity) {
        log.info("添加商品!" + commodity.toString());
        ModelMap messagesMap = new ModelMap();
        commodity.setCreateTime(new Date());
        commodityService.save(commodity);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 批量删除商品
     *
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除商品")
    @RequiresPermissions("commodity:delete")
    @DeleteMapping(value = "/batch/{ids}")
    public ResponseEntity<String> deleteBatch(@PathVariable("ids") List<Object> ids) {
        try {
            log.debug("批量删除商品! ids = {}", ids);

            if (null == ids) {
                log.info("批量删除商品不存在! ids = {}", ids);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            commodityService.deleteByCondition(Commodity.class, "id", ids);
            log.info("批量删除商品成功! ids = {}", ids);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("批量删除商品失败! ids = {}, e = {}", ids, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到编辑商品页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("commodity:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        Commodity commodity = commodityService.findById(id);
        modelMap.put("commodity", commodity);
        return BASE_PATH + "commodity-edit";
    }

    /**
     * 修改商品
     *
     * @param commodity
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "修改商品")
    @RequiresPermissions("commodity:edit")
    @ResponseBody
    @PostMapping("/update")
    public ModelMap updateContent(Commodity commodity) {
        ModelMap messagesMap = new ModelMap();
        log.info("修改商品" + commodity.toString());
        commodity.setModifyTime(new Date());
        commodityService.updateSelective(commodity);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "编辑成功!");
        return messagesMap;
    }

    /**
     * 导入数据
     *
     * @param files
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("commodity:excelImport")
    @PostMapping("/newExcelImport")
    public ResponseEntity<String> newExcelImport(@RequestParam("uploadFile") MultipartFile[] files) throws IOException {
        log.info("导入数据");
        if (files != null && files.length > 0) {
            try {
                MultipartFile file = files[0];
                List<Object> objects = ExcelUtil.readExcel(file, new CommodityVo(), 1, 1);
                for (Object o : objects) {
                    //数据复制
                    Commodity commodity = new Commodity();
                    CommodityVo cv = (CommodityVo) o;
                    log.info("导入数据" + cv.toString());
                    BeanUtils.copyProperties(cv, commodity);
                    if (!StringUtils.isEmpty(cv.getPrice())) {
                        commodity.setPrice(new BigDecimal(cv.getPrice()));
                    }
                    if (!StringUtils.isEmpty(cv.getTaxPrice())) {
                        commodity.setTaxPrice(new BigDecimal(cv.getTaxPrice()));
                    }
                    commodity.setStatus(1);
                    commodity.setCreateTime(new Date());
                    log.info("导入数据" + commodity.toString());
                    //插入数据
                    commodityService.save(commodity);
                }
                return ResponseEntity.ok("{msg:'导入成功'}");
            } catch (Exception e) {
                log.error("导入数据失败!", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 跳转到商品添加页面
     *
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("commodity:excelExport")
    @GetMapping(value = "/excelExport")
    public ResponseEntity<String> excelExport(ModelMap modelMap) {
        log.info("导出数据!");
        List<Commodity> allCommodityList = commodityService.getAllCommodityList();
        ArrayList<String> head = new ArrayList<>();
        head.add("品牌");
        head.add("型号");
        head.add("商品名称");
        head.add("备件编号");
        head.add("货品图号");
        head.add("货品规格");
        head.add("含税价");
        head.add("现金价");
        head.add("供应单位");
        head.add("报价时间");
        head.add("备注1");
        head.add("备注2");
        head.add("备注3");
        ArrayList<List<Object>> datas = new ArrayList<>();
        for (Commodity commodity : allCommodityList){
            final ArrayList<Object> data = new ArrayList<>();
            data.add(commodity.getBrand());
            data.add(commodity.getModel());
            data.add(commodity.getName());
            data.add(commodity.getSpareNo());
            data.add(commodity.getDrawingNumber());
            data.add(commodity.getSpecification());
            data.add(commodity.getTaxPrice());
            data.add(commodity.getPrice());
            data.add(commodity.getSupplier());
            data.add(commodity.getQuotedTime());
            data.add(commodity.getContent1());
            data.add(commodity.getContent2());
            data.add(commodity.getContent3());
            datas.add(data);
        }
        ExcelUtil.writeBySimple(DATA_PATH,datas,head);
        return ResponseEntity.ok("{msg:'导出成功'}");
    }

}

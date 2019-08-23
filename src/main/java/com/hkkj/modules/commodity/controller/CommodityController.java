package com.hkkj.modules.commodity.controller;

import com.alibaba.excel.metadata.Sheet;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    private static final String DATA_PATH = "D:\\商品导入.xlsx";


    @Resource
    private CommodityService commodityService;

    @RequiresPermissions("commodity:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String name, String code, String brand, String supplier, ModelMap modelMap) {
        log.debug("分页查询内容列表参数! pageNum = {}", pageNum);
        PageInfo<Commodity> pageInfo = commodityService.findPage(pageNum, PAGESIZE, name, code, brand, supplier);
        log.info("分页查询内容列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("name", name);
        modelMap.put("code", code);
        modelMap.put("brand", brand);
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
     * 保存内容
     *
     * @param commodity
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加内容")
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
     * 批量删除日志
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
     * 跳转到编辑内容页面
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
     * 更新内容
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

    @FormToken(save = true)
    @RequiresPermissions("commodity:excelImport")
    @GetMapping("/excelImport")
    public ResponseEntity<String>  excelImport() {
        try {
            log.info("导入数据");
            //第一个1代表sheet1, 第二个1代表从第几行开始读取数据，行号最小值为0
            Sheet sheet = new Sheet(1, 1, CommodityVo.class);
            List<Object> objects = ExcelUtil.readLessThan1000RowBySheet(DATA_PATH,sheet);

            for (Object o : objects){
                //数据复制
                Commodity commodity = new Commodity();
                CommodityVo cv = (CommodityVo) o;

                log.info("导入数据" + cv.toString());

                BeanUtils.copyProperties(cv,commodity);
                if (!StringUtils.isEmpty(cv.getPrice())){
                    commodity.setPrice(new BigDecimal(cv.getPrice()));
                }
                if (!StringUtils.isEmpty(cv.getTaxPrice())){
                    commodity.setTaxPrice(new BigDecimal(cv.getTaxPrice()));
                }
                commodity.setStatus(1);
                commodity.setCreateTime(new Date());
                log.info("导入数据" + commodity.toString());
                //插入数据
                commodityService.save(commodity);
            }
            return ResponseEntity.ok("导入成功！");
        } catch (Exception e) {
            log.error("导入数据失败!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}

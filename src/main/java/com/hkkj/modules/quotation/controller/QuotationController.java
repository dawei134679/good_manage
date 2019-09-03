package com.hkkj.modules.quotation.controller;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.annotation.OperationLog;
import com.hkkj.common.base.controller.BaseController;
import com.hkkj.common.security.token.FormToken;
import com.hkkj.common.util.ExcelUtil;
import com.hkkj.modules.quotation.model.Qcommodity;
import com.hkkj.modules.quotation.model.QcommodityVo;
import com.hkkj.modules.quotation.model.Quotation;
import com.hkkj.modules.quotation.service.QcommodityService;
import com.hkkj.modules.quotation.service.QuotationService;
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
import java.util.Date;
import java.util.List;

/**
 * @author ldw
 * @create 2019-08-25 14:35
 */
@Controller
@RequestMapping("/admin/quotation")
public class QuotationController extends BaseController {

    private static final String BASE_PATH = "admin/quotation/";

    @Resource
    private QuotationService quotationService;

    @Resource
    private QcommodityService qcommodityService;

    @RequiresPermissions("quotation:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String code, String customer, String salesman, String startTime, String endTime, ModelMap modelMap) {
        log.info("分页查询报价单列表参数! pageNum = {}", pageNum);
        PageInfo<Quotation> pageInfo = quotationService.findPage(pageNum, PAGESIZE, code, customer, salesman, startTime, endTime);
        log.info("分页查询内容列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo); 
        modelMap.put("code", code);
        modelMap.put("customer", customer);
        modelMap.put("salesman", salesman);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        return BASE_PATH + "quotation-list";
    }

    /**
     * 跳转到报价单添加页面
     *
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("quotation:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap) {
        log.info("跳转到报价单添加页面!");
        return BASE_PATH + "quotation-add";
    }

    /**
     * 保存报价单
     *
     * @param quotation
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "保存报价单")
    @RequiresPermissions("quotation:create")
    @ResponseBody
    @PostMapping("/save")
    public ModelMap saveContent(Quotation quotation) {
        log.info("保存报价单!" + quotation.toString());
        ModelMap messagesMap = new ModelMap();
        quotation.setCreateTime(new Date());
        quotationService.save(quotation);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 批量删除报价单
     *
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除报价单")
    @RequiresPermissions("quotation:delete")
    @DeleteMapping(value = "/batch/{ids}")
    public ResponseEntity<String> deleteBatch(@PathVariable("ids") List<Object> ids) {
        try {
            log.debug("批量删除报价单! ids = {}", ids);

            if (null == ids) {
                log.info("批量删除报价单不存在! ids = {}", ids);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            quotationService.deleteByCondition(Quotation.class, "id", ids);
            log.info("批量删除报价单成功! ids = {}", ids);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("批量删除报价单失败! ids = {}, e = {}", ids, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到编辑报价单页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("quotation:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        Quotation quotation = quotationService.findById(id);
        modelMap.put("quotation", quotation);
        return BASE_PATH + "quotation-edit";
    }

    /**
     * 更新报价单
     *
     * @param quotation
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "修改报价单")
    @RequiresPermissions("quotation:edit")
    @ResponseBody
    @PostMapping("/update")
    public ModelMap updateQuotation(Quotation quotation) {
        ModelMap messagesMap = new ModelMap();
        log.info("修改报价单" + quotation.toString());
        quotation.setModifyTime(new Date());
        quotationService.updateSelective(quotation);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "编辑成功!");
        return messagesMap;
    }

    /**
     * 跳转报价单详情页面
     *
     * @return
     */
    @FormToken(save = true)
    @OperationLog(value = "跳转报价单详情页面")
    @RequiresPermissions("quotation:commmodityInfo")
    @GetMapping(value = "/commodityInfo/{id}")
    public String quotationCommmodityInfo(@PathVariable("id") Long id, ModelMap modelMap) {
        Quotation quotation = quotationService.findById(id);
        List<Qcommodity> qcommodityList= qcommodityService.getQcommodityByQid(id);
        modelMap.put("quotation", quotation);
        modelMap.put("qcommodityList", qcommodityList);
        Integer commodityCount = 0;
        Integer allCommodityNum = 0;
        BigDecimal allAmount = BigDecimal.valueOf(0);
        if (null != qcommodityList){
            commodityCount = qcommodityList.size();
            for (Qcommodity qcommodity : qcommodityList){
                if (null != qcommodity.getAmount()){
                    allAmount = allAmount.add(qcommodity.getAmount());
                }
                if (null != qcommodity.getNum()){
                    allCommodityNum+=qcommodity.getNum();
                }
            }
        }
        String commodityCountStr = commodityCount+"条记录";
        modelMap.put("commodityCountStr", commodityCountStr);
        modelMap.put("allAmount", allAmount);
        modelMap.put("allCommodityNum", allCommodityNum);

        log.info("跳转报价单详情页面!");
        return BASE_PATH + "quotation-commmodityInfo";
    }

    /**
     * 跳转到报价单商品添加页面
     *
     * @return
     */
    @FormToken(save = true)
    @GetMapping(value = "/commmodityAdd/{id}")
    public String addCommodity(@PathVariable("id") Long id,ModelMap modelMap) {
        Quotation quotation = quotationService.findById(id);
        modelMap.put("qid",id);
        log.info("跳转到商品添加页面!");
        return BASE_PATH + "quotation-commodity-add";
    }

    /**
     * 添加报价单商品
     *
     * @param qcommodity
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加报价单商品内容")
    //@RequiresPermissions("quotation:create")
    @ResponseBody
    @PostMapping("/saveQcommodity")
    public ModelMap saveQcommodity(Qcommodity qcommodity) {
        log.info("添加商品!" + qcommodity.toString());
        ModelMap messagesMap = new ModelMap();
        qcommodity.setCreateTime(new Date());
        qcommodityService.save(qcommodity);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 跳转到编辑报价单商品页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @GetMapping("/commodityEdit/{id}")
    public String editCommodity(@PathVariable("id") Long id, ModelMap modelMap) {
        Qcommodity qcommodity = qcommodityService.findById(id);
        modelMap.put("qcommodity", qcommodity);
        return BASE_PATH + "quotation-commodity-edit";
    }

    /**
     * 更新报价单商品
     *
     * @param qcommodity
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "修改报价单商品")
    @ResponseBody
    @PostMapping("/commodityUpdate")
    public ModelMap updateCommodity(Qcommodity qcommodity) {
        ModelMap messagesMap = new ModelMap();
        log.info("修改报价单商品" + qcommodity.toString());
        qcommodity.setModifyTime(new Date());
        qcommodityService.updateSelective(qcommodity);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "编辑成功!");
        return messagesMap;
    }

    /**
     * 批量删除报价单商品
     *
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除报价单商品")
    @DeleteMapping(value = "/commoditybatch/{ids}")
    public ResponseEntity<String> commodityDeleteBatch(@PathVariable("ids") List<Object> ids) {
        try {
            log.debug("批量删除报价单商品! ids = {}", ids);

            if (null == ids) {
                log.info("批量删除报价单商品不存在! ids = {}", ids);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            qcommodityService.deleteByCondition(Qcommodity.class, "id", ids);
            log.info("批量删除报价单商品成功! ids = {}", ids);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("批量删除报价单商品失败! ids = {}, e = {}", ids, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 导入报价单商品
     *
     * @param files
     * @return
     */
    @FormToken(save = true)
    @OperationLog(value = "导入报价单商品")
   // @RequiresPermissions("quotation:commodityExcelImport")
    @PostMapping("/commodityExcelImport")
    public ResponseEntity<String> newExcelImport(@RequestParam("uploadFileCommodity") MultipartFile[] files, Long qid) throws IOException {
        log.info("导入数据，报价单qid : "+qid);
        if(files != null && files.length > 0){
           /* try {*/
                MultipartFile file = files[0];
                List<Object> objects = ExcelUtil.readExcel(file, new QcommodityVo(),1,1);
                for (Object o : objects) {
                    //数据复制
                    Qcommodity qcommodity = new Qcommodity();
                    qcommodity.setQid(qid);
                    QcommodityVo qv = (QcommodityVo) o;
                    log.info("导入数据" + qv.toString());
                    BeanUtils.copyProperties(qv, qcommodity);
                    if (!StringUtils.isEmpty(qv.getPrice())) {
                        qcommodity.setPrice(new BigDecimal(qv.getPrice()));
                    }
                    if (!StringUtils.isEmpty(qv.getNtaxPrice())) {
                        qcommodity.setNtaxPrice(new BigDecimal(qv.getNtaxPrice()));
                    }
                    if (!StringUtils.isEmpty(qv.getAmount())) {
                        qcommodity.setAmount(new BigDecimal(qv.getAmount()));
                    }
                    qcommodity.setCreateTime(new Date());
                    log.info("导入数据" + qcommodity.toString());
                    //插入数据
                    qcommodityService.save(qcommodity);
                }
                return ResponseEntity.ok("{msg:'导入成功'}");
            /*} catch (Exception e) {
                log.error("导入数据失败!", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }*/

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}

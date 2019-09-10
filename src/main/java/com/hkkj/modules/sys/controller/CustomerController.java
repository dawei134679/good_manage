package com.hkkj.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.annotation.OperationLog;
import com.hkkj.common.base.controller.BaseController;
import com.hkkj.common.security.token.FormToken;
import com.hkkj.modules.sys.model.Customer;
import com.hkkj.modules.sys.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ldw
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController extends BaseController {

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private CustomerService customerService;

    /**
     * 分页查询客户列表
     *
     * @param pageNum   当前页码
     * @param name      用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param modelMap
     * @return
     */
    @RequiresPermissions("customer:list")
    @GetMapping
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String name, String startTime, String endTime, ModelMap modelMap) throws Exception {
        try {
            log.debug("分页查询客户列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, name, startTime, endTime);
            PageInfo<Customer> pageInfo = customerService.findPage(pageNum, PAGESIZE, name, startTime, endTime);
            log.info("分页查询客户列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("name", name);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BASE_PATH + "admin-customer-list";
    }


    /**
     * 跳转到客户添加页面
     *
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("customer:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap) {
        log.info("跳转到客户添加页面!");
        return BASE_PATH + "admin-customer-add";
    }

    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加客户")
    @RequiresPermissions("customer:create")
    @ResponseBody
    @PostMapping("/save")
    public ModelMap saveCustomer(Customer customer) {
        log.info("添加客户!" + customer.toString());
        ModelMap messagesMap = new ModelMap();
        customer.setCreateTime(new Date());
        customerService.save(customer);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 批量删除客户
     *
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除客户")
    @RequiresPermissions("customer:delete")
    @DeleteMapping(value = "/batch/{ids}")
    public ResponseEntity<String> deleteBatch(@PathVariable("ids") List<Object> ids) {
        try {
            log.debug("批量删除客户! ids = {}", ids);

            if (null == ids) {
                log.info("批量删除客户不存在! ids = {}", ids);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            customerService.deleteByCondition(Customer.class, "id", ids);
            log.info("批量删除客户成功! ids = {}", ids);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("批量删除客户失败! ids = {}, e = {}", ids, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到编辑客户页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("customer:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        Customer customer = customerService.findById(id);
        modelMap.put("customer", customer);
        return BASE_PATH + "admin-customer-edit";
    }

    /**
     * 修改客户
     *
     * @param customer
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "修改客户")
    @RequiresPermissions("customer:edit")
    @ResponseBody
    @PostMapping("/update")
    public ModelMap updateContent(Customer customer) {
        ModelMap messagesMap = new ModelMap();
        log.info("修改客户" + customer.toString());
        customer.setModifyTime(new Date());
        customerService.updateSelective(customer);
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "编辑成功!");
        return messagesMap;
    }
}

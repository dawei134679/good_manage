package com.hkkj.modules.quotation.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 报价单商品excel数据
 */

public class QcommodityVo extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String code;

    @ExcelProperty(index = 1)
    private String model;

    @ExcelProperty(index = 2)
    private String name;

    @ExcelProperty(index = 3)
    private String spareNo;

    @ExcelProperty(index = 4)
    private String units;

    @ExcelProperty(index = 5)
    private Integer num;

    @ExcelProperty(index = 6)
    private String price;

    @ExcelProperty(index = 7 )
    private String discount;

    @ExcelProperty(index = 8)
    private String taxrate;

    @ExcelProperty(index = 9)
    private String ntaxPrice;

    @ExcelProperty(index = 10)
    private String amount;

    @ExcelProperty(index = 11)
    private String productQuality;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpareNo() {
        return spareNo;
    }

    public void setSpareNo(String spareNo) {
        this.spareNo = spareNo;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public String getNtaxPrice() {
        return ntaxPrice;
    }

    public void setNtaxPrice(String ntaxPrice) {
        this.ntaxPrice = ntaxPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(String productQuality) {
        this.productQuality = productQuality;
    }

    @Override
    public String toString() {
        return "QcommodityVo{" +
                "code='" + code + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", spareNo='" + spareNo + '\'' +
                ", units='" + units + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", discount='" + discount + '\'' +
                ", taxrate='" + taxrate + '\'' +
                ", ntaxPrice=" + ntaxPrice +
                ", amount=" + amount +
                ", productQuality='" + productQuality + '\'' +
                '}';
    }
}
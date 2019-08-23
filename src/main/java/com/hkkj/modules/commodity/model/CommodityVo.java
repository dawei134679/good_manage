package com.hkkj.modules.commodity.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;



public class CommodityVo extends BaseRowModel  {

    @ExcelProperty(index = 0)
    private String code;

    @ExcelProperty(index = 1)
    private String model;

    @ExcelProperty(index = 2)
    private String brand;

    @ExcelProperty(index = 3)
    private String name;

    @ExcelProperty(index = 4)
    private String spareNo;

    @ExcelProperty(index = 5)
    private String specification;

    @ExcelProperty(index = 6)
    private String taxPrice;

    @ExcelProperty(index = 7)
    private String price;

    @ExcelProperty(index = 8)
    private String supplier;

    @ExcelProperty(index = 9)
    private String drawingNumber;

    @ExcelProperty(index = 10)
    private String size;

    @ExcelProperty(index = 11)
    private String quotedTime;

    @ExcelProperty(index = 12)
    private String content;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public void setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuotedTime() {
        return quotedTime;
    }

    public void setQuotedTime(String quotedTime) {
        this.quotedTime = quotedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommodityVo{" +
                "code='" + code + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", spareNo='" + spareNo + '\'' +
                ", specification='" + specification + '\'' +
                ", taxPrice='" + taxPrice + '\'' +
                ", price='" + price + '\'' +
                ", supplier='" + supplier + '\'' +
                ", drawingNumber='" + drawingNumber + '\'' +
                ", size='" + size + '\'' +
                ", quotedTime='" + quotedTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
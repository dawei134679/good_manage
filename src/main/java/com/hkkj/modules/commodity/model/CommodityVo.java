package com.hkkj.modules.commodity.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;



public class CommodityVo extends BaseRowModel  {

    @ExcelProperty(index = 0)
    private String brand;

    @ExcelProperty(index = 1)
    private String model;

    @ExcelProperty(index = 2)
    private String name;

    @ExcelProperty(index = 3)
    private String spareNo;

    @ExcelProperty(index = 4)
    private String drawingNumber;

    @ExcelProperty(index = 5)
    private String specification;

    @ExcelProperty(index = 6)
    private String taxPrice;

    @ExcelProperty(index = 7)
    private String price;

    @ExcelProperty(index = 8)
    private String supplier;

    @ExcelProperty(index = 9)
    private String quotedTime;

    @ExcelProperty(index = 10)
    private String content1;

    @ExcelProperty(index = 11)
    private String content2;

    @ExcelProperty(index = 12)
    private String content3;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public void setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
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

    public String getQuotedTime() {
        return quotedTime;
    }

    public void setQuotedTime(String quotedTime) {
        this.quotedTime = quotedTime;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    @Override
    public String toString() {
        return "CommodityVo{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", spareNo='" + spareNo + '\'' +
                ", drawingNumber='" + drawingNumber + '\'' +
                ", specification='" + specification + '\'' +
                ", taxPrice='" + taxPrice + '\'' +
                ", price='" + price + '\'' +
                ", supplier='" + supplier + '\'' +
                ", quotedTime='" + quotedTime + '\'' +
                ", content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                ", content3='" + content3 + '\'' +
                '}';
    }
}
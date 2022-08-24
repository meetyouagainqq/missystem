package com.javasm.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBasic implements Serializable {
    private Integer id;
    private String pname; //产品名称
    private Integer series; //产品系列 0代表测试产品 1代表中金收益宝 2安顺收益
    private Integer classification; //二级分类 0代表对冲基金 1代表其他基金 2 阳光私募 3 储蓄险
    private String mechanismName; //机构名称
    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private String openTime;
    private String currency; //币种
    private Integer status; //审核状态
    private String yield; //收益率
    private String reviewPerson;//审核人
    private String cycle; //认购周期
    private String managementRate;//基金管理费率
    private String subscriptionRate;//认购费率
    private BigDecimal amount;//起投金额
    private String method;//认购费收取方式
    private String redemptionPeriod;//赎回周期
    private BigDecimal startingAmount;//赎回起始金额
    private BigDecimal redemptionFee;//赎回费
    private String lockPeriod;//锁定期

    public ProductBasic(String pname, Integer classification, Integer status) {
        this.pname = pname;
        this.classification = classification;
        this.status = status;
    }

    public ProductBasic(String pname, Integer series, Integer classification, String mechanismName, String openTime, String currency, Integer status, String yield, String reviewPerson, String cycle, String managementRate, String subscriptionRate, BigDecimal amount, String method, String redemptionPeriod, BigDecimal startingAmount, BigDecimal redemptionFee, String lockPeriod) {
        this.pname = pname;
        this.series = series;
        this.classification = classification;
        this.mechanismName = mechanismName;
        this.openTime = openTime;
        this.currency = currency;
        this.status = status;
        this.yield = yield;
        this.reviewPerson = reviewPerson;
        this.cycle = cycle;
        this.managementRate = managementRate;
        this.subscriptionRate = subscriptionRate;
        this.amount = amount;
        this.method = method;
        this.redemptionPeriod = redemptionPeriod;
        this.startingAmount = startingAmount;
        this.redemptionFee = redemptionFee;
        this.lockPeriod = lockPeriod;
    }
}

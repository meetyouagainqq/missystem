package com.javasm.system.entity;

import lombok.*;

/**
 * @author: LinHai
 * @className: SeriesRemittance
 * @Description:
 * @date: 2022/8/24 21:26
 * @version: 0.1
 * @since: jdk11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeriesRemittance {
    //汇款信息弹窗

    private Integer sid;//产品系列id
    private String sname;//产品中文名
    private String sename;//产品英文名
    private String remittance;//汇款信息概括
    private String bankName;
    private String bankSwift;
    private String bankCode;
    private String cnapsNumber;
    private String bankArea;
    private String bankCity;
    private String payeeName;
    private String payeeAccount;
    private String payeeAddress;
    private String InformationOrganization;

    public SeriesRemittance(Integer sid, String sname, String sename, String remittance) {
        this.sid = sid;
        this.sname = sname;
        this.sename = sename;
        this.remittance = remittance;
    }
}

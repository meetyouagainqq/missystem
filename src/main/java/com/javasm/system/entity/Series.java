package com.javasm.system.entity;

import lombok.*;

import java.util.List;

/**
 * @author: LinHai
 * @className: MySeries
 * @Description:
 * @date: 2022/8/22 23:20
 * @version: 0.1
 * @since: jdk11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Series {
     private Integer sid;//产品系列id
     private String sname;//产品中文名
     private String sename;//产品英文名
     private String remittance;//汇款信息概括


     public Series(String sname) {
          this.sname = sname;
     }
}

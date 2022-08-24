package com.javasm.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCrease implements Serializable {
    private Integer id;
    private Integer basicId; // 关联的产品管理表id
    private String netWorth; // 单位净值
    private String growthRate;//累计增长率
    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private String baseTime; //净值基准日
}

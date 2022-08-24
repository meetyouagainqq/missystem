package com.javasm.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//基金产品类型
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
    private Integer classification;
    private String name;
}

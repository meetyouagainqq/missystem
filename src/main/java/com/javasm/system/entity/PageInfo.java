package com.javasm.system.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageInfo implements Serializable {
    private Integer page;
    private Integer pageSize;
    private Integer totalNum;


    public void getPageSize(Integer pageSize) {
    }
}

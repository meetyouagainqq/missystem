package com.javasm.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    private Integer id;
    private String pname;
    private Integer businessType;
    private String createPerson;
    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private String createTime;
    @JsonFormat(locale = "zh_CN", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private String updateTime;
    private Integer status;

    public Review(String pname, Integer status) {
        this.pname = pname;
        this.status = status;
    }
}

package com.javasm.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private Integer mid;
    private String menuname;
    private Integer pid;
    private String url;
    private String glyphicon;
    private List<Menu> subMenu;
    //显示上一级菜单列表的名称
    private String pname;
    private Integer versionid;
    public Menu(String menuname, Integer pid) {
        this.menuname = menuname;
        this.pid = pid;
    }
    public Menu(Integer mid,String menuname,Integer pid,String url,String glyphicon){
        this.mid=mid;
        this.menuname=menuname;
        this.pid=pid;
        this.url=url;
        this.glyphicon=glyphicon;
    }
}

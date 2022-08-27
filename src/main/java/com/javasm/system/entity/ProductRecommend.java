package com.javasm.system.entity;

import lombok.*;

/**
 * @author:Dai
 * @className:ProductRecommend
 * @description:
 * @date:2022/8/23 11:07
 * @version: 1.0
 * @since: jdk11
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRecommend {
    //产品推荐管理
    private String pname;//产品名称
    private Integer finer;//推荐度
    private Integer issue;//是否首发
    private Integer subscribe;//是否线上申购
    private Integer sort;//排序
    private Integer visible;//是否投顾端可见
    private String explain;//推荐理由
    private Integer id;//产品id


    public ProductRecommend(Integer finer, Integer issue, Integer subscribe,  Integer visible, String explain) {
        this.finer = finer;
        this.issue = issue;
        this.subscribe = subscribe;

        this.visible = visible;
        this.explain = explain;
    }

    public ProductRecommend(Integer pid,Integer finer, Integer issue,  Integer visible,Integer subscribe,  String explain) {
        this.finer = finer;
        this.issue = issue;
        this.subscribe = subscribe;
        this.visible = visible;
        this.explain = explain;
        this.id = pid;
    }


}

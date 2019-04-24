package com.jfhealthcare.modules.teach.entity;

import javax.persistence.*;

@Table(name = "teach_takephotos_items")
public class TeachTakePhotosItems {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 主表外键
     */
    @Column(name = "indexID")
    private Integer indexid;

    /**
     * 图片路径
     */
    private String path;

    /**
     * 排序
     */
    private Integer nindex;

    /**
     * 获取主键ID
     *
     * @return ID - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取主表外键
     *
     * @return indexID - 主表外键
     */
    public Integer getIndexid() {
        return indexid;
    }

    /**
     * 设置主表外键
     *
     * @param indexid 主表外键
     */
    public void setIndexid(Integer indexid) {
        this.indexid = indexid;
    }

    /**
     * 获取图片路径
     *
     * @return path - 图片路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置图片路径
     *
     * @param path 图片路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取排序
     *
     * @return nindex - 排序
     */
    public Integer getNindex() {
        return nindex;
    }

    /**
     * 设置排序
     *
     * @param nindex 排序
     */
    public void setNindex(Integer nindex) {
        this.nindex = nindex;
    }
}
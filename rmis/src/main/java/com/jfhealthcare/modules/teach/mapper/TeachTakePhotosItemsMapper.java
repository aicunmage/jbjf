package com.jfhealthcare.modules.teach.mapper;

import com.jfhealthcare.modules.teach.entity.TeachTakePhotosItems;
import com.jfhealthcare.tk.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachTakePhotosItemsMapper extends MyMapper<TeachTakePhotosItems> {

    List<String> queryPathByIndexId(@Param(value="id") List<String> id);
}
package com.jfhealthcare.modules.teach.service;

import com.jfhealthcare.modules.teach.entity.TeachTakePhotosIndex;

import java.util.List;

/**
 * Created By lzw
 * CreateDate 2018/09/07
 */

public interface TeachTakePhotosService {

    /**
     * 通过upper查询目录
     * @param upper
     * @return
     */
   List<TeachTakePhotosIndex> queryPhotosByUpper(Integer upper);


    /**
     * 根据accessionNum 查询投照示教图片路径
     * @param accessionNum
     * @return
     */
   List<String> queryPathByAccessionNum(String accessionNum);
}

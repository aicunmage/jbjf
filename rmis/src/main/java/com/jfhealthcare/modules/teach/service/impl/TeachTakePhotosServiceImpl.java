package com.jfhealthcare.modules.teach.service.impl;

import com.jfhealthcare.modules.business.mapper.ViewWorklistMapper;
import com.jfhealthcare.modules.teach.entity.TeachTakePhotosIndex;
import com.jfhealthcare.modules.teach.mapper.TeachTakePhotosIndexMapper;
import com.jfhealthcare.modules.teach.service.TeachTakePhotosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By lzw
 * CreateDate 2018/09/07
 */
@Slf4j
@Service
public class TeachTakePhotosServiceImpl implements TeachTakePhotosService {

    @Autowired
    TeachTakePhotosIndexMapper teachTakePhotosIndexMapper;
    @Autowired
    ViewWorklistMapper viewWorklistMapper;

    @Override
    public List<TeachTakePhotosIndex> queryPhotosByUpper(Integer upper) {
        TeachTakePhotosIndex index = new TeachTakePhotosIndex();
        index.setUpper(upper);
        return teachTakePhotosIndexMapper.select(index);
    }

    @Override
    public List<String> queryPathByAccessionNum(String accessionNum) {
        String path = viewWorklistMapper.queryPathByAccessionNum(accessionNum);
        List<String> pathList = new ArrayList<>();
        try {
            pathList = Arrays.asList(path.split(","));
        }
        catch (Exception e){
            log.error("查询图片路径错误！", e);
            pathList.add("");
        }
        return pathList;
    }


}

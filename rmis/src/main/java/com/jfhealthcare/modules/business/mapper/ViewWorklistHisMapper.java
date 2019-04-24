package com.jfhealthcare.modules.business.mapper;

import com.jfhealthcare.modules.apply.request.PrintWorklistRequest;
import com.jfhealthcare.modules.apply.response.PrintWorklistResponse;
import com.jfhealthcare.modules.business.entity.ViewWorklistHis;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;
import com.jfhealthcare.tk.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ViewWorklistHisMapper extends MyMapper<ViewWorklistHis> {

    List<ViewWorklistResponse> queryViewWorklist(ViewWorklistRequest viewWorklistRequest);

    List<Map<String, Object>> queryCountViewWorklist(ViewWorklistRequest vwlr);

    List<ViewWorklistResponse> queryOneViewWorklist(ViewWorklistRequest vwlr);
}
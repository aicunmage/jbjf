package com.jfhealthcare.modules.business.service;

import com.github.pagehelper.PageInfo;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.modules.business.entity.RepImageHis;
import com.jfhealthcare.modules.business.request.ViewWorklistRequest;
import com.jfhealthcare.modules.business.response.ViewWorklistResponse;

import java.util.List;
import java.util.Map;

public interface ViewWorklistHisService {
	PageInfo<ViewWorklistResponse> queryViewWorklist(ViewWorklistRequest viewWorklistRequest, LoginUserEntity loginUserEntity);

	List<RepImageHis> queryRepImageByRepUid(String repUid);

	void deleteRepImageByRepImageId(String repImageId);

	ViewWorklistResponse queryOneViewWorklist(String checkAccessionNum);

	String queryWebviewerUrlByAccessionNum(String checkAccessionNum);

	Map<String, String> queryBtnsByCheckAccessionNum(String checkAccessionNum, LoginUserEntity user);
}

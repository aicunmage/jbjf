package com.jfhealthcare.modules.apply.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.jfhealthcare.common.entity.CommonStaticValue;
import com.jfhealthcare.common.entity.LoginUserEntity;
import com.jfhealthcare.common.entity.MyPageInfo;
import com.jfhealthcare.common.enums.*;
import com.jfhealthcare.common.exception.RmisException;
import com.jfhealthcare.common.utils.*;
import com.jfhealthcare.common.validator.Assert;
import com.jfhealthcare.modules.apply.entity.ApplyImage;
import com.jfhealthcare.modules.apply.entity.ApplyWorklist;
import com.jfhealthcare.modules.apply.mapper.ApplyImageMapper;
import com.jfhealthcare.modules.apply.mapper.ApplySeriesMapper;
import com.jfhealthcare.modules.apply.mapper.ApplyWorklistMapper;
import com.jfhealthcare.modules.apply.request.ApplyWorklistRequest;
import com.jfhealthcare.modules.apply.request.PrintWorklistRequest;
import com.jfhealthcare.modules.apply.response.ApplyWorklistResponse;
import com.jfhealthcare.modules.apply.response.PrintWorklistResponse;
import com.jfhealthcare.modules.apply.service.ApplyWorklistService;
import com.jfhealthcare.modules.basics.AiData;
import com.jfhealthcare.modules.business.entity.*;
import com.jfhealthcare.modules.business.mapper.*;
import com.jfhealthcare.modules.system.entity.SysDictBodypart;
import com.jfhealthcare.modules.system.entity.SysDictDtl;
import com.jfhealthcare.modules.system.entity.SysDictScanmethod;
import com.jfhealthcare.modules.system.entity.SysOrganization;
import com.jfhealthcare.modules.system.mapper.RepGroupMapper;
import com.jfhealthcare.modules.system.mapper.SysDictBodypartMapper;
import com.jfhealthcare.modules.system.mapper.SysDictDtlMapper;
import com.jfhealthcare.modules.system.mapper.SysDictScanmethodMapper;
import com.jfhealthcare.modules.system.service.DistributedLock;
import com.jfhealthcare.modules.system.service.SysDictDtlService;
import com.jfhealthcare.modules.system.service.SysOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Service
public class ApplyWorklistServiceImpl implements ApplyWorklistService {

    @Value("${dcm.image.url}")
    private String startUrl;
    @Value("${ai.host}")
    private String aiHost;
    @Value("${ai.userName}")
    private String aiUserName;
    @Value("${ai.aiToDocName}")
    private String aiToDocName;

    @Autowired
    private ApplyWorklistMapper applyWorklistMapper;
    @Autowired
    private ViewWorklistMapper viewWorklistMapper;
    @Autowired
    private BusinChecklistIndexMapper businChecklistIndexMapper;
    @Autowired
    private BusinChecklistIndexHisMapper businChecklistIndexHisMapper;
    @Autowired
    private SysOrganizationService sysOrganizationServiceImpl;
    @Autowired
    private BusinPatientMapper businPatientMapper;
    @Autowired
    private RepRecordMapper repRecordMapper;
    @Autowired
    private RepImageMapper repImageMapper;
    @Autowired
    private ApplyImageMapper applyImageMapper;
    @Autowired
    private ApplySeriesMapper applySeriesMapper;
    @Autowired
    private SysDictBodypartMapper sysDictBodypartMapper;
    @Autowired
    private SysDictDtlMapper sysDictDtlMapper;
    @Autowired
    SysDictScanmethodMapper sysDictScanmethodMapper;
    @Autowired
    BusinChecklistItemsMapper businChecklistItemsMapper;
    @Autowired
    private SysDictDtlService sysDictDtlService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageInfo <ApplyWorklistResponse> queryApplyWorkList(ApplyWorklistRequest awlr) {
        if (awlr.getCheckDate() != null) {
            if (awlr.getStudyStartTime() == null || awlr.getStudyEndTime() == null) {
                List <Date> checkTime = DateUtils.getCheckTime(awlr.getCheckDate());
                if (!ObjectUtils.isEmpty(checkTime)) {
                    awlr.setStudyStartTime(checkTime.get(0));
                    awlr.setStudyEndTime(checkTime.get(1));
                }
            }
        }
        PageHelper.startPage(awlr.getPageNum(), awlr.getPageSize());
        List <ApplyWorklistResponse> applyWorklistResponses = applyWorklistMapper.queryApplyWorkList(awlr);
        PageInfo <ApplyWorklistResponse> pageInfo = new PageInfo <ApplyWorklistResponse>(applyWorklistResponses);
        return pageInfo;
    }


    @Override
    public PageInfo <PrintWorklistResponse> queryPrintWorklist(PrintWorklistRequest vwlr) {
        if (vwlr.getCheckDate() != null) {
            if (vwlr.getApplyStartTime() == null || vwlr.getApplyEndTime() == null) {
                List <Date> checkTime = DateUtils.getCheckTime(vwlr.getCheckDate());
                if (!ObjectUtils.isEmpty(checkTime)) {
                    vwlr.setApplyStartTime(checkTime.get(0));
                    vwlr.setApplyEndTime(checkTime.get(1));
                }
            }
        }
        MyPageInfo <PrintWorklistResponse> pageInfo = new MyPageInfo <PrintWorklistResponse>(null);
        /**
         * 打开状态  1  只查列表  ：返回列表数据
         * 所有状态  0  只查数量： 返回状态数量
         * 所有状态  2  数量  列表一起返回
         */
        if ("1".equals(vwlr.getIsOpen())) {
            pageInfo = getPrintList(vwlr);
        } else if ("0".equals(vwlr.getIsOpen())) {
            getStatusNum(vwlr, pageInfo);
        } else if ("2".equals(vwlr.getIsOpen())) {
            pageInfo = getPrintList(vwlr);
            getStatusNum(vwlr, pageInfo);
        }
        return pageInfo;
    }


    private void getStatusNum(PrintWorklistRequest vwlr, MyPageInfo <PrintWorklistResponse> pageInfo) {
        List <Map <String, Object>> statusnum = viewWorklistMapper.queryPrintCountWorklist(vwlr);
        pageInfo.setOtherValue(statusnum);
    }

    private MyPageInfo <PrintWorklistResponse> getPrintList(PrintWorklistRequest vwlr) {
        List <String> printStatusCodes = vwlr.getPrintStatusCodes();
        if (!CollectionUtils.isEmpty(printStatusCodes)) {
            List <String> status = new ArrayList <String>();
            for (String sta : printStatusCodes) {
                if (PrintStatusEnum.PENDING_DOING.getStatusCode().equals(sta)) {
                    status.add(CheckStatusEnum.PENDING_REPORT.getStatusCode());
                    status.add(CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
                    status.add(CheckStatusEnum.PENDING_TWO_REVIEW.getStatusCode());
                    status.add(CheckStatusEnum.PENDING_THREE_REVIEW.getStatusCode());
                    status.add(CheckStatusEnum.REPORTING.getStatusCode());
                    status.add(CheckStatusEnum.REVIEWING.getStatusCode());
                    status.add(CheckStatusEnum.ZANCUNING.getStatusCode());
                    status.add(CheckStatusEnum.PENGING_HUIZHENG_REPORT.getStatusCode());
                    status.add(CheckStatusEnum.PENGING_HUIZHENG_REVIEW.getStatusCode());
                } else if (PrintStatusEnum.COMPLETE_DOING.getStatusCode().equals(sta)) {
                    status.add(CheckStatusEnum.COMPLETE_REVIEW.getStatusCode());
                } else if (PrintStatusEnum.COMPLETE_PRINT.getStatusCode().equals(sta)) {
                    status.add(CheckStatusEnum.COMPLETE_PRINT.getStatusCode());
                } else if (PrintStatusEnum.COMPLETE_REFUSE.getStatusCode().equals(sta)) {
                    status.add(CheckStatusEnum.COMPLETE_REFUSE.getStatusCode());
                    status.add(CheckStatusEnum.COMPLETE_ABANDONED.getStatusCode());
                }
            }
            vwlr.setPrintStatusCodes(status);
        }
        PageHelper.startPage(vwlr.getPageNum(), vwlr.getPageSize());
        List <PrintWorklistResponse> vwls = viewWorklistMapper.queryPrintWorklist(vwlr);
        MyPageInfo <PrintWorklistResponse> pageInfo = new MyPageInfo <PrintWorklistResponse>(vwls);
        return pageInfo;
    }


    @Override
    public ApplyWorklistResponse queryApplyWorkListById(String checkNum) {
        ApplyWorklist selectByPrimaryKey = applyWorklistMapper.selectByPrimaryKey(checkNum);
        Assert.isNull(selectByPrimaryKey, "查询申请时未查到有效申请数据！账号：" + NameUtils.getLoginCode());
        ApplyWorklistResponse applyWorklistResponse = new ApplyWorklistResponse();
        TransferUtils.copyPropertiesIgnoreNull(selectByPrimaryKey, applyWorklistResponse);
        List <Map <String, Object>> l = applyWorklistMapper.queryInstanceByStudyUid(selectByPrimaryKey.getStudyUid());
        Example ex = new Example(BusinChecklistIndex.class);
        ex.createCriteria().andEqualTo("checkNum", selectByPrimaryKey.getCheckNum());
        ex.setOrderByClause("CRT_TIME desc");
        List <BusinChecklistIndex> businChecklistIndexs = businChecklistIndexMapper.selectByExample(ex);
        if (CollectionUtils.isNotEmpty(businChecklistIndexs)) {
            BusinChecklistIndex bcli = businChecklistIndexs.get(0);
            applyWorklistResponse.setDescribeBq(bcli.getDescribeBq());
            applyWorklistResponse.setCheckPastIllness(bcli.getPastIllness());
            applyWorklistResponse.setCheckPtnSource(bcli.getPtnSource());
            applyWorklistResponse.setCheckJzFlag(bcli.getJzFlag());
        }
        // luzw 2019-03-05增加检查部位和方法
        BusinChecklistItems checklistItems = businChecklistItemsMapper.queryByCheckNum(checkNum);
        List<Map> bodyPartInfos = new ArrayList<Map>();
        List<Map> bodyAligns = new ArrayList<Map>();
        List<Map> summaries = new ArrayList<Map>();
        //获取上一次申请的检查部位检查方法,如果是第一次申请，则不返回
        if (checklistItems != null) {
            String accessionNum = businChecklistItemsMapper.queryByCheckNum(checkNum).getAccessionNum();
            Example busEx = new Example(BusinChecklistItems.class);
            busEx.createCriteria().andEqualTo("accessionNum", accessionNum);
            List<BusinChecklistItems> itemsList = businChecklistItemsMapper.selectByExample(busEx);
            for (BusinChecklistItems items : itemsList) {
                Map bodyPartMap = new HashMap();
                bodyPartMap.put("id", items.getPartsCode());
                Integer leftRight = sysDictBodypartMapper.selectByPrimaryKey(Integer.valueOf(items.getPartsCode())).getLeftRight();
                bodyPartMap.put("left_right", leftRight);
                bodyPartInfos.add(bodyPartMap);

                Map bodyAlignsMap = new HashMap();
                bodyAlignsMap.put("partId", items.getBodyAlignCode());
                bodyAligns.add(bodyAlignsMap);

                Map summariesMap = new HashMap();
                summariesMap.put("summaryId", items.getSummaryCode());
                summaries.add(summariesMap);
            }
        }
        applyWorklistResponse.setImageUrls(l);
        applyWorklistResponse.setUrlStart(startUrl);
        applyWorklistResponse.setBodyPartInfos(bodyPartInfos);
        applyWorklistResponse.setBodyAligns(bodyAligns);
        applyWorklistResponse.setSummaries(summaries);
        return applyWorklistResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApplyWorkListToView(ApplyWorklistRequest applyWorklistRequest, LoginUserEntity loginUserEntity) {
        //申请之后先通过checkNum锁该条数据
        Example ex = new Example(ApplyWorklist.class);
        ex.createCriteria().andEqualTo("checkNum", applyWorklistRequest.getCheckNum());
        ApplyWorklist worklist = applyWorklistMapper.selectByPrimaryKey(applyWorklistRequest.getCheckNum());
        //锁该行数据 乐观锁
        int num = applyWorklistMapper.updateByExampleSelective(worklist, ex);
        if (num == 0) {
            return;//不可操作   返回
        }
        String logincode = loginUserEntity.getSysOperator().getLogincode();
        SysOrganization org = sysOrganizationServiceImpl.querySingleSysOrganization(loginUserEntity.getSysOperatorDtl().getOrgId());
        String logseries = "报告申请初始化-病人姓名：" + applyWorklistRequest.getPtnName() + ",-申请机构：" + org.getName();
        log.info(logseries + ":=========初始化开始===========");
        ApplyWorklist applyWorklist = applyWorklistMapper.selectByPrimaryKey(applyWorklistRequest.getCheckNum());
        Assert.isNull(applyWorklist, "提交申请时未查到有效申请数据！账号：" + logincode);

        //2.初始化viewworklist相关数据
        List <String> instanceUids = applyWorklistRequest.getInstanceUids();
        Assert.isNull(instanceUids, "未选择影像，无法初始化！");

        //数据初始化-----系列
        //检查信息初始化
        String checkNum = applyWorklistRequest.getCheckNum();
        Assert.isBlank(checkNum, "检查单号不能为空！");

        BusinChecklistIndex bizindex = new BusinChecklistIndex();
        bizindex.setStatus(CheckStatusEnum.PENDING_REPORT.getStatus());
        bizindex.setStatusCode(CheckStatusEnum.PENDING_REPORT.getStatusCode());
        bizindex.setCheckNum(checkNum);
        bizindex.setAccessionNumberDcm(applyWorklist.getAccessionNumber());//原dcm流水号
        bizindex.setDescribeBq(applyWorklistRequest.getDescribeBq());//病史描述
       /* bizindex.setParts(applyWorklistRequest.getBodyPart());//检查部位
        bizindex.setSummary(applyWorklistRequest.getSummary());//检查方法*/
        bizindex.setExam(applyWorklistRequest.getExam());
        bizindex.setApplyDoc(loginUserEntity.getSysOperator().getName());//申请医生
        bizindex.setApplyDocCode(logincode);//申请医生账号
        bizindex.setApplyHosp(org.getName());
        bizindex.setApplyHospCode(loginUserEntity.getSysOperatorDtl().getOrgId());
        bizindex.setJzFlag(ObjectUtils.isEmpty(applyWorklistRequest.getCheckJzFlag()) ? false : applyWorklistRequest.getCheckJzFlag());
        bizindex.setPtnSource(applyWorklistRequest.getCheckPtnSource());//患者来源
        bizindex.setPastIllness(applyWorklistRequest.getCheckPastIllness());//既往史
        bizindex.setImgNum(instanceUids.size());
        bizindex.setType("DR");
        bizindex.setTypeCode("3406");
        bizindex.setIsdelete(false);
        log.info(logseries + ":=========检查信息初始化完成===========");
        //诊断报告初始化
        RepRecord repRecord = new RepRecord();
        repRecord.setRepUid(getAccessionNum());
        repRecord.setPatId(checkNum);
        repRecord.setPatAge(applyWorklistRequest.getPtnAge());
        if (StringUtils.isNotEmpty(applyWorklistRequest.getPtnAgeUnitCode())) {
            SysDictDtl dictDtlForAge = sysDictDtlService.queryDictDtlById(applyWorklistRequest.getPtnAgeUnitCode());
            repRecord.setAgeUnit(dictDtlForAge.getOthervalue());
            repRecord.setAgeUnitCode(applyWorklistRequest.getPtnAgeUnitCode());
        }

        log.info(logseries + ":=========诊断报告初始化完成===========");
        //患者信息初始化
        BusinPatient bp = new BusinPatient();
        bp.setPatId(checkNum);
        bp.setPtnIdDcm(applyWorklist.getPtnIdDcm());//原dcm患者id
        bp.setName(applyWorklistRequest.getPtnName());
        bp.setSex(applyWorklistRequest.getSex());
        bp.setSexCode(applyWorklistRequest.getSexCode());
        bp.setIsdelete(false);
        BusinPatient buspat = businPatientMapper.selectByPrimaryKey(checkNum);
        boolean isBPNeed = false;
        if (!ObjectUtils.isEmpty(buspat)) {
            isBPNeed = true;
        }
        log.info(logseries + ":=========患者初始化完成===========");
        //报告贴图初始化
        List <RepImage> repImages = new ArrayList <RepImage>();
        List <Map <String, Object>> l = applyWorklistMapper.queryInstanceByInstanceUid(instanceUids);

        if (CollectionUtils.isNotEmpty(l)) {
            try {
                String aiParameter = null;
                int i = 0;
                for (Map <String, Object> map : l) {
                    RepImage repImage = new RepImage();
                    repImage.setRepUid(repRecord.getRepUid());
                    repImage.setImgPage((String) map.get("imageUrl"));
                    repImage.setNumber(i);
                    repImage.setIsdelete(CommonStaticValue.IS_NOT_DELETE);
                    repImages.add(repImage);
                    String jpgurl = "jpgurl=" + URLEncoder.encode(startUrl + map.get("imageUrl"), "UTF-8");
                    aiParameter = StringUtils.isEmpty(aiParameter) ? jpgurl : aiParameter + "&" + jpgurl;
                    i++;
                }
                log.info(logseries + ":=========报告贴图初始化完成===========");
                //AI 初始化
                long startTime = System.currentTimeMillis();
                HttpClientUtils instance = HttpClientUtils.getInstance();

                String httpGet = instance.httpGetByWaitTime(StringUtils.trim(aiHost) + aiParameter, 10000, 20000);
                log.info(logseries + "=====Ai调用地址:{}", StringUtils.trim(aiHost) + aiParameter);
                if (StringUtils.isNotBlank(httpGet)) {
                    log.info(logseries + "=====Ai调用返回:{}", httpGet);
                    AiData aiData = JSON.parseObject(httpGet, AiData.class);
//					boolean aiFlag=false;//确定ai是否要转给医师
                    String aiFlag = null;
                    //AI转报告
                    if (ReportAiEnum.TOREPORTE.getAiStatusCode().equals(aiData.getReportStatus())) {
                        bizindex.setStatusAi(ReportAiEnum.TOREPORTE.getAiStatus());
                        bizindex.setStatusAiCode(ReportAiEnum.TOREPORTE.getAiStatusCode());
                        aiFlag = "report";
                        //转审核
                    } else if (ReportAiEnum.TOREVIEWE.getAiStatusCode().equals(aiData.getReportStatus())) {
                        bizindex.setStatusAi(ReportAiEnum.TOREVIEWE.getAiStatus());
                        bizindex.setStatusAiCode(ReportAiEnum.TOREVIEWE.getAiStatusCode());
                        bizindex.setStatus(CheckStatusEnum.PENDING_ONE_REVIEW.getStatus());
                        bizindex.setStatusCode(CheckStatusEnum.PENDING_ONE_REVIEW.getStatusCode());
                        bizindex.setReportDr(aiUserName);
                        bizindex.setReportTime(new Date());
                        aiFlag = "audit";
                        //转分片
                    } else if (ReportAiEnum.TOFENPIAN.getAiStatusCode().equals(aiData.getReportStatus())) {
                        bizindex.setStatusAi(ReportAiEnum.TOFENPIAN.getAiStatus());
                        bizindex.setStatusAiCode(ReportAiEnum.TOFENPIAN.getAiStatusCode());
                        //ai拒绝
                    } else if (ReportAiEnum.AIREFUSE.getAiStatusCode().equals(aiData.getReportStatus())) {
                        bizindex.setStatusAi(ReportAiEnum.AIREFUSE.getAiStatus());
                        bizindex.setStatusAiCode(ReportAiEnum.AIREFUSE.getAiStatusCode());
                        bizindex.setStatus(CheckStatusEnum.PENDING_REPORT.getStatus());
                        bizindex.setStatusCode(CheckStatusEnum.PENDING_REPORT.getStatusCode());
                        //若AI返回结果不正确，ai状态为未处理
                    } else {
                        bizindex.setStatusAi(ReportAiEnum.UNTREATED.getAiStatus());
                        bizindex.setStatusAiCode(ReportAiEnum.UNTREATED.getAiStatusCode());
                        bizindex.setStatus(CheckStatusEnum.PENDING_REPORT.getStatus());
                        bizindex.setStatusCode(CheckStatusEnum.PENDING_REPORT.getStatusCode());
                    }
                    //默认未参与
                    if (StringUtils.isNotBlank(aiData.getImging())) {
                        repRecord.setFinding1(aiData.getImging());
                    }
                    if (StringUtils.isNotBlank(aiData.getDiagnosisOpinion())) {
                        repRecord.setImpression1(aiData.getDiagnosisOpinion());
                    }

                    //AI 对应的转医师相关业务
                    String aistatus = redisUtils.get(RedisEnum.AIBTNSTATUS.getValue());
                    if (StringUtils.isEmpty(aistatus)) {
                        aistatus = "0";
                        redisUtils.set(RedisEnum.AIBTNSTATUS.getValue(), aistatus, RedisUtils.NOT_EXPIRE);
                    }
                    if (StringUtils.isNotBlank(aiFlag) && StringUtils.equals("1", aistatus)) {
                        if (StringUtils.equals("report", aiFlag)) {
                            bizindex.setStatus(CheckStatusEnum.REPORTING.getStatus());
                            bizindex.setStatusCode(CheckStatusEnum.REPORTING.getStatusCode());
                            bizindex.setReportDr(aiToDocName);
                            bizindex.setReportTime(new Date());
                        } else {
                            bizindex.setStatus(CheckStatusEnum.REVIEWING.getStatus());
                            bizindex.setStatusCode(CheckStatusEnum.REVIEWING.getStatusCode());
                            bizindex.setAuditDr(aiToDocName);
                            bizindex.setAuditTime(new Date());
                        }
                    }
                    long endTime = System.currentTimeMillis();
                    log.info(logseries + ":=========AI申请用时：{}===========", endTime - startTime);
                } else {
                    throw new RmisException("AI请求连接超时或处理超时");
                }
            } catch (Exception e) {
                bizindex.setStatusAi(ReportAiEnum.UNTREATED.getAiStatus());
                bizindex.setStatusAiCode(ReportAiEnum.UNTREATED.getAiStatusCode());
                bizindex.setStatus(CheckStatusEnum.PENDING_REPORT.getStatus());
                bizindex.setStatusCode(CheckStatusEnum.PENDING_REPORT.getStatusCode());
                if ("AI请求连接超时或处理超时".equals(e.getMessage())) {
                    log.error("AI初始化失败！", "AI请求连接超时或处理超时");
                } else {
                    log.error("AI初始化失败！", e);
                }

            }
        }
        //增加部位明细表的写入
        List <Map> bodyPartInfos = applyWorklistRequest.getBodyPartInfos();
        List <Map> bodyAligns = applyWorklistRequest.getBodyAligns();
        List <Map> summaries = applyWorklistRequest.getSummaries();
        List <BusinChecklistItems> itemsInfos = null;
        Map bodyMap = new HashMap();
        if (bodyPartInfos != null && bodyAligns != null && summaries != null && bodyPartInfos.size() == bodyAligns.size() && bodyPartInfos.size() == summaries.size()) {
            itemsInfos = getItemsInfoByIds(bodyPartInfos, bodyAligns, summaries, logincode);
            bodyMap = transferBodyPart(bodyPartInfos);
        } else {
            log.warn("页面传入的部位数组不合法，不会写入明细表");
        }
        doFinishForWorkList(isBPNeed, bizindex, bp, repRecord, repImages, applyWorklist, itemsInfos,bodyMap);
        log.info(logseries + ":=========初始化结束===========");

        //1.更新申请数据，生成检查单号
        log.info(logseries + ":=========状态更新及申请列表信息===========");
        applyWorklist.setPtnName(applyWorklistRequest.getPtnName());
        applyWorklist.setSex(applyWorklistRequest.getSex());
        applyWorklist.setSexCode(applyWorklistRequest.getSexCode());
        applyWorklist.setPtnAge(applyWorklistRequest.getPtnAge());
        applyWorklist.setPtnAgeUnit(applyWorklistRequest.getPtnAgeUnit());
        applyWorklist.setPtnAgeUnitCode(applyWorklistRequest.getPtnAgeUnitCode());
        applyWorklist.setSummary(applyWorklistRequest.getSummary());
        applyWorklist.setExam(applyWorklistRequest.getExam());
        //更新检查部位
        String bodyParts = transferBodyPart(applyWorklistRequest.getBodyPartInfos()).get("bodyParts").toString();
        applyWorklist.setBodyPart(bodyParts);
        applyWorklist.setApplyDoc(logincode);
        applyWorklist.setApplyStatus(ApplyStatusEnum.COMPLETE_APPLY.getStatus());
        applyWorklist.setApplyStatusCode(ApplyStatusEnum.COMPLETE_APPLY.getStatusCode());
        applyWorklistMapper.updateByPrimaryKeySelective(applyWorklist);
    }

    private void doFinishForWorkList(boolean isBPNeed, BusinChecklistIndex bizindex, BusinPatient bp,
                                     RepRecord repRecord, List <RepImage> repImages, ApplyWorklist applyWorklist,
                                     List<BusinChecklistItems> itemsInfos,Map bodyMap) {
        //获取锁成功业务代码
        String checkNum = applyWorklist.getCheckNum();
        Example ex = new Example(BusinChecklistIndex.class);
        ex.createCriteria().andEqualTo("checkNum", checkNum);
        int countIndex = businChecklistIndexMapper.selectCountByExample(ex);
        int countIndexHis = businChecklistIndexHisMapper.selectCountByExample(ex);
        String accessNum = checkNum + (countIndex + countIndexHis + 1);

        bizindex.setAccessionNum(accessNum);
        bizindex.setParts(bodyMap.get("bodyParts").toString());
        bizindex.setPartsCode(bodyMap.get("bodyPartIds").toString());
        bizindex.setCrtTime(new Date());
        bizindex.setApplyTime(new Date());

        businChecklistIndexMapper.insertSelective(bizindex);
        if (isBPNeed) {//不为空 更新
            bp.setCrtTime(new Date());
            businPatientMapper.updateByPrimaryKeySelective(bp);
        } else {
            businPatientMapper.insert(bp);
        }
        repRecord.setAccessionNum(accessNum);
        repRecordMapper.insertSelective(repRecord);
        for (RepImage repImage : repImages) {
            repImageMapper.insertSelective(repImage);
        }
        // 写入部位明细表
        if (itemsInfos != null && itemsInfos.size() > 0) {
            // 根据accession_num获取最大值number_id
            BusinChecklistItems businChecklistItems = new BusinChecklistItems();
            businChecklistItems.setAccessionNum(accessNum);
            Integer numberId = businChecklistItemsMapper.selectCount(businChecklistItems);
            for (BusinChecklistItems item : itemsInfos) {
                numberId++;
                item.setNumberId(numberId);
                item.setAccessionNum(accessNum);
                businChecklistItemsMapper.insertSelective(item);
            }
        }
    }

    private static String getAccessionNum() {
        String accessionNum = DateUtils.dateToString(new Date(), DateUtils.patternF) + "-" + RandomStringUtils.randomNumeric(3);
        return accessionNum;
    }

    @Override
    public List <Map <String, Object>> queryPrintWorkListStatus(PrintWorklistRequest printWorklistRequest) {
        PrintWorklistRequest pwlr = new PrintWorklistRequest();
        if (StringUtils.isNotBlank(printWorklistRequest.getApplyOrg())) {
            pwlr.setApplyOrg(printWorklistRequest.getApplyOrg());
        }
        List <Map <String, Object>> statusnum = viewWorklistMapper.queryPrintCountWorklist(pwlr);
        return statusnum;
    }

    @Override
    public String queryApplyWorkListToRemind(String checkNum) {
        String remind = viewWorklistMapper.queryApplyWorkListToRemind(checkNum);
        return StringUtils.isEmpty(remind) || "0".equals(remind) ? "0" : "1";
    }

    @Override
    public String queryApplyWorkToCheckImageByUids(List <String> instanceUids) {
        Example example = new Example(ApplyImage.class);
        example.createCriteria().andIn("instanceUid", instanceUids).andEqualTo("imgaeDeleted", true);
        List <ApplyImage> applyImages = applyImageMapper.selectByExample(example);
        return CollectionUtils.isEmpty(applyImages) ? "0" : "1";
    }

    /***
     * 获取部位相关信息
     * @param bodyPartInfos
     * @param bodyAligns
     * @param summaries
     * @return
     */
    public List <BusinChecklistItems> getItemsInfoByIds(List <Map> bodyPartInfos, List <Map> bodyAligns, List <Map> summaries, String logincode) {
        List <BusinChecklistItems> itemsInfos = new ArrayList <>();
        for (int i = 0; i < bodyPartInfos.size(); i++) {
            //部位
            BusinChecklistItems businChecklistItems = new BusinChecklistItems();
            String bodyPartId = bodyPartInfos.get(i).get("id").toString();
            SysDictBodypart sysDictBodypart = sysDictBodypartMapper.selectByPrimaryKey(Integer.valueOf(bodyPartId));
            businChecklistItems.setPartsCode(bodyPartId);
            businChecklistItems.setPartsEn(sysDictBodypart.getEnName());
            businChecklistItems.setParts(sysDictBodypart.getName());
            //左右
            List <String> bodyAlignIds = (ArrayList) bodyAligns.get(i).get("partId");
            String alignIdStr = "";
            String alignNameStr = "";
            for (int j = 0; j < bodyAlignIds.size(); j++) {
                alignIdStr += bodyAlignIds.get(j);
                SysDictDtl sysDictDtl = new SysDictDtl();
                sysDictDtl.setCode("bodyAlign");
                sysDictDtl.setName(bodyAlignIds.get(j));
                sysDictDtl.setIsdelete(Boolean.FALSE);
                List <SysDictDtl> sysDictDtls = sysDictDtlMapper.select(sysDictDtl);
                alignNameStr += sysDictDtls.get(0).getOthervalue();
                if (j < bodyAlignIds.size() - 1) {
                    alignIdStr += "|";
                    alignNameStr += "|";
                }
            }
            businChecklistItems.setBodyAlignCode(alignIdStr);
            businChecklistItems.setBodyAlign(alignNameStr);
            /*//方法 ,检查方法多选，特殊处理
            List<Integer> summaryId = (List<Integer>) summaries.get(i).get("summaryId");
            List<String> scanNameList = new ArrayList<>();
            for (Integer id : summaryId) {
                SysDictScanmethod sysDictScanmethod = sysDictScanmethodMapper.selectByPrimaryKey(id);
                scanNameList.add(sysDictScanmethod.getName());
            }
            businChecklistItems.setSummaryCode(Joiner.on(",").join(summaryId));
            businChecklistItems.setSummary(Joiner.on(",").join(scanNameList));*/
            //方法
            if (summaries.get(i).get("summaryId") != null && summaries.get(i).get("summaryId") != "") {
                //将summaryId字符串转化为list
                List<String> summaryIdList = Arrays.asList(summaries.get(i).get("summaryId").toString().split(","));
                List<String> summaryNameList = new ArrayList<String>();
                for (String summaryId : summaryIdList) {
                    Integer id = Integer.valueOf(summaryId);
                    SysDictScanmethod sysDictScanmethod = sysDictScanmethodMapper.selectByPrimaryKey(id);
                    summaryNameList.add(sysDictScanmethod.getName());
                }
                businChecklistItems.setSummaryCode(summaries.get(i).get("summaryId").toString());
                businChecklistItems.setSummary(Joiner.on(",").join(summaryNameList));
            }

            businChecklistItems.setIsdelete(Boolean.FALSE);
            businChecklistItems.setCrtTime(new Date());
            businChecklistItems.setCrtUser(logincode);

            itemsInfos.add(businChecklistItems);
        }
        return itemsInfos;
    }

    /**
     * 检查部位拼接
     *
     * @param bodyPartInfos
     * @return
     */
    @Override
    public Map transferBodyPart(List<Map> bodyPartInfos) {
        List<String> bodyList = new ArrayList<String>();
        List<String> bodyIdList = new ArrayList<String>();
        for (Map bodyPartInfo : bodyPartInfos) {
            SysDictBodypart dictBodyPart = sysDictBodypartMapper.selectByPrimaryKey(Integer.valueOf(bodyPartInfo.get("id").toString()));
            bodyList.add(dictBodyPart.getName());
            bodyIdList.add(bodyPartInfo.get("id").toString());
        }
        String bodyParts = Joiner.on(",").join(bodyList);
        String bodyPartIds = Joiner.on(",").join(bodyIdList);
        Map map = new HashMap();
        map.put("bodyParts", bodyParts);
        map.put("bodyPartIds", bodyPartIds);
        return map;
    }
}

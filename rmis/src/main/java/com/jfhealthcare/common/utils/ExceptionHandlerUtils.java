package com.jfhealthcare.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xujinma
 */
@Slf4j
public class ExceptionHandlerUtils {
	/**
	 * 各种异常自定义
	 * @param e
	 * @throws Exception 
	 */
	public static boolean handerException(Exception e)  {
		log.info("**********ExceptionHandlerUtils start **********");
		
		if(e instanceof DuplicateKeyException) {
			log.info("**********DuplicateKeyException**********");
			if(StringUtils.contains(e.getMessage(), "apply_study") || StringUtils.contains(e.getMessage(), "apply_series")) {
				log.info("apply_study 或者 apply_series 主键已存在异常！不做处理！");
			}
		}else if(e instanceof MySQLIntegrityConstraintViolationException) {
			log.info("**********MySQLIntegrityConstraintViolationException**********");
		}else {
			//如果都不匹配   返回true   抛出异常
			return true;
		}
		log.info("**********ExceptionHandlerUtils end **********");
		return false; //已做处理   返回false  不需要再处理
	}
}

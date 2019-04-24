package com.jfhealthcare;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jfhealthcare.common.utils.HttpClientUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestAiDemo {
	
	public static void main(String[] args) throws Exception {
//		boolean numeric = StringUtils.isNumeric("-23123");
//		System.out.println(numeric);
//		String url ="http://172.19.148.212:8915/diagnose?jpgurl=http%3A%2F%2Fv2.jfhealthcare.cn%2Fv1%2Fpicl%2Faets%2Fpiclarc%2Fwado%3FrequestType%3DWADO%26contentType%3Dimage%2Fjpeg%26studyUID%3D1.2.276.0.7230010.3.1.2.1445917447.5004.1529909766.6%26seriesUID%3D1.2.276.0.7230010.3.1.3.1445917447.5004.1529909902.8%26objectUID%3D1.2.276.0.7230010.3.1.4.1445917447.5004.1529909902.7";
//		
//		String html="<p>1、片示<span style='color: rgb(0, 0, 255);'>髌骨多为二分髌骨，请结合临床以除外骨折可能，必要时CT检查；</span></p><p><span style='color: rgb(0, 0, 255);'>2、</span> 片示足部跖、趾骨未见明显骨折及脱位，请短期（7天）内复查，如症状持续或加重，行CT进一步检查以除外不全或隐匿性骨折。</p>";
//		String encode = URLEncoder.encode(html,"UTF-8");
//		String url ="http://180.167.46.105/rlm/check_api?html="+encode;
//		System.out.println("===================");
//		System.out.println(url);
//		System.out.println("===================");
//		HttpClientUtils instance = HttpClientUtils.getInstance();
//		String httpGet = instance.httpGetByWaitTime(url, 10000, 20000);
//		System.out.println(httpGet);
		
//		StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));  
//        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();  
//        HttpHeaders headers = new HttpHeaders();  
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");  
//        headers.setContentType(type);  
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); 
        
		
//		HttpClientUtils instance = HttpClientUtils.getInstance();
//		String httpGet = instance.httpGetByWaitTime(url, 10000, 20000);
//		System.out.println(httpGet);
		
//		RestTemplate rt=new RestTemplate();
//		rt.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
//		String body = forEntity.getBody();
//		System.out.println(body);
//		System.out.println(getAgeWithNotStartZero("000012"));
//		System.out.println(getAgeWithNotStartZero("120"));
		
		String s="http://172.19.148.212:8915/diagnose?jpgurl=http%3A%2F%2Fv2.jfhealthcare.cn%2Fv1%2Fpicl%2Faets%2Fpiclarc%2Fwado%3FrequestType%3DWADO%26contentType%3Dimage%2Fjpeg%26studyUID%3D1.2.276.0.7230010.3.1.2.1445917447.5004.1529909766.6%26seriesUID%3D1.2.276.0.7230010.3.1.3.1445917447.5004.1529909902.8%26objectUID%3D1.2.276.0.7230010.3.1.4.1445917447.5004.1529909902.7";
		HttpClientUtils instance = HttpClientUtils.getInstance();
		String httpGet = instance.httpGetByWaitTime(s, 10000, 20000);
		System.out.println(httpGet);
		
		
	}
	
	
	private static String getAgeWithNotStartZero(String ptnAge) {
		if(StringUtils.isNotEmpty(ptnAge)) {
			Matcher matcher = Pattern.compile("[1-9]").matcher(ptnAge);
			if(matcher.find()) {
				return ptnAge.substring(matcher.start());
			}else {
				return "0";
			}
		}
		return "0";
	}
}

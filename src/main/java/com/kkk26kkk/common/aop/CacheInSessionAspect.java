package com.kkk26kkk.common.aop;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CacheInSessionAspect {
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	
	@Around("@annotation(com.kkk26kkk.common.aop.CacheInSession) && @ annotation(target)")
	public Object getCachedObject(ProceedingJoinPoint joinPoint, CacheInSession target) throws Throwable {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Object returnObject;

		String cacheKey = target.name();
		final String type = target.type();
		Class<?> c = Class.forName(type);
		String[] keys = StringUtils.split(target.key(), ",");
		List<String> keyList = Arrays.asList(keys);
		Object[] arguments = joinPoint.getArgs();
		for(Object argument : arguments) {
			if(!StringUtils.equals(type, argument.getClass().getName())) {
				continue;
			}
			for(String key : keyList) {
				final String methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
				Method method = c.getMethod(methodName);
	            Object value = method.invoke(argument);
	            cacheKey += ":" + String.valueOf(value);
			}
			break;
	    }

		Date curDate = new Date();
		String expireDate = (String) session.getAttribute("expire" + cacheKey);
		if(!expiresDate(curDate, expireDate)) {
			returnObject = session.getAttribute(cacheKey);
			if(null != returnObject) {
				return returnObject;
			}
		}

		returnObject = joinPoint.proceed();
		String ttl = target.ttl();
		Date expireTime = DateUtils.addSeconds(curDate, Integer.parseInt(ttl));
	
		expireDate = fdf.format(expireTime);
		session.setAttribute(cacheKey, returnObject);
		session.setAttribute("expire" + cacheKey, expireDate);

		return returnObject;
	}
	
	private boolean expiresDate(Date curTime, String expireDate) {
		if(null == expireDate) {
			return true;
		}
		
		try {
			Date expireTime = DateUtils.parseDate(expireDate, fdf.getPattern());
			
			int expire = DateUtils.truncatedCompareTo(curTime, expireTime, Calendar.SECOND);
			
			if(expire > 0) {
				return true;
			}
		} catch (ParseException e) {
			return true;
		}
		
		return false;
	}
}

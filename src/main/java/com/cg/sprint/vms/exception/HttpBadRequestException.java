package com.cg.sprint.vms.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpBadRequestException 
{
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value= {Exception.class})
	protected ErrorInfo handleConflict(Exception ex,HttpServletRequest req)
	{
		String bor=ex.getMessage();
		String url=req.getRequestURL().toString();
		return new ErrorInfo(url,bor);
	}
	
}

package controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import entities.TaxRecord;


@ControllerAdvice
public class GlobalExceptionHandlerMethods {
	private Logger logger = Logger.getLogger(ExceptionHandler.class);

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=RuntimeException.class)
	public ModelAndView handleRuntimeException(RuntimeException e){
		logger.debug("Entering handleRuntimeException method.");
		ModelAndView mav = new ModelAndView("propertytaxformpage");
		mav.addObject("command", new TaxRecord());
		mav.addObject("exceptionmessage",e.getMessage());
		logger.info("Exiting handleRuntimeException method.");
		return mav;
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public ModelAndView handleException(Exception e){
		logger.debug("Entering handleException method.");
		ModelAndView mav = new ModelAndView("Exception");
		mav.addObject("exceptionmessage",e.getMessage());
		logger.info("Exiting handleException method.");
		return mav;
	}
	
	
}

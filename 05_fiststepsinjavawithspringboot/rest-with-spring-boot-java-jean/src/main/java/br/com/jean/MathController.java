package br.com.jean;

import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jean.exceptions.UnsupportedMathOperationException;
import jakarta.websocket.server.PathParam;

@RestController
public class MathController {
	
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sun/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double sum(
			@PathVariable (value = "numberone")String numberone , 
			@PathVariable(value = "numbertwo")String numbertwo) throws NumberFormatException {
		
		if(!isNumber(numberone) || !isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
		
		return convertToDouble (numberone) + convertToDouble(numbertwo);
		
	}
	
	@RequestMapping(value = "sub/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double sub (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		if(!isNumber(numberone) || !isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
		
		return convertToDouble(numberone)- convertToDouble(numbertwo);
		
	}
	@RequestMapping(value = "multi/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double multi (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		if(!isNumber(numberone) || !isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
		
		return convertToDouble(numberone)* convertToDouble(numbertwo);
		
	}
	
	@RequestMapping(value = "div/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double div (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		if(!isNumber(numberone) || !isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		else if(convertToDouble(numberone)== 0 || convertToDouble(numbertwo)== 0) {
			
			throw new UnsupportedMathOperationException("The numbers should different zero. ");
		}
		
		
		return convertToDouble(numberone)/ convertToDouble(numbertwo);
		
	}
	@RequestMapping(value = "media/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double media (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		if(!isNumber(numberone) || !isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
		
		return (convertToDouble(numberone)+ convertToDouble(numbertwo))/2;
		
	}
	
	@RequestMapping(value = "raiz/{numberone}", method = RequestMethod.GET)
	public Double raiz (@PathVariable(value = "numberone") String numberone) {
		
		if(!isNumber(numberone)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
		return Math.sqrt(convertToDouble(numberone)) ;
		
	}
	
	

	private double convertToDouble(String str) {
		if(str == null)return 0D;
		String number = str.replaceAll(",", ".");
		if(isNumber(number))return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumber(String str) {
		if(str == null)return false;
		String number = str.replaceAll(",", ".");
		
	  return  number.matches("-?\\d+(\\.\\d+)?");
	  
	}

}

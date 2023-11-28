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
	
	SimpleCalculator calculator = new SimpleCalculator();
	
	@RequestMapping(value = "/sun/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double sum(
			@PathVariable (value = "numberone")String numberone , 
			@PathVariable(value = "numbertwo")String numbertwo) throws NumberFormatException {
		
		checkNumericInputs(numberone, numbertwo);
		
		return calculator.sum(NumberConverter.convertToDouble(numberone), NumberConverter.convertToDouble(numbertwo));
		
	}
	
	@RequestMapping(value = "sub/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double sub (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		checkNumericInputs(numberone, numbertwo);
		
		return calculator.sub(NumberConverter.convertToDouble(numberone), NumberConverter.convertToDouble(numbertwo));
		
		
	}
	@RequestMapping(value = "multi/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double multi (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		checkNumericInputs(numberone, numbertwo);
		
		return calculator.multi(NumberConverter.convertToDouble(numberone), NumberConverter.convertToDouble(numbertwo));
		
		
	}
	
	@RequestMapping(value = "div/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double div (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		checkNumericInputs(numberone, numbertwo);
		
		if (NumberConverter.convertToDouble(numbertwo) == 0) {
			
            throw new UnsupportedMathOperationException("The divisor should be different from zero.");
        }
		
		return calculator.div(NumberConverter.convertToDouble(numberone), NumberConverter.convertToDouble(numbertwo));
		
		
		
	}
	@RequestMapping(value = "media/{numberone}/{numbertwo}", method = RequestMethod.GET)
	public Double media (@PathVariable(value = "numberone") String numberone, 
			@PathVariable(value = "numbertwo")String numbertwo) {
		
		checkNumericInputs(numberone, numbertwo);
		
		return calculator.media(NumberConverter.convertToDouble(numberone), NumberConverter.convertToDouble(numbertwo));
		
		
	}
	
	@RequestMapping(value = "raiz/{numberone}", method = RequestMethod.GET)
	public Double raiz (@PathVariable(value = "numberone") String numberone) {
		
		checkNumericInputs(numberone);
		
		return calculator.raiz(NumberConverter.convertToDouble(numberone));
		
		
	}
	
	private void checkNumericInputs(String numberone, String numbertwo) {

		if(!NumberConverter.isNumber(numberone) || !NumberConverter.isNumber(numbertwo)) {
			
			throw new UnsupportedMathOperationException("Please set a numeric Value.");
		}
		
    }

    private void checkNumericInputs(String number) {
    	
        if (!NumberConverter.isNumber(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
    }


}

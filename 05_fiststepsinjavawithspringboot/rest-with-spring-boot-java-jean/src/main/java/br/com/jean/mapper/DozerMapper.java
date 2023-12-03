package br.com.jean.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

public class DozerMapper {
	
	private static com.github.dozermapper.core.Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O,D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin,destination);
	}
	
	public static <O,D> List<D> parseListObjects(List<O> origins, Class<D> destination) {
		
		List<D> destinationObjects = new ArrayList<D>();
		
		for(O origin : origins ) {
			
			destinationObjects.add(mapper.map(origin,destination));
		}
		
		return destinationObjects ;
	}

}

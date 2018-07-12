package eng.tz.ms.la.core;
import java.lang.reflect.InvocationTargetException;

import eng.tz.ms.la.model.Line;
import eng.tz.ms.la.model.MetaLine;


/**
 * @author salvatore mariniello
 */

public class AnnotationFactory  {
 
	private static AnnotationFactory annotationFactory;

	private AnnotationFactory() {
		 
	}
	
	public static AnnotationFactory get(){
	if(annotationFactory==null)
		annotationFactory= new AnnotationFactory();
	
	return annotationFactory;
	}
	
	public static Line<MetaLine> audit(Object obj,Object request){
		Line<MetaLine> lineBuilder=auditBuilder(obj,request);
		if(lineBuilder==null){
			return new Line<MetaLine>();
		}
		return lineBuilder;
	}
	public static Line<MetaLine> audit(Object obj){
		Line<MetaLine> lineBuilder=auditBuilder(obj,null);
		if(lineBuilder==null){
			return new Line<MetaLine>();
		}
		return lineBuilder;
	}
	private static Line<MetaLine> auditBuilder(Object obj,Object request)
	{
		try {
			return AnnotationFactory.get().auditBean(obj,request);
		} catch (IllegalArgumentException e) {
	
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {
		
		} catch (ClassNotFoundException e) {
		
		} catch (InvocationTargetException e) {
		
		}
		return new Line<MetaLine>();
	}
	
	public Line<MetaLine> auditBean(Object obj,Object objRequest) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		Line<MetaLine> line= new Line<MetaLine>();
		MetaLine builder = new MetaLine();
		if(obj!=null){
		builder=AnnotationUtil.audit(obj,builder,objRequest,line);
	    line.setT(builder);
		}
		return line;
	}


 

}

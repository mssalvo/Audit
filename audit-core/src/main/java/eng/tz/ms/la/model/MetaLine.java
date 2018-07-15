package eng.tz.ms.la.model;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author s.mariniello
 */
public class MetaLine extends ArrayList<MetaField> {
 
	private static final long serialVersionUID = 1667801484417564593L;




	public MetaLine() {
 
	}
	
	public MetaLine(MetaField metaField) {
		 super.add(metaField); 
	}
	
	@Override
	public boolean add(MetaField metaField) {
		return super.add(metaField);
	}
	
	public MetaLine addField(MetaField metaField) {
		 super.add(metaField);
		 return this;
	}
 
	public MetaLine addField(String name,String value) {
		  super.add(new MetaField(name, value));
		 return this;
	}
	
	public MetaLine addField(String name,String value,boolean printType) {
		  super.add(new MetaField(name, value,(printType && value!=null)?value.getClass():null));
			 return this;
	}
 
 
	
	@Override
	public String toString() {
		return "" + (toArray() != null ? "MetaField=" + Arrays.toString(toArray()) + ", " : "")
				+  "";
	}

	
	
}

package eng.tz.ms.la.model.custom;

import java.net.InetAddress;
import java.net.UnknownHostException;

import eng.tz.ms.la.core.IMetaActor;
/**
 * @author s.mariniello
 */
public class AuditMetaActor implements IMetaActor {

	@Override
	public String getActor() {
		 
		return "anonimus";
	}

	@Override
	public String getOrigin() {
		 
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {}
		  catch (Exception e) {}
		return "origin";
	}

	@Override
	public String toString() {
		return toPrint();
	}

	
	@Override
	public String toPrint() {
		return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "") 
				+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";
	}
}

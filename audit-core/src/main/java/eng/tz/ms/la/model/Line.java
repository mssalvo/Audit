/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.ms.la.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import eng.tz.ms.la.core.IMetaActor;

/**
 * @author s.mariniello
 */
public class Line<T>  implements Serializable {

	private static final long serialVersionUID = -4701530830771819006L;

	private T t;
	private IMetaActor auditMeta;
	private Date time;
	private MetaInfo metaInfo;
	
	public Line() {
		
	}

	public Line(T t) {
		this.t = t;
		this.time = new Date();
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
		this.time = new Date();
	}

	public void setMetaActor(IMetaActor auditMeta) {
		this.auditMeta = auditMeta;
	}
	
	public IMetaActor getMetaActor() {
		return auditMeta;
	}
	
	public void setMetaInfo(MetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}
	
	public MetaInfo getMetaInfo() {
		return metaInfo;
	}
	
	
	@Override
	public String toString() {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(time) + " " +(auditMeta!=null ?auditMeta.toPrint():" ") + " " +(metaInfo!=null ?metaInfo.toString():" ")+ (t != null ? t.toString() : "-");
	}

}

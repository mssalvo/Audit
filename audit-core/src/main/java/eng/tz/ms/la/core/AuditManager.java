/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.ms.la.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import eng.tz.ms.la.model.Info;
import eng.tz.ms.la.model.Line;
import eng.tz.ms.la.model.MetaField;
import eng.tz.ms.la.model.MetaInfo;
import eng.tz.ms.la.model.MetaLine;
import eng.tz.ms.la.model.OperationExportFile;

/**
 * @author salvatore mariniello
 */

public class AuditManager extends EntitySetting {

	private static final long serialVersionUID = 1436749079910022271L;
	private static AuditManager auditFactory;
 
	private boolean encoded;
	private String keyUser = "Audit";
	private Line<MetaLine> lineIntern;
	private MetaLine metaLine;
	private String origin;
	private String actor;
	private IMetaActor auditMetaActor;
	private MetaInfo metaInfo;
	
	private AuditManager() {
		this.checkAllDir();
		this.auditInit();
	}

	private AuditManager(String keyUser) {
		 this.actor=keyUser;
		 this.checkAllDir();
		 auditInit();
	}
	
	private AuditManager(Settyngs settyngs) {
		this.setSettyng(settyngs);
		checkAllDir();
		auditInit();
	}
	
	private AuditManager(Settyngs settyngs,String keyUser) {
		this.actor=keyUser;
		this.setSettyng(settyngs);
		this.checkAllDir();
		this.auditInit();
	}

 

	private AuditManager writeLinea(Line<?> line) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(getPathAllCompleteLog(), true);
		} catch (FileNotFoundException e) {

		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = line.toString();
	 
		pw.println(lineLog.toString());
		pw.flush();
		pw.close();
		
		 return this;
	}
	
	private AuditManager writeLineaUser(Line<?> line) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(getPathUserLogginCompleteLog(), true);
		} catch (FileNotFoundException e) {

		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = line.toString();
 
		pw.println(lineLog.toString());
		pw.flush();
		pw.close();
		
		
		return this;
	}

	public AnnotationFactory annotation(){
		return AnnotationFactory.get();
	}
	
	public AuditManager build(){
		return this.buildAudit(false);
	}
	
	public AuditManager build(boolean isConsole){
		return this.buildAudit(isConsole);
	}
	
	private AuditManager buildAudit(boolean isConsole){
		if(this.actor!=null)
		this.keyUser=this.actor;
		if(origin!=null || actor!=null)
		this.lineIntern.setMetaActor(new MetaActorOrigin());
		if(this.auditMetaActor!=null && this.actor==null)
		this.lineIntern.setMetaActor(this.auditMetaActor);
		if(this.auditMetaActor!=null && this.actor==null)
		this.keyUser=this.auditMetaActor.getActor();
		this.lineIntern.setT(this.metaLine);
		this.lineIntern.setMetaInfo(this.metaInfo);
		if(isConsole)
		System.out.print(this.lineIntern.toString());
		this.checkWriteLine().writeLinea(this.lineIntern);
		this.writeLineaUser(this.lineIntern);
		this.auditInit();
		
		return this;
	}
	

	private void auditInit(){
		this.lineIntern=new Line<MetaLine>(); 
		this.metaLine= new MetaLine();
		this.metaInfo=new MetaInfo();
		this.auditMetaActor=null;
		this.origin=null;
		this.actor=null;
		
	}
	
	
	
	public AuditManager log(MetaLine line,Object reflection,Object request) {
		this.metaLine.addAll(line);
		if(reflection!=null){
		Line<MetaLine> lineAnn=AnnotationFactory.audit(reflection, request);
		if(lineAnn!=null && lineAnn.getT()!=null)
		this.metaLine.addAll(lineAnn.getT());
		if(lineAnn.getMetaActor()!=null && this.auditMetaActor==null)
			this.auditMetaActor=lineAnn.getMetaActor();
		}
		return this;
	}
 
	
	public AuditManager log(Object reflection,Object request) {
		if(reflection!=null){
		Line<MetaLine> lineLog=AnnotationFactory.audit(reflection, request);	
		if(lineLog!=null && lineLog.getT()!=null)
		this.metaLine.addAll(lineLog.getT());
		if(lineLog.getMetaActor()!=null && this.auditMetaActor==null)
		this.auditMetaActor=lineLog.getMetaActor();

		}
		return this;
	}
	
	public AuditManager log(MetaLine line,Object reflection) {
		
		this.metaLine.addAll(line);
		if(reflection!=null){
		Line<MetaLine> lineAnn=AnnotationFactory.audit(reflection);	
		if(lineAnn!=null && lineAnn.getT()!=null)
		this.metaLine.addAll(lineAnn.getT());
		if(lineAnn.getMetaActor()!=null && this.auditMetaActor==null)
			this.auditMetaActor=lineAnn.getMetaActor();
		}

		return this;
	}
	
	public AuditManager log(Object reflection) {
		
		if(reflection!=null){
			Line<MetaLine> lineLog=AnnotationFactory.audit(reflection);	
			if(lineLog!=null && lineLog.getT()!=null)
			this.metaLine.addAll(lineLog.getT());
			if(lineLog.getMetaActor()!=null && this.auditMetaActor==null)
				this.auditMetaActor=lineLog.getMetaActor();
			}
		
		return this;
	}
	
	public AuditManager log(MetaLine line) {
		this.metaLine.addAll(line);
		return this;
	}
	public AuditManager addMetaInfo(MetaInfo info) {
		this.metaInfo.addAll(info);
		return this;
	}
	public AuditManager addInfo(Info info) {
		this.metaInfo.add(info);
		return this;
	}
	
	public AuditManager addInfo(String name, String value) {
		this.metaInfo.add(new Info(name, value));
		return this;
	}
	
	public AuditManager log(String line) {
		this.metaLine.add(new MetaField(null, line));
		return this;
	}
	
	public AuditManager setActor(String actor) {
		this.actor=actor;
		return this;	
		}
	
	public AuditManager setOrigin(String origin) {
		this.origin=origin;
		return this;	
		}
	
	public AuditManager setMetaActor(Object metaActor) {
		try{
		this.auditMetaActor=(IMetaActor)metaActor;
		}catch(Exception e){}
		return this;	
	}
	
	public AuditManager setMetaActor(IMetaActor metaActor) {
		this.auditMetaActor=metaActor;
		return this;	
	}
	
	public AuditManager setMetaActor(Class<?> metaActor) {
		Object mta=null;
		try {
			mta = metaActor.newInstance();
			if(mta!=null)
		this.auditMetaActor=(IMetaActor)mta;			
		} catch (InstantiationException e) { }
		  catch (IllegalAccessException e) { }
		  catch(Exception e){}
		
		return this;	
	}
	
	
	public AuditManager addMetaField(String name,Object value) {
		this.metaLine.add(new MetaField(name,value,value==null?null:value.getClass()));
		return this;	
		}


	public boolean isEncoded() {
		return encoded;
	}

	public AuditManager setEncoded(boolean encoded) {
		this.encoded = encoded;
		return this;
	}

	public AuditManager laSettyng(Settyngs settyngs) {
		this.setSettyng(settyngs);
		checkAllDir();
		return this;
	}
	
	public AuditManager laSettyng(Class<?> settyngs) {
		try {
			this.setSettyng((Settyngs)settyngs.newInstance());
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		checkAllDir();
		return this;
	}

	public synchronized static AuditManager audit() {

		if (auditFactory == null) {
			auditFactory = new AuditManager();

		}

		return auditFactory;

	}
	

	
	public synchronized static AuditManager audit(Settyngs settyngs) {

		if (auditFactory == null) {
			auditFactory = new AuditManager(settyngs);
		} else {
			auditFactory.laSettyng(settyngs);
		}

		return auditFactory;

	}

 

	private AuditManager checkWriteLine() {
		File f = new File(getPathAllCompleteLog());
		if (!f.exists()) {
			return this.writeClearAll();
		}
		return checkUserLogginWriteLine();
	}
	private AuditManager checkUserLogginWriteLine() {
		File f = new File(getPathUserLogginCompleteLog());
		if (!f.exists()) {
			return this.writeClear();
		}
		return this;
	}

	private AuditManager writeClear() {
		BufferedWriter in = null;
		try {
			File f = new File(getPathUserLogginCompleteLog());
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
		}
		return this;

	}
	
	private AuditManager writeClearAll() {

		BufferedWriter in = null;
		try {
			File f = new File(getPathAllCompleteLog());
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
		}
		return this;

	}
 
	public AuditManager crypt(){
		this.cryptFileUser();
		return this;
	}
	
	public void export(boolean isCryptFile, Export export) throws UnsupportedEncodingException, IOException {
		if (isCryptFile) {
			this.cryptFile();
			export.call(new OperationExportFile(new File(getPathAllCompleteLog() + getCryptExtension())));
		} else {
			export.call(new OperationExportFile(new File(getPathAllCompleteLog())));
		}
	}

	public void exportUser(boolean isCryptFile, Export export) throws UnsupportedEncodingException, IOException {
		if (isCryptFile) {
			this.cryptFileUser();
			export.call(new OperationExportFile(new File(getPathUserLogginCompleteLog() + getCryptExtension())));
		} else {
			export.call(new OperationExportFile(new File(getPathUserLogginCompleteLog())));
		}
	}
	
	private String getPathAllCompleteLog() {
		return getPath_Log_User() + checkLogName();
	}

	private String getPathUserLogginCompleteLog() {
		return getPath_Log_User() + checkLogNameUserLoggin(this.keyUser);
	}
	
	public String encrypt(String s) {
		return Base64.encodeBytes(s.getBytes());
	}

	public String decrypt(String s) throws UnsupportedEncodingException {
		return new String(Base64.decode(s), "UTF-8");
	}

	private boolean cryptFile() {

		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedWriter out = null;

		try {

			fstream = new FileInputStream(getPathAllCompleteLog());
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder fileContent = new StringBuilder();

			while ((strLine = br.readLine()) != null) {
				fileContent.append(strLine);
				fileContent.append(LINE_SEPARATOR);
			}
		
			FileWriter fstreamWrite = new FileWriter(getPathAllCompleteLog() + getCryptExtension());
			out = new BufferedWriter(fstreamWrite);
			out.write(this.encrypt(fileContent.toString()));

		} catch (Exception e) {
			return false;
		} finally {
			try {
				fstream.close();
				out.flush();
				out.close();
				in.close();
			} catch (IOException e) {
				return false;
			}
		}

		return true;
	}

	private boolean cryptFileUser() {

		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedWriter out = null;

		try {

			fstream = new FileInputStream(getPathUserLogginCompleteLog());
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder fileContent = new StringBuilder();

			while ((strLine = br.readLine()) != null) {
				fileContent.append(strLine);
				fileContent.append(LINE_SEPARATOR);
			}
  
			FileWriter fstreamWrite = new FileWriter(getPathUserLogginCompleteLog() + getCryptExtension());
			out = new BufferedWriter(fstreamWrite);
			out.write(this.encrypt(fileContent.toString()));

		} catch (Exception e) {
			return false;
		} finally {
			try {
				fstream.close();
				out.flush();
				out.close();
				in.close();
			} catch (IOException e) {
				return false;
			}
		}

		return true;
	}
	
	private class MetaActorOrigin implements IMetaActor{

		@Override
		public String getActor() {
			 
			return actor;
		}

		@Override
		public String getOrigin() {
			 
			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {}
			  catch (Exception e) {}
			
			return origin;
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
	
}

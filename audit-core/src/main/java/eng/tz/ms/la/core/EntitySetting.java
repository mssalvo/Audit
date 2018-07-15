package eng.tz.ms.la.core;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eng.tz.ms.la.model.LogSettyng;

/**
 * @author s.mariniello
 */

public class EntitySetting implements Serializable {

	private static final long serialVersionUID = 5687997323479995145L;
	private LogSettyng settyng = new LogSettyng();
	private final String folderDay = new SimpleDateFormat("ddMMyyy").format(new Date(System.currentTimeMillis()));
	public final String path_home = System.getProperty("user.dir") + File.separator;
	private final String path_file = "la" + File.separator + "file" + File.separator + folderDay + File.separator;
	public final String path_log = "la-audit" + File.separator + folderDay + File.separator;
	private final String logNameLog = "audit.txt";
	private final String logExtension = ".txt";
	public final static String LINE_SEPARATOR = System.getProperty("line.separator");

	public EntitySetting() {
	}

	
	public String getConservazionePathComplete(){
		return settyng.getConservazionePathComplete();
	}
	
	protected boolean checkDirConservazione() {

		File f = new File(settyng.getConservazionePathComplete());
		if (!f.exists()) {
			boolean bol = f.mkdirs();
			return bol;
		}
 
		return true;
	}
	
	protected boolean checkAllDir() {

		File f = new File(getPath_Log());
		if (!f.exists()) {
			boolean bol = f.mkdirs();
			return bol;
		}

		f = new File(getPath_Log_User());
		if (!f.exists()) {
			boolean bol = f.mkdirs();
			return bol;
		}

		return true;
	}

	public String getPath_File() {

		return checkPathLogHome(settyng.getPath_home()) + this.path_file;
	}

	public String getPath_Log() {

		return checkPathLogHome(settyng.getPath_home()) + this.path_log;
	}

	public String getPath_Log_User() {

		return checkPathLogHome(settyng.getPath_home()) + this.path_log + File.separator;
	}

	
	private String checkPathLogHome(String paths_home) {
		if (paths_home == null || paths_home.isEmpty()) {
			return this.path_home;
		}
		if (paths_home.endsWith(File.separator)) {
			return paths_home;
		} else {
			return paths_home + File.separator;
		}
	}

	protected String checkLogName() {
		String logName = settyng.getLogName();
		if (logName == null || logName.isEmpty()) {
			return this.logNameLog;
		}

		if (logName.endsWith(checkExtension())) {
			return logName;
		} else {
			return logName.split("\\.")[0] + checkExtension();
		}
	}
	
	protected String checkLogNameUserLoggin(String userLoggin) {
		String logName = userLoggin;
		if (logName == null || logName.isEmpty()) {
			return this.logNameLog;
		}

		if (logName.endsWith(checkExtension())) {
			return logName;
		} else {
			return logName.split("\\.")[0] + checkExtension();
		}
	}

	protected String checkExtension() {
		String logsExtension = settyng.getLogExtension();
		if (logsExtension == null || logsExtension.isEmpty()) {
			return this.logExtension;
		}
		if (logsExtension.startsWith(".")) {
			return logsExtension;
		} else {
			return "." + logsExtension;
		}
	}

	protected void setSettyng(Settyngs settyngs) {
		settyngs.settyng(settyng);

	}
	
	public LogSettyng getSettyng() {
		return settyng;
	}

	public String getCryptExtension() {
		return settyng.getEncryptExtension();
	}
	
 public static String generaImpronta(byte[] buffer) {
 
		try {
			 
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update( buffer );
			// Get the MD5 sum
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			String output = bigInt.toString(16);
			System.out.println("MD5: " + output);
			return output;
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("Algoritmo MD5 non trovato", e);
		}		
	}
	
 public boolean isPrintType(){
	 return settyng.isPrintType();
 }
	
}

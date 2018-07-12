package eng.tz.ms.la.core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import eng.tz.ms.la.model.LogSettyng;

/**
 * @author salvatore mariniello
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

	protected String getCryptExtension() {
		return settyng.getEncryptExtension();
	}
}

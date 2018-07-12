package eng.tz.ms.la.model.custom;

import eng.tz.ms.la.core.Settyngs;
import eng.tz.ms.la.model.LogSettyng;
/**
 * @author salvatore mariniello
 */
public class CustomSettyng implements Settyngs {

	@Override
	public void settyng(LogSettyng settyng) {
		settyng.setPath_home(System.getProperty("user.dir")+"/../logs");
		settyng.setEncryptExtension(".ckr");
		settyng.setLogName("Audit");
	}

}

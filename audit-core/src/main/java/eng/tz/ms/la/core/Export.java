package eng.tz.ms.la.core;

import java.io.IOException;
import eng.tz.ms.la.model.OperationExportFile;
/**
 * @author salvatore mariniello
 */
public interface Export {
	public void call(OperationExportFile file) throws IOException;

}

package eng.tz.ms.la.model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/**
 * @author salvatore mariniello
 */
public class OperationExportFile {
	private File file;

	public OperationExportFile(File file) {
		super();
		this.file = file;

	}

	public byte[] getBytes() throws IOException {
		byte[] dataByte = new byte[(int) getFile().length()];
		DataInputStream dataInputStream = new DataInputStream(getInputStream());
		dataInputStream.readFully(dataByte);
		dataInputStream.close();
		return dataByte;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(getFile());
	}

	public File getFile() {
		return file;
	}

}

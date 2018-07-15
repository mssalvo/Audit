package eng.tz.ms.la.model;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import eng.tz.ms.la.core.AuditManager;
import eng.tz.ms.la.core.EntitySetting;
/**
 * @author s.mariniello
 */
public class OperationExportFile {
	private File file;
	private String impronta;
	private AuditManager manager;

	public OperationExportFile(File file, AuditManager manager) {
		super();
		this.file = file;
		this.manager = manager;

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

	public String getFileNameWithoutExt() {
		return file.getName().split("\\.")[0];
	}

	public String getSecurityImpronta() {

		try {
			return EntitySetting.generaImpronta(getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Algoritmo MD5 non trovato", e);
		}
	}

	public AuditManager getManager() {
		return manager;
	}

	public boolean saveToSystemFolderCons() {
		try {
			String impronta = getSecurityImpronta();
			checkPathFileConservazione();

			FileWriter fstreamWrite = new FileWriter(
					getManager().getConservazionePathComplete() + getFileNameWithoutExt() + "-(" + impronta + ").la");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write(new String(getBytes(), "UTF-8"));
			out.flush();
			out.close();

			deleteFileLog(impronta);

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	private void checkPathFileConservazione() {
		try {
			File f = new File(getManager().getConservazionePathComplete());
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
		}
	}

	private void deleteFileLog(String impronta) {
		try {

			File fimpr = new File(
					getManager().getConservazionePathComplete() + getFileNameWithoutExt() + "-(" + impronta + ").la");
			if (fimpr.exists()) {

				String pathCompl = getFile().getAbsolutePath();

				if (pathCompl.endsWith(getManager().getCryptExtension())) {
					pathCompl = pathCompl.replace(getManager().getCryptExtension(), "");
				} else {
					pathCompl += getManager().getCryptExtension();
				}
				File f = new File(pathCompl);
				if (f.exists()) {
					f.deleteOnExit();
				}

				getFile().deleteOnExit();
			}
		} catch (Exception e) {
		}
	}

}

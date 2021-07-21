package osmium.io;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import osmium.exception.exceptionTypes.EmptyDirectoryException;
import osmium.exception.exceptionTypes.InvalidDirectoryException;

public class WorldSelector {

	private String savesPath;
	private File worldDirectory;

	public WorldSelector() {
		this.savesPath = System.getenv("APPDATA") + "\\.minecraft\\saves\\";
	}

	private void refreshWorldDir() throws InvalidDirectoryException, EmptyDirectoryException {
		File dir = new File(savesPath);

		if (!dir.exists())
			throw new InvalidDirectoryException(dir);

		File[] worlds = dir.listFiles();

		worldDirectory = Arrays.stream(worlds).filter(File::isDirectory)
				.max(Comparator.comparing(File::lastModified)).orElse(null);
		
		if (worldDirectory == null)
			throw new EmptyDirectoryException(dir);
	}

	public File getLevelDat() throws InvalidDirectoryException, EmptyDirectoryException {
		refreshWorldDir();
			
		return new File(worldDirectory + "\\level.dat");
	}

	public void setSavesPath(String savesPath) {
		this.savesPath = savesPath;
	}
	
	public void resetSavesPath() {
		this.savesPath = System.getenv("APPDATA") + "\\.minecraft\\saves\\";
	}
}

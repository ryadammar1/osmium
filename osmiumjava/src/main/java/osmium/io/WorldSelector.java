package osmium.io;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import osmium.exception.InvalidDirectoryException;

public class WorldSelector {

	private String savesPath;
	private File worldDir;

	public WorldSelector(String savesPath) {
		this.savesPath = savesPath;
	}

	public WorldSelector() {
		this.savesPath = System.getenv("APPDATA") + "\\.minecraft\\saves\\";
	}

	public File getWorldDir() throws InvalidDirectoryException {
		if(worldDir != null)
			return worldDir;
		refreshWorldDir();
			return worldDir;
	}
	
	public File getRefreshedWorldDir() throws InvalidDirectoryException {
		refreshWorldDir();
			return worldDir;
	}
	
	private void refreshWorldDir() throws InvalidDirectoryException {
		File dir = new File(savesPath);
		
		if(!dir.exists())
			throw new InvalidDirectoryException(dir);
		
		File[] worlds = dir.listFiles();
		this.worldDir = Arrays.stream(worlds).filter(File::isDirectory).max(Comparator.comparing(File::lastModified))
				.orElse(null);
	}
	
	public File getLevelDat() throws InvalidDirectoryException {
		return new File(getWorldDir() + "\\level.dat");
	}
	
	public File getRefreshedLevelDat() throws InvalidDirectoryException {
		return new File(getRefreshedWorldDir() + "\\level.dat");
	}

	public void setWorldDir(File worldDir) {
		this.worldDir = worldDir;
	}

	public String getSavesPath() {
		return savesPath;
	}

	public void setSavesPath(String savesPath) {
		this.savesPath = savesPath;
	}

}

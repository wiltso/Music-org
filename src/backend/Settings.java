package backend;

public class Settings {
	private volatile static Settings instance;
	public boolean canCreateActions;
	
	private Settings() {
		canCreateActions = true;
	}
	
	public static Settings getInstance() {
		if (instance == null) {
			synchronized (History.class) {
				if (instance == null) {
					instance = new Settings();
				}
			}
		}
		return instance;
	}
}

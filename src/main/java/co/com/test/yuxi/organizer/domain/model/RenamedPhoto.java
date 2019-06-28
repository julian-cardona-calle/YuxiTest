package co.com.test.yuxi.organizer.domain.model;

public class RenamedPhoto {

	private String oldName;

	private String newName;

	public RenamedPhoto(String oldName, String newName) {
		super();
		this.oldName = oldName;
		this.newName = newName;
	}

	public String getOldName() {
		return oldName;
	}

	public String getNewName() {
		return newName;
	}

}

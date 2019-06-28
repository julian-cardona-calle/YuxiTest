package co.com.test.yuxi.organizer.domain.model;

import java.time.LocalDateTime;

public class Photo {

	private int number;

	private String name;

	private String extension;

	private String city;

	private LocalDateTime date;

	public Photo(int number, String name, String extension, String city, LocalDateTime date) {
		this.number = number;
		this.name = name;
		this.extension = extension;
		this.city = city;
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	public String getCity() {
		return city;
	}

	public LocalDateTime getDate() {
		return date;
	}

}

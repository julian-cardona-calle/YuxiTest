package co.com.test.yuxi.organizer.domain.model.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import co.com.test.yuxi.organizer.domain.model.Photo;

@Component
public class PhotoFactory {

	private static final String PHOTO_PARTS_SEPARATOR = ",";
	private static final String NAME_PARTS_SEPARATOR = "\\.";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public Photo create(int lineNumber, String line) {
		String[] photoParts = line.split(PHOTO_PARTS_SEPARATOR);
		String fullName = photoParts[0].trim();
		String[] nameParts = fullName.split(NAME_PARTS_SEPARATOR);
		String extension = nameParts[1];
		String city = photoParts[1].trim();
		String date = photoParts[2].trim();
		return new Photo(lineNumber, fullName, extension, city,
				LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)));
	}

}

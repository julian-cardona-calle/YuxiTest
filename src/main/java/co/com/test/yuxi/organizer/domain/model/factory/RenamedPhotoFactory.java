package co.com.test.yuxi.organizer.domain.model.factory;

import org.springframework.stereotype.Component;

import co.com.test.yuxi.organizer.domain.model.Photo;
import co.com.test.yuxi.organizer.domain.model.RenamedPhoto;

@Component
public class RenamedPhotoFactory {

	public RenamedPhoto create(Photo photo,String newName) {
		return new RenamedPhoto(photo.getName(), newName);
	}
	
}

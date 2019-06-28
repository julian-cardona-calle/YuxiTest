package co.com.test.yuxi.organizer.domain.business;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.test.yuxi.organizer.domain.model.Photo;
import co.com.test.yuxi.organizer.domain.model.RenamedPhoto;
import co.com.test.yuxi.organizer.domain.model.factory.RenamedPhotoFactory;
import co.com.test.yuxi.organizer.domain.repository.PhotoRepository;

@Service
public class PhotoService {

	private static final String NAME_PARTS_SEPARATOR = ".";
	private static final String PADDING_NUMBER = "0";

	private PhotoRepository photoRepository;

	private RenamedPhotoFactory renamedPhotoFactory;

	public PhotoService(PhotoRepository photoRepository, RenamedPhotoFactory renamedPhotoFactory) {
		this.photoRepository = photoRepository;
		this.renamedPhotoFactory = renamedPhotoFactory;
	}

	public Stream<RenamedPhoto> getRenamedPhotos() {
		Stream<Photo> photos = this.photoRepository.getPhotos();
		photos = photos.sorted(Comparator.comparing(Photo::getDate));
		var groupsByCity = photos.collect(Collectors.groupingBy(Photo::getCity));
		Stream<RenamedPhoto> flatMap = groupsByCity.entrySet().stream().flatMap((entry) -> {
			AtomicInteger counter = new AtomicInteger();
			return entry.getValue().stream().map(photo -> {
				return Map.entry(photo.getNumber(),
						renamePhoto(photo, entry.getValue().size(), counter.incrementAndGet()));
			});
		}).sorted(Map.Entry.comparingByKey()).map(Entry::getValue);
		return flatMap;
	}

	protected RenamedPhoto renamePhoto(Photo photo, int amountPhotosByCity, int photoIndex) {
		String newName = photo.getCity() + StringUtils.leftPad(String.valueOf(photoIndex),
				String.valueOf(amountPhotosByCity).length(), PADDING_NUMBER) + NAME_PARTS_SEPARATOR
				+ photo.getExtension();
		return this.renamedPhotoFactory.create(photo, newName);
	}

}

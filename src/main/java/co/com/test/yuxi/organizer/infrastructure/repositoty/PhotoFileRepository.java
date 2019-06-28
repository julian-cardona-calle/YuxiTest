package co.com.test.yuxi.organizer.infrastructure.repositoty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import co.com.test.yuxi.organizer.domain.model.Photo;
import co.com.test.yuxi.organizer.domain.model.factory.PhotoFactory;
import co.com.test.yuxi.organizer.domain.repository.PhotoRepository;
import co.com.test.yuxi.organizer.domain.repository.exception.RepositoryExcepcion;

@Repository
public class PhotoFileRepository implements PhotoRepository {

	private static final String REPOSITORY_EXCEPTION_MESSAGE = "An error has occurred while reading photos from the file";

	private static final String FILE_NAME = "/photos.txt";

	private PhotoFactory photoFactory;

	public PhotoFileRepository(PhotoFactory photoFactory) {
		this.photoFactory = photoFactory;
	}

	@Override
	public Stream<Photo> getPhotos() {
		Path photosPath;
		try {
			AtomicInteger counter = new AtomicInteger();
			photosPath = Paths.get(PhotoFileRepository.class.getResource(FILE_NAME).toURI());
			return Files.lines(photosPath).map(line -> this.photoFactory.create(counter.incrementAndGet(), line));
		} catch (Exception e) {
			throw new RepositoryExcepcion(REPOSITORY_EXCEPTION_MESSAGE, e);
		}
	}

}

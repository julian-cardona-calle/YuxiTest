package co.com.test.yuxi.organizer.domain.repository;

import java.util.stream.Stream;

import co.com.test.yuxi.organizer.domain.model.Photo;

public interface PhotoRepository {

	Stream<Photo> getPhotos();
}

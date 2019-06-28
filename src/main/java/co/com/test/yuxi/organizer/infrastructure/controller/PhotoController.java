package co.com.test.yuxi.organizer.infrastructure.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.test.yuxi.organizer.domain.business.PhotoService;
import co.com.test.yuxi.organizer.domain.model.RenamedPhoto;

@RestController
@RequestMapping("/photos")
public class PhotoController {

	private static final String NAMES = "names";
	private PhotoService photoService;

	public PhotoController(PhotoService photoService) {
		this.photoService = photoService;
	}

	@GetMapping("/renamed")
	public Map<String, List<RenamedPhoto>> getRenamedPhotos() {
		return Map.of(NAMES, this.photoService.getRenamedPhotos().collect(Collectors.toList()));
	}

}

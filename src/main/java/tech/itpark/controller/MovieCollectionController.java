package tech.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.dto.CollectionDto;
import tech.itpark.service.MovieCollectionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/collection")
public class MovieCollectionController {

    private final MovieCollectionService movieCollectionService;

    @GetMapping("/all")
    public List<CollectionDto> collections() {
        return movieCollectionService.getCollections();
    }

    @GetMapping("/{id}")
    public CollectionDto collection(@PathVariable("id") long id) {
        return movieCollectionService.getCollection(id);
    }
}


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final List<SearchResult> searchResults = new ArrayList<>();

    public SearchService() {
        loadJsonData();
    }

    private void loadJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Load rental cars and hotels from JSON files
            List<SearchResult> rentalCars = objectMapper.readValue(Paths.get("src/main/resources/rental_cars.json").toFile(),
                    new TypeReference<List<SearchResult>>() {});
            List<SearchResult> hotels = objectMapper.readValue(Paths.get("src/main/resources/hotels.json").toFile(),
                    new TypeReference<List<SearchResult>>() {});

            searchResults.addAll(rentalCars);
            searchResults.addAll(hotels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SearchResult> searchByCity(String city) {
        return searchResults.stream()
                .filter(result -> result.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}

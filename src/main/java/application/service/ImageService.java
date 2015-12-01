package application.service;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by predding on 11/30/15.
 */
@Component
public class ImageService {

    /**
     * Get a list of images matching the criteria for a specific city.
     *
     * @param city           the city name for image lookup
     * @param additionalTags list of any additional tags to be included in the image search
     * @param imageCount     the number of images to return
     * @return a list of image URLs matching the criteria
     */
    public List<String> getCityImages(String city, List<String> additionalTags, int imageCount) {
        return null;
    }
}

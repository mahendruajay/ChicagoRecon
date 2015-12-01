package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by predding on 11/30/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResponse {
    @JsonProperty("results")
    private List<ImageResult> images;

    public List<ImageResult> getImages() {
        return images;
    }
}

package application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by predding on 11/30/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResult {
    @JsonProperty("width")
    int width;

    @JsonProperty("height")
    int height;

    @JsonProperty("url")
    String url;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }
}

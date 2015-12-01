package application.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import application.domain.ImageResponse;
import application.domain.ImageResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by predding on 11/30/15.
 */
@Component
public class ImageService {
    private static final String BASE_IMAGE_SEARCH_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&safe=active&imgtype=photo&imgsz=xlarge&rsz=%d&q=%s";

    /**
     * Get a list of images matching the criteria for a specific city.
     *
     * @param city           the city name for image lookup
     * @param additionalTags list of any additional tags to be included in the image search
     * @param imageCount     the number of images to return
     * @return a list of image URLs matching the criteria
     */
    public List<String> getCityImages(String city, List<String> additionalTags, int imageCount) {
        List<String> tags = Lists.newArrayList(city);

        if (additionalTags != null) {
            tags.addAll(additionalTags);
        }

        String url = String.format(BASE_IMAGE_SEARCH_URL, imageCount, Joiner.on(",").join(tags));

        try {
            String response = readJsonFromUrl(url);

            return processResponse(response);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            return sb.toString();
        } finally {
            is.close();
        }
    }

    private List<String> processResponse(String json) {
        List<String> images = new ArrayList<>();
        Gson gson = new Gson();
        ImageResponse imageResponse = gson.fromJson(json, ImageResponse.class);

        if (imageResponse != null && imageResponse.getImages() != null) {
            for (ImageResult imageResult : imageResponse.getImages()) {
                images.add(imageResult.getUrl());
            }
        }

        return images;
    }
}

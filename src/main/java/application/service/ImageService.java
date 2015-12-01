package application.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by predding on 11/30/15.
 */
@Component
public class ImageService {
    private static final String BASE_IMAGE_SEARCH_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&safe=active&imgtype=photo&imgsz=xlarge";

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

        try {
            String url = BASE_IMAGE_SEARCH_URL +
                    "&rsz=" + imageCount +
                    "&q=" + URLEncoder.encode(Joiner.on(",").join(tags), "UTF-8");
            String response = readJsonFromUrl(url);

            return processResponse(response);
        } catch (Exception e) {
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

    private List<String> processResponse(String json) throws IOException {
        List<String> images = new ArrayList<>();

        JSONObject baseObject = new JSONObject(json);
        JSONObject responseData = baseObject.getJSONObject("responseData");
        JSONArray results = responseData.getJSONArray("results");

        for (int i = 0; i < results.length(); ++i) {
            final JSONObject image = results.getJSONObject(i);
            images.add(image.getString("unescapedUrl"));
        }

        return images;
    }
}

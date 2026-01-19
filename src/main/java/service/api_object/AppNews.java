package service.api_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;

@Data
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppNews {
    @JsonProperty("appid")
    private Integer appId;
    @JsonProperty("newsitems")
    private ArrayList<NewsItem> newsItems;
    private Integer count;
}

package service.api_object.i_steam_news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;


@Data
@FieldNameConstants
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppNews {
    @JsonProperty("appid")
    private Integer appId;
    @JsonProperty("newsitems")
    private ArrayList<NewsItem> newsItems;
    private Integer count;
    @JsonIgnore
    private Integer requestCount;
}

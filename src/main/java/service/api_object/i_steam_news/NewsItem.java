package service.api_object.i_steam_news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;

@Data
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsItem {
    private String gid;
    private String title;
    private String url;
    @JsonProperty("is_external_url")
    private Boolean isExternalUrl;
    private String author;
    private String contents;
    @JsonProperty("feedlabel")
    private String feedLabel;
    private Integer date;
    @JsonProperty("feedname")
    private String feedName;
    @JsonProperty("feed_type")
    private Integer feedType;
    @JsonProperty("appid")
    private Integer appId;
    private ArrayList<String> tags;
}

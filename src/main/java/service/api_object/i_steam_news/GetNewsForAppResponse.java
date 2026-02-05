package service.api_object.i_steam_news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class GetNewsForAppResponse {
    @JsonProperty("appnews")
    private AppNews appNews;
}

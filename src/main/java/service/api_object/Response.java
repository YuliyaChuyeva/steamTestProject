package service.api_object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Response {
    @JsonProperty("player_level")
    public Integer playerLevel;
}

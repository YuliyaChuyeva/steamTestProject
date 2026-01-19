package service.api_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class Achievement {
    @JsonProperty("apiname")
    private String apiName;
    private Integer achieved;
    @JsonProperty("unlocktime")
    private Integer unlockTime;
}

package service.api_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("game_count")
    private Integer gameCount;
    @JsonProperty("games")
    private List<Game> games;
}

package service.api_object.i_player_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("game_count")
    private Integer gameCount;
    @JsonProperty("games")
    private List<Game> games;
}

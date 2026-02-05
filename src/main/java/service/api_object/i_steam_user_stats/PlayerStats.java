package service.api_object.i_steam_user_stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStats {
    private String steamID;
    private String gameName;
    private ArrayList<Achievement> achievements;
    private Boolean success;
}

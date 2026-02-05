package service.api_object.i_steam_user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    @JsonProperty("steamid")
    public String steamId;
    @JsonProperty("communityvisibilitystate")
    public Integer communityVisibilityState;
    @JsonProperty("profilestate")
    public Integer profileState;
    @JsonProperty("personaname")
    public String personaName;
    @JsonProperty("profileurl")
    public String profileUrl;
    public String avatar;
    @JsonProperty("avatarmedium")
    public String avatarMedium;
    @JsonProperty("avatarfull")
    public String avatarFull;
    @JsonProperty("avatarhash")
    public String avatarHash;
    @JsonProperty("lastlogoff")
    public Integer lastLogoff;
    @JsonProperty("personastate")
    public Integer personaState;
    @JsonProperty("primaryclanid")
    public String primaryClanId;
    @JsonProperty("timecreated")
    public Integer timeCreated;
    @JsonProperty("personastateflags")
    public Integer personaStateFlags;
}

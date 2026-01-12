package service.api_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    @JsonProperty("appid")
    private Integer appId;
    private String name;
    @JsonProperty("playtime_forever")
    private Integer playtimeForever;
    @JsonProperty("img_icon_url")
    private String imgIconUrl;
    @JsonProperty("has_community_visible_stats")
    private Boolean hasCommunityVisibleStats;
    @JsonProperty("playtime_windows_forever")
    private Integer playtimeWindowsForever;
    @JsonProperty("playtime_mac_forever")
    private Integer playtimeMacForever;
    @JsonProperty("playtime_linux_forever")
    private Integer playtimeLinuxForever;
    @JsonProperty("playtime_deck_forever")
    private Integer playtimeDeckForever;
    @JsonProperty("rtime_last_played")
    private Integer rtimeLastPlayed;
    @JsonProperty("has_leaderboards")
    private Boolean hasLeaderboards;
    @JsonProperty("content_descriptorids")
    private ArrayList<Integer> contentDescriptorids;
    @JsonProperty("playtime_disconnected")
    private Integer playtimeDisconnected;
}

package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnedGamesResponse {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        @JsonProperty("game_count")
        private Integer gameCount;
        @JsonProperty("games")
        private List<Game> games;

        public Integer getGameCount() {
            return gameCount;
        }

        public void setGameCount(Integer gameCount) {
            this.gameCount = gameCount;
        }

        public List<Game> getGames() {
            return games;
        }

        public void setGames(List<Game> games) {
            this.games = games;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Game {
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

        public Integer getAppId() {
            return appId;
        }

        public void setAppId(Integer appId) {
            this.appId = appId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPlaytimeForever() {
            return playtimeForever;
        }

        public void setPlaytimeForever(Integer playtimeForever) {
            this.playtimeForever = playtimeForever;
        }

        public String getImgIconUrl() {
            return imgIconUrl;
        }

        public void setImgIconUrl(String imgIconUrl) {
            this.imgIconUrl = imgIconUrl;
        }

        public Boolean getHasCommunityVisibleStats() {
            return hasCommunityVisibleStats;
        }

        public void setHasCommunityVisibleStats(Boolean hasCommunityVisibleStats) {
            this.hasCommunityVisibleStats = hasCommunityVisibleStats;
        }

        public Integer getPlaytimeWindowsForever() {
            return playtimeWindowsForever;
        }

        public void setPlaytimeWindowsForever(Integer playtimeWindowsForever) {
            this.playtimeWindowsForever = playtimeWindowsForever;
        }

        public Integer getPlaytimeMacForever() {
            return playtimeMacForever;
        }

        public void setPlaytimeMacForever(Integer playtimeMacForever) {
            this.playtimeMacForever = playtimeMacForever;
        }

        public Integer getPlaytimeLinuxForever() {
            return playtimeLinuxForever;
        }

        public void setPlaytimeLinuxForever(Integer playtimeLinuxForever) {
            this.playtimeLinuxForever = playtimeLinuxForever;
        }

        public Integer getPlaytimeDeckForever() {
            return playtimeDeckForever;
        }

        public void setPlaytimeDeckForever(Integer playtimeDeckForever) {
            this.playtimeDeckForever = playtimeDeckForever;
        }

        public Integer getRtimeLastPlayed() {
            return rtimeLastPlayed;
        }

        public void setRtimeLastPlayed(Integer rtimeLastPlayed) {
            this.rtimeLastPlayed = rtimeLastPlayed;
        }

        public Boolean getHasLeaderboards() {
            return hasLeaderboards;
        }

        public void setHasLeaderboards(Boolean hasLeaderboards) {
            this.hasLeaderboards = hasLeaderboards;
        }

        public ArrayList<Integer> getContentDescriptorids() {
            return contentDescriptorids;
        }

        public void setContentDescriptorids(ArrayList<Integer> contentDescriptorids) {
            this.contentDescriptorids = contentDescriptorids;
        }

        public Integer getPlaytimeDisconnected() {
            return playtimeDisconnected;
        }

        public void setPlaytimeDisconnected(Integer playtimeDisconnected) {
            this.playtimeDisconnected = playtimeDisconnected;
        }
    }
}

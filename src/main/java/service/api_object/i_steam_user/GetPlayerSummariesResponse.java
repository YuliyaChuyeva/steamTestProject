package service.api_object.i_steam_user;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class GetPlayerSummariesResponse {
    public Response response;
}

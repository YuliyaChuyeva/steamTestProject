package service.api_object.i_steam_user;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;

@Data
@FieldNameConstants
public class Response {
      public ArrayList<Player> players;
}

package service.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@FieldNameConstants
public class Game {
    private String title;
    private String price;
    private String releaseDate;
}

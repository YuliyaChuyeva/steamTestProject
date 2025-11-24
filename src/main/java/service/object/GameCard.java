package service.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@FieldNameConstants
public class GameCard {
    private final String title;
    private final List<String> tags;

}

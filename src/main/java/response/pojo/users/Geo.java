package response.pojo.users;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    private String lat;
    private String lng;
}

package response.pojo.users;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;
}

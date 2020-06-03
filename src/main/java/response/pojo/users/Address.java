package response.pojo.users;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}

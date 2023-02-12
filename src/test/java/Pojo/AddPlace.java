package Pojo;

import java.util.List;
import lombok.Data;
@Data

public class AddPlace {

    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String language;

    private Location location;

    private List<String> types;



}

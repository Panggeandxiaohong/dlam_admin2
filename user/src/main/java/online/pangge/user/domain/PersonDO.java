package online.pangge.user.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDO{

    private Integer id;

    private String username;

    private Integer age;

    public String toString() {
    	return "id"+id+",name="+username+",age="+age;
    }
}
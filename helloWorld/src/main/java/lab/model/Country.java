package lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Country implements Serializable {

    @Value("1")
	private int id;

    @Value("Russia")
    private String name;

    @Value("RU")
    private String codeName;

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}
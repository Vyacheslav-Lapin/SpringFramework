package lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column
    private String name;
    @Column(name = "code_name")
    private String codeName;

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

}

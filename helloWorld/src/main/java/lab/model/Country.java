package lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country implements Serializable {

	private int id;
	private String name;
	private String codeName;

	public Country(String name, String codeName) {
		this.name = name;
		this.codeName = codeName;
	}
}

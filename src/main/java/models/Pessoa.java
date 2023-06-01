package models;

import enums.EnumSexo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Pessoa {
	private Integer id;
	@NonNull
	public String nome;
	@NonNull
	private Integer idade;
	@NonNull
	private EnumSexo sexo;
	
	public Pessoa(int id) {
		this.id = id;
	}
}

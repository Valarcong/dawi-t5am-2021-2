package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_categorias")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
	
	@Id
	private int idcategoria;
	private String descripcion;
	
	

}

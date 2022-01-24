package br.com.generation.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_produtos")
public class Produtos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="O preenchimento deste campo é obrigatório.")
	@Size(min=5,max=100,message="O campo obrigatório deve conter no minímo 5 e no máximo 100 caracteres.")
	private String titulo;
	
	@Column(name = "data_lancamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;
	
	@NotBlank(message="O preenchimento deste campo é obrigatório.")
	@Size(min=5,max=100,message="O campo obrigatório deve conter no minímo 5 e no máximo 100 caracteres.")
	private String console;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	@NotBlank(message="Campo Obrigatório")
	private BigDecimal valor;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}

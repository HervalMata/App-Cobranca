package com.algaworks.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Titulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	@NotEmpty(message="Descrição é obrigatório")
	@Size(max=60, message = "A descrição não pode ter mais de 60 caracteres")
	private String descricao;
	@NotNull(message = "Data de vencimento é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@NotNull(message="Valor não pode ser nulo.")
	@DecimalMin(value = "0.01" , message = "Valor não pode ser menor do que 0,01")
	@DecimalMax(value =  "99999999.99", message = "Valor não poder ser mais do que 9.999.999.99")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	public Titulo() {
	}
	
	public Titulo(Long codigo, String descricao, Date dataVencimento, BigDecimal valor, StatusTitulo status) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.status = status;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	
	public boolean isPendente(){
		return StatusTitulo.PENDENTE.equals(this.status);
	}
	public boolean isNew(){
		return codigo == null || codigo < 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}

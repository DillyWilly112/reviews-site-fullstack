package org.wecancodeit;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Strain {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@ManyToMany(mappedBy = "strains")
	private Collection<Type> types;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	//default no args contructor required by jpa
	public Strain() {
	}

	public Strain(String name) {
		this.name = name;
	}
	
	public Collection<Type> getTypes() {
		return types;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Strain other = (Strain) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}

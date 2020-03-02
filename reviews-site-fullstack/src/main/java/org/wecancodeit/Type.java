package org.wecancodeit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Type {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	
	@ManyToMany
	private Collection<Strain> strains;
	
	@OneToMany(mappedBy = "type")
	private Collection<Composition> compositions;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Collection<Strain> getTypes() {
		
		return strains;
	}
	public Collection<Composition> getCompositions() {
		
		return compositions;
	}
	
	public Type() {
	}

	public Type(String name, String description, Strain...strains) {
		this.name = name;
		this.description = description;
		this.strains = new HashSet<>(Arrays.asList(strains));
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
		Type other = (Type) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

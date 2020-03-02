package org.wecancodeit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Composition {

	@Id
	@GeneratedValue
	private long id;
	
	private String origin;
	
	@ManyToOne
	private Type type;
	
	public Composition() {
		
	}
	
	public Composition(String origin, Type type) {
		this.origin = origin;
		this.type = type;
		
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
		Composition other = (Composition) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

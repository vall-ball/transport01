package ru.vallball.transport01;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("bigbus")
public class Bigbus extends Bus{
	
	@OneToMany(
	        mappedBy = "bus",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Tour> tours;

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
	
	
	
}

package telran.objects_streams.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Address implements Serializable {
	public String city;
	public String street;
	public int building;
	public int flat;
	public Address(String city, String street, int building, int flat) {
		super();
		this.city = city;
		this.street = street;
		this.building = building;
		this.flat = flat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + building;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + flat;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (building != other.building)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (flat != other.flat)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", building=" + building + ", flat=" + flat + "]";
	}
	
	
	
}

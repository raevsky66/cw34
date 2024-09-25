package telran.objects_streams.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Person implements Serializable{
	public int id;
	public String name;
	public Address address;
	public LocalDate birthDay;
	
	public Person(int id, String name, Address address, LocalDate birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthDay = birthDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address + ", birthDay=" + birthDay + "]";
	}
	
	
}

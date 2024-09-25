package telran.objects_streams.dto;

import java.time.LocalDate;

public class Child extends Person {
	public String garten;

	public Child(int id, String name, Address address, LocalDate birthDay, String garten) {
		super(id, name, address, birthDay);
		this.garten = garten;
	}

	@Override
	public String toString() {
		return "Child [garten=" + garten + ", id=" + id + ", name=" + name + ", address=" + address + ", birthDay="
				+ birthDay + "]";
	}
	
}

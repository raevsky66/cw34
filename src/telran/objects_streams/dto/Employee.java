package telran.objects_streams.dto;

import java.time.LocalDate;

public class Employee extends Person {
	public String company;
	public int salary;
	public String title;
	
	public Employee(int id, String name, Address address, LocalDate birthDay, String company, int salary,
			String title) {
		super(id, name, address, birthDay);
		this.company = company;
		this.salary = salary;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [company=" + company + ", salary=" + salary + ", title=" + title + ", id=" + id + ", name="
				+ name + ", address=" + address + ", birthDay=" + birthDay + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + salary;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (salary != other.salary)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}

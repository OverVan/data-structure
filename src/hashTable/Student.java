package hashTable;

public class Student {
	private String firstName;
	private String lastName;
	private int cls;
	private int grade;

	public Student(String firstName, String lastName, int cls, int grade) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cls = cls;
		this.grade = grade;
	}

	private int hashCode(Object... fields) {
		int hash = 0;
		int B = 31;
		for (Object field : fields) {
			hash = hash * B + field.hashCode();
		}
		return hash;
	}

	@Override
	public int hashCode() {
		// 可阅读内置的hash函数-Objects.hash
		return hashCode(firstName, lastName, cls, grade);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
		}
		if (other.getClass() != getClass()) {
			return false;
		}
		Student student = (Student) other;
		if (cls != student.cls) {
			return false;
		}
		if (grade != student.grade) {
			return false;
		}
		if (!(firstName == student.firstName || firstName != null && firstName.equals(student.firstName))) {
			return false;
		}
		if (!(lastName == student.lastName || lastName != null && lastName.equals(student.lastName))) {
			return false;
		}
		return true;
	}
}

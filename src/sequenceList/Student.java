package sequenceList;

public class Student {
	private String name;
	private int score;

	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}

	/**
	 * 深度比较
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Student other = (Student) object;
		if (name.equals(other.name) && score == other.score) {
			return true;
		}
		return false;
	}
}

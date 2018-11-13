
public class StaticClass {

	static class Cow {
		int a;

		public Cow(int a) {
			this.a = a;
		}
	}

	static class Cow2 implements Comparable<Cow> {
		int a;

		public Cow2(int a) {
			this.a = a;
		}

		@Override
		public int compareTo(Cow other) {
			return Integer.compare(this.a, other.a);
		}
	}
}

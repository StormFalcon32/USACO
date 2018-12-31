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
		public boolean equals(Object o) {
			Cow2 other = (Cow2) o;
			return this.a == other.a;
		}
		
		@Override
		public int hashCode() {
			int hash = 7;
			hash = 31 * hash + a;
			return hash;
		}
		
		@Override
		public int compareTo(Cow other) {
			return Integer.compare(this.a, other.a);
		}
	}
}

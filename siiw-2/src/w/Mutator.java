package w;

public class Mutator {
	public static Path Mutate(Path p) {
		City tmp = p.getCity(0);
		p.setCity(0, p.getCity(1));
		p.setCity(1, tmp);

		return p;
	}
}

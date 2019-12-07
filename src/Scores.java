// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

public class Scores implements Comparable<Scores>{

	private String name = null;
	private Double score;
	
	public Scores() {
		
	}
	
	public Scores(String n, Double s) {
		name = n;
		score = s;
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public String name() {
		return this.name;
		
	}
	
	
	public Double score() {
		return score;
	}
	
	
	public int compareTo(Scores o) {
		
		return score.compareTo(o.score());
	
	}
	
	public String toString() {
		
		return (name + "   -   " + score);
		
	}

}

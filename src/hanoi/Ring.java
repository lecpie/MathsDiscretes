package hanoi;

public class Ring {
	
	private int width;
	
	public Ring (int width) {
		this.width = width;
		
	} // Tower()
	
	public int getWidth() {
		return width;
		
	} // getWidth()
	
	public String toString() {
		String str = "-";
		
		for (int i = 1; i < width; ++i)
			str += "--";
		
		return str;
		
	} // toString()
	
} // class Ring

package hanoi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class Hanoi {
	
	private static final int NBTOWERS = 3;
	
	private Stack <Ring> towers[];
	private int size;
	private int nbMove;
	
	/**
	 * Constructor for the Hanoi class
	 * @param size : number of rings
	 */
	public Hanoi (int size) {
		this.size = size;
		
		towers = new Stack[NBTOWERS];
		
		for (int i = 0; i < NBTOWERS; ++i) {
			towers[i] = new Stack <Ring>();
		}
		
		for (int i = 0; i < size; ++i)
			towers[0].push(new Ring(size - i));
		
		nbMove = 0;
				
	} // towers()
	
	/**
	 * Get the number of ring
	 * @return the number of ring
	 */
	public int getSize() {
		return size;
		
	} // getSize()
	
	public int getNbMove() {
		return nbMove;
		
	} // getNbMove()
	
	/**
	 * try to move a ring from point a to point b
	 * @param a : the position of the ring to move
	 * @param b : the position where to move the ring
	 * @throws Exception
	 */
	public void move (int a, int b) throws Exception {		
		if (towers[a].size() == 0)
			throw new Exception("no ring to move");
		
		if (towers[b].size() != 0 && towers[b].lastElement().getWidth() < towers[a].lastElement().getWidth())
			throw new Exception("Not allowed to move a small to move a big ring on the top of a small one");
		
		towers[b].push(towers[a].pop());
		
		++nbMove;
				
	} // move()
	
	/**
	 * Check if the rings are all in the last towers
	 * @return true if the game is won, false otherwise
	 */
	public boolean isWon() {
		return towers [NBTOWERS - 1].size() == getSize();
		
	} // isWon()
	
	/**
	 * Display the hanoi towers
	 */
	public void display() {
		String spaces = new String (new char[1 + size * 2]).replaceAll("\0", " ");
		int middle = size;
		
		for (int i = size; i-- != 0; ) {
			for (int j = 0; j < NBTOWERS; ++j) {
				
				StringBuilder builder = new StringBuilder(spaces);
				
				if (i < towers[j].size()) {
					String tower = towers[j].elementAt(i).toString();
					builder.replace(middle - tower.length() / 2,1 + middle + tower.length() / 2, tower);
				}
				else
					builder.setCharAt(middle, '|');
				
				System.out.print(builder.toString());
			}
			System.out.println();
		}
		
		System.out.println(new String (new char[(1 + getSize() * 2) * getSize()]).replaceAll("\0", "#"));
		
	} // display()
	
	/**
	 * Test for the hanoi game
	 * @param args : number of tower to use, default is 3
	 */
	public static void main (String[] args) {
		int size = 5;
		if (args.length == 1)
			size = Integer.parseInt(args[0]);
		
		Hanoi hanoi = new Hanoi(size);
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			while (!hanoi.isWon()) {
				hanoi.display();
				
				int a = Integer.parseInt(reader.readLine());
				int b = Integer.parseInt(reader.readLine());
				
				hanoi.move(a, b);
			}
			
			hanoi.display();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // main()
	
} // class Hanoi

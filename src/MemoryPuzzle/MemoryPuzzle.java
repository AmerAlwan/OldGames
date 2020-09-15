package MemoryPuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import EasyGraphics.*;

public class MemoryPuzzle {

	static int width = 900, height = 900;
	static Frame frame = new Frame("Memory Puzzle Game", 100, 100, width, height, false);
	static Canvas panel = new Canvas();

	public static boolean checkArray(int[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++) {
			for (int z = 0; z < puzzle[i].length; z++) {
				if (puzzle[i][z] == -1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void setup() {
		int num = 0;
		int[][] puzzle = new int[6][6];
		for (int i = 0; i < puzzle.length; i++) {
			for (int z = 0; z < puzzle[i].length; z++) {
				puzzle[i][z] = -1;
			}
		}
		while (!checkArray(puzzle)) {
			int timesDone = 0;
			while (timesDone < 2) {
				int col = (int) (Math.random() * puzzle.length);
				int row = (int) (Math.random() * puzzle[0].length);
				if (puzzle[col][row] == -1) {
					puzzle[col][row] = num;
					timesDone += 1;
				}
			}
			num += 1;
		}

		panel.setPuzzle(puzzle);
		frame.update();
	}

	public static void main(String[] args) {
		frame.add(panel);
		setup();

	}
}

class Canvas extends JPanel {

	private int[][] puzzle;
	private MemorySquare[][] squares;
	private Mouse mouse = new Mouse(this);
	private int[] exposedSquares = new int[2];
	private int[] num1 = new int[2];
	private int[] num2 = new int[2];
	private int numOfExposed = 0;

	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
		squares = new MemorySquare[puzzle.length][puzzle[0].length];
		Color[] colors = new Color[(puzzle.length * puzzle[0].length) / 2];
		for (int i = 0; i < colors.length; i++) {
			int i1 = (int) (Math.random() * 255);
			int i2 = (int) (Math.random() * 255);
			int i3 = (int) (Math.random() * 255);

			colors[i] = new Color(i1, i2, i3);
		}
		for (int i = 0; i < puzzle.length; i++) {
			for (int z = 0; z < puzzle[i].length; z++) {
				squares[i][z] = new MemorySquare((100 * i) + (i * 20) + 100, (100 * z) + (z * 20) + 75, puzzle[i][z],
						colors[puzzle[i][z]]);
				// squares[i][z].flip();
			}
		}
	}

	public void coverSquares() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				if (squares[i][j] != null) {
					squares[i][j].cover();
				}
			}
		}
	}


	@Override
	public void paintComponent(Graphics gPar) {
		super.paintComponent(gPar);
		Graphics2D g = (Graphics2D) gPar;
		setBackground(Color.WHITE);

		if (numOfExposed >= 2) {
			if (exposedSquares[0] == exposedSquares[1]) {
				System.out.println("You win");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				squares[num1[0]][num2[0]] = null;
				squares[num1[1]][num2[1]] = null;
			} else {
				System.out.println("You lose");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				coverSquares();
			}

			numOfExposed = 0;
		}

		for (int i = 0; i < squares.length; i++) {
			if (squares[i] != null) {
				for (int z = 0; z < squares[i].length; z++) {
					if (squares[i][z] != null) {
						if (mouse.isInsideRect(squares[i][z].getX(), squares[i][z].getY(), squares[i][z].getWidth(),
								squares[i][z].getHeight())) {
							if (mouse.isLeftPressed()) {
								if(numOfExposed == 1) {
									if (num1[0] != i || num2[0] != z) {
										squares[i][z].flip();
										exposedSquares[numOfExposed] = puzzle[i][z];
										num1[numOfExposed] = i;
										num2[numOfExposed] = z;
										numOfExposed += 1;
									}
								} else {
									squares[i][z].flip();
									exposedSquares[numOfExposed] = puzzle[i][z];
									num1[numOfExposed] = i;
									num2[numOfExposed] = z;
									numOfExposed += 1;
								}
								
							}
						}

						g.setColor(squares[i][z].getColor());
						g.fillRect(squares[i][z].getX(), squares[i][z].getY(), squares[i][z].getWidth(),
								squares[i][z].getHeight());
					}
				}
			}
		}

		repaint();

	}

}

class MemorySquare {
	private int type, x, y, width, height;
	private Color color, defaultColor;
	private boolean dColor = true;

	public MemorySquare(int x, int y, int type, Color color) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = 100;
		this.height = 100;
		// color = new Color(0, 0, 0);
		this.color = color;
		defaultColor = new Color(0, 0, 0);
	}

	public int getType() {
		return type;
	}

	public Color getColor() {
		return defaultColor;
	}

	public void cover() {
		dColor = true;
		defaultColor = new Color(0, 0, 0);
	}

	public void flip() {
		if (dColor) {
			dColor = false;
			defaultColor = color;
		} else {
			dColor = true;
			defaultColor = new Color(0, 0, 0);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

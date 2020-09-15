//package SlidingPuzzle;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
//import javax.swing.JPanel;
//
//import EasyGraphics.*;
//
//public class SlidingPuzzle {
//	static int width = 800, height = 800;
//	static Frame frame = new Frame("Sliding Puzzle", 100, 100, width, height, false);
//	static Canvas panel = new Canvas();
//
//	public static boolean arrayContains(int[][] array, int num) {
//		for (int i = 0; i < array.length; i++) {
//			for (int z = 0; z < array[i].length; z++) {
//				if (array[i][z] == num) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	public static void matrix(int[][] puzzle) {
//		for (int o = 0; o < puzzle.length; o++) {
//			for (int p = 0; p < puzzle[o].length; p++) {
//				System.out.print(puzzle[o][p] + String.format("%-5s", ""));
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//
//	public static void startGame() {
//
//		// System.out.println("Block");
//
//		int[][] puzzle = new int[5][5];
//		for (int i = 0; i < puzzle.length; i++) {
//			for (int z = 0; z < puzzle[i].length; z++) {
//				puzzle[i][z] = -1;
//			}
//		}
//
//		puzzle[puzzle.length - 1][puzzle[0].length - 1] = -2;
//
//		// matrix(puzzle);
//
//		while (arrayContains(puzzle, -1)) {
//			for (int i = 0; i < puzzle.length; i++) {
//				for (int z = 0; z < puzzle[i].length; z++) {
//					while (true) {
//
//						int random = (int) (Math.random() * ((puzzle.length * puzzle[0].length) - 1)) + 1;
//						if (!arrayContains(puzzle, random)) {
//							puzzle[i][z] = random;
//							// System.out.println(i + " " + z);
//							// matrix(puzzle);
//							break;
//						}
//
//						if (i == puzzle.length - 1 && z == puzzle[0].length - 1) {
//							break;
//						}
//					}
//				}
//			}
//
//		}
//
//		matrix(puzzle);
//		panel.setValues(puzzle);
//		frame.update();
//
//	}
//
//	public static void main(String[] args) {
//		frame.add(panel);
//		startGame();
//
//	}
//
//}
//
//class Canvas extends JPanel {
//
//	private int[][] puzzle;
//	private Square[][] square;
//	private Mouse mouse = new Mouse(this);
//	
//	public static void matrix(int[][] puzzle) {
//		for (int o = 0; o < puzzle.length; o++) {
//			for (int p = 0; p < puzzle[o].length; p++) {
//				System.out.print(puzzle[o][p] + String.format("%-5s", ""));
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//
//
//	public void setValues(int[][] puzzle) {
//		this.puzzle = puzzle;
//		square = new Square[puzzle.length][puzzle[0].length];
//		for (int i = 0; i < square.length; i++) {
//			for (int z = 0; z < square[i].length; z++) {
//				square[i][z] = new Square(150 + (i * 100), 120 + (z * 100), 500 / puzzle.length, 500 / puzzle.length,
//						puzzle[z][i]);
//			}
//		}
//	}
//
//	public int checki() {
//		for(int i =0; i < puzzle.length; i++) {
//			for(int z = 0; z < puzzle[i].length; z++) {
//				if(puzzle[i][z] == -2) {
//					return i;
//				}
//			}
//		}
//		return 0;
//	}
//
//	public int checkz() {
//		for(int i =0; i < puzzle.length; i++) {
//			for(int z = 0; z < puzzle[i].length; z++) {
//				if(puzzle[i][z] == -2) {
//					return z;
//				}
//			}
//		}
//		return 0;
//	}
//	
//	public void switchPuzzle(int id, int zd, int i, int z) {
//		if(i > id) {
//			
//		}
//	}
//
//	@Override
//	public void paintComponent(Graphics gPar) {
//		super.paintComponent(gPar);
//		Graphics2D g = (Graphics2D) gPar;
//		setBackground(Color.WHITE);
//
//		if (square != null && square[square.length - 1][square[0].length - 1] != null) {
//
//			g.setColor(Color.BLACK);
//			g.drawRect(square[0][0].getX(), square[0][0].getY(), square.length * 100, square[0].length * 100);
//
//			for (int i = 0; i < square.length; i++) {
//
//				for (int z = 0; z < square[i].length; z++) {
//					
//
//						if (mouse.isInsideRect(square[i][z].getX(), square[i][z].getY(), square[i][z].getWidth(),
//								square[i][z].getHeight())) {
//							if (mouse.isLeftPressed()) {
//								switchPuzzle(puzzle[i][z], puzzle[checki(i, z)][checkz(i,z)], i, z, checki(i,z), checkz(i,z));
//								matrix(puzzle);
//							}
//						}
//
//						g.drawImage(square[i][z].getImg().getImage(), square[i][z].getX(), square[i][z].getY(),
//								square[i][z].getWidth(), square[i][z].getHeight(), this);
//
//						// System.out.println("dd");
//					
//				}
//			}
//		}
//		repaint();
//	}
//}
//
//class Square {
//
//	private int x, y, width, height, num;
//	private Picture image;
//
//	public Square(int x, int y, int width, int height, int num) {
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
//		this.num = num;
//		this.image = new Picture(
//				"C:/Users/amera/eclipse-workspace/Games/src/SlidingPuzzle/imgs/" + String.valueOf(this.num) + ".png");
//	}
//
//	public Picture getImg() {
//		return image;
//	}
//
//	public int getX() {
//		return x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public int getNum() {
//		return num;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}
//
//}

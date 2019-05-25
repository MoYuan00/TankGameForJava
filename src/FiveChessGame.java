
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
/**
 * 
 * @author sky
 *
 */
public class FiveChessGame {
	/**
	 * 控件属性
	 */
	private JFrame frame; // 五子棋游戏的窗口框架
	private GameMap map; // 五子棋游戏的窗口容器
 
	/**
	 * 静态数据属性
	 */
	private static final int BOARD_SIZE = 15; // 棋盘大小(15 * 15)
	private static final int ROW_WIDTH = 36; // 间距
	private static final int SPACE = ROW_WIDTH / 2; // 上下边间距
 
	public FiveChessGame() {
		frame = new JFrame("五子棋"); // 实现五子棋游戏窗口框架
		map = new GameMap(); // 实现五子棋游戏窗口容器
		
//		map.setPreferredSize(new Dimension(ROW_WIDTH * (BOARD_SIZE - 1) + SPACE
//				* 2, ROW_WIDTH * (BOARD_SIZE - 1) + SPACE * 2));
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				String str = "是否要退出游戏?";
//				// 添加消息对话框
//				if (JOptionPane.showConfirmDialog(null, str, "退出游戏",
//						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//					System.exit(0); // 退出
//				}
//			}
//		});
		frame.add(map);
		frame.pack();
		frame.setLocation(250, 50);
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
 
	/**
	 * 内部容器类，用于实现图像处理
	 */
	private class GameMap extends JPanel {
		private static final long serialVersionUID = 16578987565248L;
 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 棋盘
			g.setColor(new Color(200, 100, 50)); // 设为桔黄色
			g.fillRect(0, 0, ROW_WIDTH * (BOARD_SIZE + 1), ROW_WIDTH
					* (BOARD_SIZE + 1)); // 填充棋盘
		}
	}
 
	public static void main(String[] args) {
		FiveChessGame game = new FiveChessGame();
	}
}
 
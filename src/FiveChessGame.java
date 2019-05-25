
 
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
	 * �ؼ�����
	 */
	private JFrame frame; // ��������Ϸ�Ĵ��ڿ��
	private GameMap map; // ��������Ϸ�Ĵ�������
 
	/**
	 * ��̬��������
	 */
	private static final int BOARD_SIZE = 15; // ���̴�С(15 * 15)
	private static final int ROW_WIDTH = 36; // ���
	private static final int SPACE = ROW_WIDTH / 2; // ���±߼��
 
	public FiveChessGame() {
		frame = new JFrame("������"); // ʵ����������Ϸ���ڿ��
		map = new GameMap(); // ʵ����������Ϸ��������
		
//		map.setPreferredSize(new Dimension(ROW_WIDTH * (BOARD_SIZE - 1) + SPACE
//				* 2, ROW_WIDTH * (BOARD_SIZE - 1) + SPACE * 2));
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				String str = "�Ƿ�Ҫ�˳���Ϸ?";
//				// �����Ϣ�Ի���
//				if (JOptionPane.showConfirmDialog(null, str, "�˳���Ϸ",
//						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//					System.exit(0); // �˳�
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
	 * �ڲ������࣬����ʵ��ͼ����
	 */
	private class GameMap extends JPanel {
		private static final long serialVersionUID = 16578987565248L;
 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// ����
			g.setColor(new Color(200, 100, 50)); // ��Ϊ�ۻ�ɫ
			g.fillRect(0, 0, ROW_WIDTH * (BOARD_SIZE + 1), ROW_WIDTH
					* (BOARD_SIZE + 1)); // �������
		}
	}
 
	public static void main(String[] args) {
		FiveChessGame game = new FiveChessGame();
	}
}
 
package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

public class TelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int i = 10;
					while (i > 1) {
						TelaPrincipal window = new TelaPrincipal();
						JFrame frame = window.frame;
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setSize(300, 200);
						Dimension screenSize = getTelaDimension();
						int randomX = getRandomPosition(screenSize.width, frame.getWidth());
						int randomY = getRandomPosition(screenSize.height, frame.getHeight());
						frame.setLocation(randomX, randomY);

						frame.setVisible(true);
						i += 1;
						if (i % 5 == 0)
							Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static Dimension getTelaDimension() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		return toolkit.getScreenSize();
	}

	private static int getRandomPosition(int screenSize, int frameSize) {
		Random random = new Random();
		int maxPosition = screenSize - frameSize;
		return random.nextInt(maxPosition);
	}

}

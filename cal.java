package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * {@code cal} 클래스는 간단한 계산기 애플리케이션을 GUI로 구현한 것입니다. 이 클래스는 {@link JFrame}을 확장하고
 * {@link ActionListener}를 구현하여 버튼의 동작을 처리합니다. 계산기는 다양한 메모리, 산술 및 유틸리티 기능을
 * 포함합니다.
 */
public class cal extends JFrame implements ActionListener {

	/**
	 * 메모리 작업용 버튼의 레이블 배열.
	 */
	String[] items0 = { "MC", "MR", "M+", "M-", "MS", "M" };

	/**
	 * 계산기 버튼의 레이블을 위한 2차원 배열.
	 */
	String[][] items = { { "%", "CE", "C", "Back" }, { "1/x", "x2", "√", "÷" }, { "7", "8", "9", "X" },
			{ "4", "5", "6", "-" }, { "1", "2", "3", "+" }, { "+/-", "0", ".", "=" } };

	/**
	 * 메모리 작업 버튼 배열.
	 */
	JButton[] buts0 = new JButton[6];

	/**
	 * 계산기의 기능 및 숫자용 버튼 2차원 배열.
	 */
	JButton[][] buts = new JButton[6][4];

	/**
	 * 현재 입력과 결과를 표시하는 텍스트 필드.
	 */
	JTextField tf = new JTextField(20);

	/**
	 * 새로운 {@code cal} 계산기 애플리케이션을 생성합니다. JFrame의 제목, 레이아웃, 텍스트 필드 및 버튼 패널을 설정합니다.
	 */
	public cal() {
		setTitle("계산기");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLayout(new BorderLayout());

		// 텍스트 필드를 초기화하고 설정
		tf.setFont(new Font("굴림", Font.BOLD, 40));
		tf.setBackground(Color.LIGHT_GRAY);
		add(tf, BorderLayout.NORTH);
		tf.enable(false); // 텍스트 필드를 편집 불가로 설정

		// 버튼을 위한 메인 패널 생성 및 JFrame에 추가
		JPanel pn2 = new JPanel(new BorderLayout());
		add(pn2, BorderLayout.CENTER);

		// 메모리 작업 패널 생성
		JPanel pnM = new JPanel(new GridLayout(1, 5));
		for (int i = 0; i < buts0.length; i++) {
			buts0[i] = new JButton(items0[i]);
			buts0[i].setBackground(Color.LIGHT_GRAY);
			pnM.add(buts0[i]);
		}
		pn2.add(pnM, BorderLayout.NORTH);

		// 메인 계산기 버튼 패널 생성
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(6, 4));
		pn2.add(pn, BorderLayout.CENTER);

		// 기능 및 숫자 버튼을 패널에 추가하고, 액션 리스너를 연결
		for (int i = 0; i < buts.length; i++) {
			for (int j = 0; j < buts[i].length; j++) {
				buts[i][j] = new JButton(items[i][j]);
				buts[i][j].setBackground(Color.white);
				pn.add(buts[i][j]);
				buts[i][j].addActionListener(this);
			}
		}

		// "equals" 버튼을 별도의 색상으로 설정
		buts[buts.length - 1][buts[0].length - 1].setBackground(Color.BLUE);
		buts[buts.length - 1][buts[0].length - 1].setForeground(Color.WHITE);
	}

	/**
	 * 버튼 클릭 이벤트를 처리합니다. 이 메서드는 계산기의 모든 버튼 동작에 응답합니다.
	 * 
	 * @param e 버튼 클릭에 의해 발생한 {@code ActionEvent}.
	 */
	public void actionPerformed(ActionEvent e) {
		// 버튼 동작을 위한 플레이스홀더
	}

	/**
	 * 계산기 애플리케이션을 실행하는 메인 메서드입니다. {@code cal} 객체를 초기화하고 표시합니다.
	 * 
	 * @param args 커맨드라인 인수 (사용되지 않음).
	 */
	public static void main(String[] args) {
		cal c = new cal();
		c.setVisible(true);
	}
}

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
 * {@code Calculator} 클래스는 간단한 GUI 기반 계산기 프로그램을 구현합니다. 사칙연산과 몇 가지 추가 기능을 포함하며,
 * Java Swing을 사용하여 GUI를 구성합니다.
 */
public class Calculator extends JFrame implements ActionListener {
	/** 메모리 버튼 레이블 배열 */
	String[] items0 = { "MC", "MR", "M+", "M-", "MS", "M" };

	/** 계산기 버튼 레이블 2차원 배열 */
	String[][] items = { { "%", "CE", "C", "Back" }, { "1/x", "x²", "√", "÷" }, { "7", "8", "9", "×" },
			{ "4", "5", "6", "-" }, { "1", "2", "3", "+" }, { "+/-", "0", ".", "=" } };

	/** 메모리 기능 버튼 배열 */
	JButton[] memoryButtons = new JButton[6];

	/** 숫자 및 기능 버튼 배열 */
	JButton[][] functionButtons = new JButton[6][4];

	/** 현재 입력과 결과를 표시하는 텍스트 필드 */
	JTextField displayField = new JTextField(20);

	/** 첫 번째 피연산자 */
	private double firstOperand = 0;

	/** 두 번째 피연산자 */
	private double secondOperand = 0;

	/** 선택된 연산자 */
	private String operator = "";

	/** 새로운 숫자 입력 상태 플래그 */
	private boolean startNewNumber = true;

	/**
	 * 계산기 애플리케이션의 GUI를 초기화하고 설정합니다. 텍스트 필드와 버튼 패널을 구성합니다.
	 */
	public Calculator() {
		setTitle("계산기");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLayout(new BorderLayout());

		// 텍스트 필드 초기화 및 설정
		displayField.setFont(new Font("굴림", Font.BOLD, 40));
		displayField.setBackground(Color.LIGHT_GRAY);
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		displayField.setEditable(false);
		add(displayField, BorderLayout.NORTH);

		// 메모리 버튼 패널 생성
		JPanel memoryPanel = new JPanel(new GridLayout(1, 5));
		for (int i = 0; i < memoryButtons.length; i++) {
			memoryButtons[i] = new JButton(items0[i]);
			memoryButtons[i].setBackground(Color.LIGHT_GRAY);
			memoryPanel.add(memoryButtons[i]);
		}
		JPanel mainPanel = new JPanel(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(memoryPanel, BorderLayout.NORTH);

		// 계산기 버튼 패널 생성
		JPanel buttonPanel = new JPanel(new GridLayout(6, 4));
		for (int i = 0; i < functionButtons.length; i++) {
			for (int j = 0; j < functionButtons[i].length; j++) {
				functionButtons[i][j] = new JButton(items[i][j]);
				functionButtons[i][j].setBackground(Color.WHITE);
				functionButtons[i][j].addActionListener(this);
				buttonPanel.add(functionButtons[i][j]);
			}
		}
		// = 버튼 색상 설정
		functionButtons[5][3].setBackground(Color.BLUE);
		functionButtons[5][3].setForeground(Color.WHITE);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	/**
	 * 버튼 클릭 이벤트를 처리합니다. 숫자, 소수점, 연산자 및 기능 버튼에 따라 텍스트 필드와 계산 결과를 업데이트합니다.
	 *
	 * @param e 버튼 클릭으로 인해 발생한 {@code ActionEvent} 객체.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		// 숫자와 소수점 처리
		if (command.matches("\\d") || command.equals(".")) {
			if (startNewNumber) {
				displayField.setText(command);
				startNewNumber = false;
			} else {
				displayField.setText(displayField.getText() + command);
			}
		}
		// 연산자 처리
		else if (command.equals("+") || command.equals("-") || command.equals("×") || command.equals("÷")) {
			firstOperand = Double.parseDouble(displayField.getText());
			operator = command;
			startNewNumber = true;
		}
		// "=" 버튼 처리 (계산 수행)
		else if (command.equals("=")) {
			secondOperand = Double.parseDouble(displayField.getText());
			double result = 0;
			switch (operator) {
			case "+":
				result = firstOperand + secondOperand;
				break;
			case "-":
				result = firstOperand - secondOperand;
				break;
			case "×":
				result = firstOperand * secondOperand;
				break;
			case "÷":
				if (secondOperand != 0)
					result = firstOperand / secondOperand;
				else
					displayField.setText("Error");
				return;
			}
			displayField.setText(String.valueOf(result));
			startNewNumber = true;
		}
		// C 버튼 처리 (모든 입력 초기화)
		else if (command.equals("C")) {
			displayField.setText("");
			firstOperand = 0;
			secondOperand = 0;
			operator = "";
			startNewNumber = true;
		}
		// Back 버튼 처리 (마지막 입력 삭제)
		else if (command.equals("Back")) {
			String currentText = displayField.getText();
			displayField.setText(currentText.length() > 0 ? currentText.substring(0, currentText.length() - 1) : "");
		}
	}

	/**
	 * 계산기 애플리케이션을 실행하는 메인 메서드. {@code Calculator} 객체를 생성하고 보이도록 설정합니다.
	 *
	 * @param args 커맨드라인 인수 (사용되지 않음).
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setVisible(true);
	}
}

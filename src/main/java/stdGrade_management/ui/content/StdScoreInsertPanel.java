package stdGrade_management.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.service.ScoreService;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.exception.InvalidCheckException;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class StdScoreInsertPanel extends JPanel {
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;
	private JTextField tfSoc;
	private JTextField tfSci;
	private ScoreService ScoreService;
	private StudentScoreViewService ViewService;

	public StdScoreInsertPanel() {
		initialize();
	}
	
	public void setViewService(StudentScoreViewService viewService) {
		ViewService = viewService;
	}

	private void initialize() {
		setBorder(new EmptyBorder(0, 0, 0, 30));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblKor = new JLabel("국어");
		lblKor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblKor);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		add(tfKor);
		
		JLabel lblEng = new JLabel("영어");
		lblEng.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEng);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		add(tfEng);
		
		JLabel lblMath = new JLabel("수학");
		lblMath.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMath);
		
		tfMath = new JTextField();
		tfMath.setColumns(10);
		add(tfMath);
		
		JLabel lblSoc = new JLabel("사회");
		lblSoc.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSoc);
		
		tfSoc = new JTextField();
		tfSoc.setColumns(10);
		add(tfSoc);
		
		JLabel lblSci = new JLabel("과학");
		lblSci.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSci);
		
		tfSci = new JTextField();
		tfSci.setColumns(10);
		add(tfSci);
	}
	
	public void setItem(StudentScoreView item) {
		tfKor.setText(item.getKor() + "");
		tfEng.setText(item.getEng() + "");
		tfMath.setText(item.getMath() + "");
		tfSoc.setText(item.getSoc() + "");
		tfSci.setText(item.getSci() + "");
	}
	
	public StudentScoreView getItem() {
		validCheck();
		int kor = Integer.parseInt(tfKor.getText().trim());
		int eng = Integer.parseInt(tfEng.getText().trim());
		int math = Integer.parseInt(tfMath.getText().trim());
		int soc = Integer.parseInt(tfSoc.getText().trim());
		int sci = Integer.parseInt(tfSci.getText().trim());
		
		return new StudentScoreView(kor, eng, math, soc, sci);
	}

	public void validCheck() {
		if (tfKor.getText().equals("") || tfEng.getText().equals("")
				|| tfMath.getText().equals("")
				|| tfSoc.getText().equals("")
				|| tfSci.getText().equals("")){
			throw new InvalidCheckException();
		}
	}
	
	public void clearTf() {
		tfKor.setText("");
		tfEng.setText("");
		tfMath.setText("");
		tfSoc.setText("");
		tfSci.setText("");
	}

	public JTextField getTfKor() {
		return tfKor;
	}

	public void setTfKor(JTextField tfKor) {
		this.tfKor = tfKor;
	}

	public JTextField getTfEng() {
		return tfEng;
	}

	public void setTfEng(JTextField tfEng) {
		this.tfEng = tfEng;
	}

	public JTextField getTfMath() {
		return tfMath;
	}

	public void setTfMath(JTextField tfMath) {
		this.tfMath = tfMath;
	}

	public JTextField getTfSoc() {
		return tfSoc;
	}

	public void setTfSoc(JTextField tfSoc) {
		this.tfSoc = tfSoc;
	}

	public JTextField getTfSci() {
		return tfSci;
	}

	public void setTfSci(JTextField tfSci) {
		this.tfSci = tfSci;
	}
	
	
}

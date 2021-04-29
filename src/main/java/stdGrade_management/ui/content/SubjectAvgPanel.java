package stdGrade_management.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SubjectAvgPanel extends JPanel {
	private JLabel lblAvg;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;
	private JTextField tfSoc;
	private JTextField tfSci;
	private JTextField tfAvg;
	private JPanel panel;

	public SubjectAvgPanel() {
		
		initialize();
	}
	private void initialize() {
		setBorder(new EmptyBorder(0, 20, 0, 20));
		setLayout(new GridLayout(0, 8, 10, 0));
		
		lblAvg = new JLabel("과목별평균점수");
		add(lblAvg);
		lblAvg.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel = new JPanel();
		add(panel);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		add(tfKor);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		add(tfEng);
		
		tfMath = new JTextField();
		tfMath.setColumns(10);
		add(tfMath);
		
		tfSoc = new JTextField();
		tfSoc.setColumns(10);
		add(tfSoc);
		
		tfSci = new JTextField();
		tfSci.setColumns(10);
		add(tfSci);
		
		tfAvg = new JTextField();
		add(tfAvg);
		tfAvg.setColumns(10);
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
	public JTextField getTfAvg() {
		return tfAvg;
	}
	public void setTfAvg(JTextField tfAvg) {
		this.tfAvg = tfAvg;
	}
	

}

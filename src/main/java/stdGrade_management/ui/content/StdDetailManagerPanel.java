package stdGrade_management.ui.content;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentDetail;
import stdGrade_management.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class StdDetailManagerPanel extends AbstractContentPanel<StudentDetail> implements ActionListener{
	//int stdNo; boolean gender; Date birthday; byte[] pic;
	
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	
	private JLabel lblPic;
	private JTextField tfStdNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JDateChooser dateBirthday;
	
	public StdDetailManagerPanel() {
		initialize();
		loadPic(null);
	}
	
	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if (imgFilePath == null) {
				ImageIcon icon = new ImageIcon(imgPath + "noImg.jpg");
				changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
				ImageIcon icon = new ImageIcon(imgFilePath);
				changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon changeIcon = new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
	}
	
	private void initialize() {
		setBorder(new TitledBorder(null, "학생 상세정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel("");
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.NORTH);
		
		btnAddPic = new JButton("사진 추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblStdNo = new JLabel("학번");
		lblStdNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblStdNo);
		
		tfStdNo = new JTextField();
		tfStdNo.setEditable(false);
		pContent.add(tfStdNo);
		tfStdNo.setColumns(10);
		
		JLabel lblBirthday = new JLabel("생일");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblBirthday);
		
		dateBirthday = new JDateChooser();
		pContent.add(dateBirthday);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		pGender.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rdbtnMale = new JRadioButton("남자");
		rdbtnMale.setSelected(true);
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("여자");
		buttonGroup.add(rdbtnFemale);
		pGender.add(rdbtnFemale);
	}

	public void setTfStdNo(Student stdNo) {
		tfStdNo.setText(String.valueOf(stdNo.getStdNo()));
	}
	
	@Override
	public void setItem(StudentDetail item) {
		tfStdNo.setText(String.valueOf(item.getStdNo()));
		byte[] iconBytes = item.getPic();
		ImageIcon icon = new ImageIcon(iconBytes);
		lblPic.setIcon(icon);
		
		dateBirthday.setDate(item.getBirthday());
		if (item.isGender()) {
			rdbtnMale.setSelected(true);
		}else {
			rdbtnFemale.setSelected(true);
		}
	}

	@Override
	public StudentDetail getItem() {
		validCheck();
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		boolean gender = rdbtnMale.isSelected() ? true : false;
		Date birthday = dateBirthday.getDate();
		byte[] pic = getImage();
		return new StudentDetail(stdNo, gender, birthday, pic);
	}

	private byte[] getImage() {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		
			//icon -> image로 만듬
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();
		
			ImageIO.write(bi, "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void validCheck() {
		if (dateBirthday.getDate().equals(null) ) {
			throw new InvalidCheckException();
		}
	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateBirthday.setDate(new Date());
		rdbtnMale.setSelected(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"jpg & png & gif images", "jpg", "png", "gif");
		
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(
						null,
						"파일을 선택하지 않았습니다.",
						"경고 메세지",
						JOptionPane.WARNING_MESSAGE);
			return;		
		}
		
		String ChooseFilePath = chooser.getSelectedFile().getPath();
		loadPic(ChooseFilePath);
	}
}

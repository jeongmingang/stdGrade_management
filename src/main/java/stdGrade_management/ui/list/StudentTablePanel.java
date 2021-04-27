package stdGrade_management.ui.list;

import javax.swing.SwingConstants;

import stdGrade_management.dto.Student;
import stdGrade_management.service.StudentService;
import stdGrade_management.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class StudentTablePanel extends AbstractCustomTablePanel<Student> {
	public StudentTablePanel() {
	}
	private StudentService service;

	public void setService(StudentService service) {
		this.service = service;
	}

	@Override
	public Student getItem() {
		int row = table.getSelectedRow();
		int stdNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Student(stdNo)));
	}

	@Override
	public void initList() {
		list = service.showStudents();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼별 내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 150, 100);
	}

	@Override
	public Object[] toArray(Student t) {
		return new Object[] { t.getStdNo(), t.getStdName(), t.getBan() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "학번", "이름", "분반" };
	}

}

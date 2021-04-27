package stdGrade_management.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import stdGrade_management.dto.Student;
import stdGrade_management.dto.StudentScoreView;
import stdGrade_management.service.StudentScoreViewService;
import stdGrade_management.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class StdScoreTablePanel extends AbstractCustomTablePanel<StudentScoreView> {
	private StudentScoreViewService service;

	public void setService(StudentScoreViewService service) {
		this.service = service;
	}

	@Override
	public StudentScoreView getItem() {
		int row = table.getSelectedRow();
		int stdNo = (int) table.getValueAt(row, 0);
		
		if (row == -1) {
			throw new NotSelectedException();
		}
		
		return list.get(list.indexOf(new StudentScoreView(new Student(stdNo))));
	}

	@Override
	public void initList() {
		list = service.showStdScores();
	}
	public void setInitList(List<StudentScoreView> stdScoreView) {
		list = stdScoreView;
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼별 내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6, 7);
		
		// 컬럼별 너비 조정
		setTableCellWidth(100, 150, 100 ,100, 100, 100, 100, 100);	
	}

	@Override
	public Object[] toArray(StudentScoreView t) {
		return new Object[] {
						t.getStdNo().getStdNo(),
						t.getStdName(),
						t.getBan(),
						t.getKor(),
						t.getEng(),
						t.getMath(),
						t.getSoc(),
						t.getSci(),
					};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"학번", "이름", "분반", "국어", "영어", "수학", "사회", "과학"};
	}

}

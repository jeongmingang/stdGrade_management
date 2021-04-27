package stdGrade_management.ui.list;

import javax.swing.SwingConstants;

import stdGrade_management.dto.Ban;
import stdGrade_management.service.BanService;
import stdGrade_management.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class BanTablePanel extends AbstractCustomTablePanel<Ban> {
	private BanService service;
	
	public BanTablePanel() {
	}

	public void setService(BanService service) {
		this.service = service;
	}

	@Override
	public Ban getItem() {
		int row = table.getSelectedRow();
		String ban = (String) table.getValueAt(row, 0);
		
		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Ban(ban)));
	}
	
	@Override
	public void initList() {
		list = service.showBans();
	}

	@Override
	protected void setAlignAndWidth() {
		//컬럼 내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0);
						
		//컬럼별 너비 조정
		setTableCellWidth(100);
	}

	@Override
	public Object[] toArray(Ban t) {
		return new Object[] {t.getBan()};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"분반"};
	}
}

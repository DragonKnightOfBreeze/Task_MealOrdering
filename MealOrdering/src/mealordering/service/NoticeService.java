package mealordering.service;

import mealordering.dao.DaoFactory;
import mealordering.dao.NoticeDao;
import mealordering.domain.Notice;
import mealordering.domain.annotations.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 公告的服务类
 */
public class NoticeService {
	public NoticeDao dao = DaoFactory.getNoticeDao();

	/**
	 * 添加公告。
	 * @param notice 公告信息
	 */
	@Permission(Permission.P.Admin)
	public void doAdd(@NotNull Notice notice) {
		try {
			dao.doAdd(notice);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑通知信息。
	 * @param notice 公告信息
	 */
	@Permission(Permission.P.Admin)
	public void doEdit(@NotNull Notice notice) {
		try {
			dao.doEdit(notice);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Id删除公告。
	 * @param id 公告Id
	 */
	@Permission(Permission.P.Admin)
	public void doDeleteById(int id) {
		try {
			dao.doDeleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Id查询公告。
	 * @param id 公告Id
	 */
	@Permission(Permission.P.Admin)
	public Notice findById(int id) {
		Notice notice = null;
		try {
			notice = dao.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * 查询所有公告，按照时间降序排列。
	 */
	@Permission(Permission.P.All)
	public List<Notice> findAll(int pageIndex, int count) {
		List<Notice> notice = null;
		try {
			notice = dao.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * 查询最近添加或修改的一条公告。
	 */
	@Permission(Permission.P.All)
	public Notice findLatest() {
		Notice notice = null;
		try {
			notice = dao.findRecent(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return notice;
	}
}

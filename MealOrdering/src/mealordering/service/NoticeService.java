package mealordering.service;

import dkbreeze.utils.ext.ListExt;
import mealordering.dao.DaoFactory;
import mealordering.dao.NoticeDao;
import mealordering.domain.Notice;
import mealordering.exception.ResultEmptyException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
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
	public void doAdd(@NotNull Notice notice) throws SQLException {
		dao.doAdd(notice);
	}

	/**
	 * 编辑通知信息。
	 * @param notice 公告信息
	 */
	public void doEdit(@NotNull Notice notice) throws SQLException {
		dao.doEdit(notice);
	}

	/**
	 * 根据Id删除公告。
	 * @param id 公告Id
	 */
	public void doDeleteById(int id) throws SQLException {
		dao.doDeleteById(id);
	}

	/**
	 * 根据Id查询公告。
	 * @param id 公告Id
	 */
	public Notice findById(int id) throws SQLException, ResultEmptyException {
		Notice notice = dao.findById(id);
		if(notice == null)
			throw new ResultEmptyException();
		return notice;
	}

	/**
	 * 查询指定数量的最近添加或修改的公告。
	 */
	public List<Notice> findRecent(int findCount) throws SQLException, ResultEmptyException {
		List<Notice> noticeList = dao.findRecent(findCount);
		if(ListExt.orEmpty(noticeList))
			throw new ResultEmptyException();
		return noticeList;
	}

	/**
	 * 查询所有公告，按照时间降序排列。
	 */
	public List<Notice> findAll() throws SQLException, ResultEmptyException {
		List<Notice> noticeList = dao.findAll();
		if(ListExt.orEmpty(noticeList))
			throw new ResultEmptyException();
		return noticeList;
	}

	/**
	 * 根据公告标题进行模糊搜索。
	 */
	public List<Notice> searchByTitle(@NotNull String title) throws SQLException, ResultEmptyException {
		List<Notice> noticeList = dao.searchByTitle(title);
		if(ListExt.orEmpty(noticeList))
			throw new ResultEmptyException();
		return noticeList;
	}
}

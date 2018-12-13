package mealordering.service;

import mealordering.dao.DaoFactory;
import mealordering.dao.NoticeDao;
import mealordering.domain.BeanPage;
import mealordering.domain.Notice;
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
	 * 查询最近添加或修改的一条公告。
	 */
	public Notice findLatest() {
		Notice notice = null;
		try {
			notice = dao.findRecent(1).get(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * 查询指定数量的最近添加或修改的公告。
	 */
	public BeanPage<Notice> findInPage(int findCount, int pageIndex, int count) {
		BeanPage<Notice> noticePage = null;
		try {
			List<Notice> noticeList = dao.findRecent(findCount);
			noticePage = new BeanPage<>(pageIndex, count, noticeList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return noticePage;
	}

	/**
	 * 查询所有公告，按照时间降序排列，分页显示。
	 */
	public BeanPage<Notice> findAllInPage(int pageIndex, int count) {
		BeanPage<Notice> noticePage = null;
		try {
			List<Notice> noticeList = dao.findAll();
			noticePage = new BeanPage<>(pageIndex, count, noticeList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return noticePage;
	}

	/**
	 * TODO
	 */
	public BeanPage<Notice> searchByTitleInPage(@NotNull String title, int pageIndex, int count) {
		BeanPage<Notice> noticePage = null;
		try {
			List<Notice> noticeList = dao.searchByTitle(title, pageIndex, count);
			noticePage = new BeanPage<>(pageIndex, count, noticeList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return noticePage;
	}


}

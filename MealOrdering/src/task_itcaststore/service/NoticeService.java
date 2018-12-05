package task_itcaststore.service;

import task_itcaststore.dao.NoticeDao;
import task_itcaststore.domain.Notice;

import java.util.List;

/**
 * 通知的服务类
 */
public class NoticeService {
	private NoticeDao dao = new NoticeDao();

	/**
	 * 后台系统，查询所有公告。
	 */
	public List<Notice> findAllNotices() {
		try {
			return dao.getAllNotices();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：查询所有公告失败！");
		}
	}

	/**
	 * 后台系统，添加公告。
	 */
	public void addNotice(Notice notice) {
		try {
			dao.addNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：添加公告失败！");
		}
	}

	/**
	 * 后台系统，根据id查找公告。
	 */
	public Notice findNoticeById(String n_id) {
		try {
			return dao.findNoticeById(n_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：查询公告失败！");
		}
	}

	/**
	 * 后台系统，根据id修改公告。
	 */
	public void updateNotice(Notice notice) {
		try {
			dao.updateNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：修改公告失败！");
		}
	}

	/**
	 * 后台系统，根据id删除公告。
	 */
	public void deleteNoticeById(String n_id) {
		try {
			dao.deleteNoticeById(n_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：删除公告失败！");
		}
	}

	/**
	 * 前台系统，查询最新添加或修改的一条公告。
	 */
	public Notice getRecentNotice() {
		try {
			return dao.getRecentNotice();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("警告：查询公告失败！");
		}
	}
}

package com.renren.infra.xweb.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.renren.infra.xweb.entity.Mario_server_state;
import com.renren.infra.xweb.repository.Mario_server_stateMybatisDao;

@Component
@Transactional
public class Mario_server_stateService {

	@Autowired
	private Mario_server_stateMybatisDao mario_server_stateDao;

	/**
	 * 创建分页查询.
	 * 
	 * @param filterParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return Page<Test>
	 */
	public Page<Mario_server_state> getMario_server_state(
			Map<String, Object> filterParams, int pageNumber, int pageSize) {

		List<Mario_server_state> mario_server_states = mario_server_stateDao
				.find(filterParams, buildRowBounds(pageNumber, pageSize));
		int totalnum = mario_server_stateDao.findTotalNum(filterParams);

		return new PageImpl<Mario_server_state>(mario_server_states,
				new PageRequest(pageNumber - 1, pageSize), totalnum);
	}

	/**
	 * 查找所有的Mario_server_state.
	 * 
	 * @return List<Mario_server_state>
	 */
	public List<Mario_server_state> getAllMario_server_state() {
		return mario_server_stateDao.findAll();
	}

	/**
	 * 创建分页请求.
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return RowBounds
	 */
	private RowBounds buildRowBounds(int pageNumber, int pageSize) {
		return new RowBounds((pageNumber - 1) * pageSize, pageSize);
	}

	public List<Mario_server_state> getMario_server_state(int server_id,
			Long start_time_stamp, Long end_time_stamp) {
		return mario_server_stateDao.findBetweenTimeStamp(server_id,
				start_time_stamp, end_time_stamp);
	}

	public Mario_server_state getLastMario_server_state(int server_id) {
		return mario_server_stateDao.findLastStateByServerId(server_id);
	}
}

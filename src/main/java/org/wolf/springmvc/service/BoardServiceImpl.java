package org.wolf.springmvc.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolf.springmvc.dao.BoardDao;
import org.wolf.springmvc.domain.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List<Map<String, Object>> list() {
		return boardDao.list();
	}
}

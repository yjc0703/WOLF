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
	
	public List<BoardVO> list() {
		return boardDao.list();
	}

	@Override
	public int delete(int seq) {
		return boardDao.delete(seq);
	}

	@Override
	public BoardVO read(int seq) {
		boardDao.updateReadCount(seq);
		return boardDao.select(seq);
	}

	@Override
	public int edit(BoardVO boardVO) {
		return boardDao.update(boardVO);
	}

	@Override
	public void write(BoardVO boardVO) {
		boardDao.inert(boardVO);		
	}
}

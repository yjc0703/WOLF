package org.wolf.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wolf.springmvc.domain.BoardVO;

public interface BoardService {
	public abstract List<BoardVO> list();
	
	public abstract int delete(int seq);

	public abstract BoardVO read(int seq);

	public abstract int edit(BoardVO boardVO);

	public abstract void write(BoardVO boardVO);
}

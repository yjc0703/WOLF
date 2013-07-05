package org.wolf.springmvc.dao;

import java.util.List;

import org.wolf.springmvc.domain.BoardVO;

public interface BoardDao {
	public abstract List<BoardVO> list();

	public abstract int delete(int seq);

	public abstract int update(BoardVO boardVO);

	public abstract BoardVO inert(BoardVO boardVO);

	public abstract BoardVO select(int seq);
}

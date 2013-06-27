package org.wolf.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wolf.springmvc.domain.BoardVO;

public interface BoardDao {
	public List<Map<String, Object>> list();
	
	//public List<BoardVO> list();
}

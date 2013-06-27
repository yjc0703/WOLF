package org.wolf.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wolf.springmvc.domain.BoardVO;

public interface BoardService {
	public List<Map<String, Object>> list();
}

package com.springboot.board.service;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.dto.BoardFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;


public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception;
	
	Long insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	Long updateBoard(BoardDto board) throws Exception;

	Long deleteBoard(int boardIdx) throws Exception;

	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception;
}

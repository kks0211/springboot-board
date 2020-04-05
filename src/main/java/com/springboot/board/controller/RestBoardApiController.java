package com.springboot.board.controller;


import com.springboot.board.dto.BoardDto;
import com.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RestBoardApiController {

	private final BoardService boardService;
	
	/*@GetMapping("/api/board")
	public List<BoardDto> openBoardList() throws Exception{
		return boardService.selectBoardList();
	}*/

	@PostMapping("/api/board/write")
	public Long insertBoard(@RequestBody BoardDto board) throws Exception{
		return boardService.insertBoard(board, null);
	}
	
	@GetMapping("/api/board/{boardIdx}")
	public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
		return boardService.selectBoardDetail(boardIdx);
	}
	
	@PutMapping("/api/board/{boardIdx}")
	public Long updateBoard(@RequestBody BoardDto board) throws Exception{
		return boardService.updateBoard(board);
		//return "redirect:/api/board";
	}
	
	@DeleteMapping("/api/board/{boardIdx}")
	public Long deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
		return boardService.deleteBoard(boardIdx);
		//return "redirect:/api/board";
	}
}

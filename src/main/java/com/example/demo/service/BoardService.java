package com.example.demo.service;

import com.example.demo.domain.entity.Board;
import com.example.demo.domain.repository.BoardRepository;
import com.example.demo.dto.BoardDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}


	@Transactional(readOnly = true)
	public Page<Board> pageList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}


	@Transactional
	public BoardDto getPost(Long id) {
		Board board = boardRepository.findById(id).get();

		BoardDto boardDto = BoardDto.builder()
			.id(board.getId())
			.study_project(board.getStudy_project())
			.person_num(board.getPerson_num())
			.online_offline(board.getOnline_offline())
			.duration(board.getDuration())
			.skill(board.getSkill())
			.date(board.getDate())
			.calling(board.getCalling())
			.title(board.getTitle())
			.input_content(board.getInput_content())
			.createdDate(board.getCreatedDate())
			.build();
		return boardDto;
	}

	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}

	/* 회원가입 시, 유효성 체크 */
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();

		/* 유효성 검사에 실패한 필드 목록을 받음 */
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}
}


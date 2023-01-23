package com.example.demo.controller;


import com.example.demo.domain.entity.Board;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@Controller
public class BoardController {


    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model, @PageableDefault(size = 2 , sort = "id", direction = Sort.Direction.ASC)
    Pageable pageable) {
        Page<Board> postList = boardService.pageList(pageable);
        int startPage = Math.max(1, postList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(postList.getTotalPages(), postList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("postList", postList);
        return "board/list";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit";
    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }







}

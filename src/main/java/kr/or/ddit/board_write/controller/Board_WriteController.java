package kr.or.ddit.board_write.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.bd_inquiry.model.Bd_InquiryVo;
import kr.or.ddit.board_write.model.Board_WriteVo;
import kr.or.ddit.board_write.service.IBoard_WriteService;
import kr.or.ddit.paging.model.PageVo;

@Controller
public class Board_WriteController {
	
	private static final Logger logger = LoggerFactory.getLogger(Board_WriteController.class);
	
	@Resource(name="board_WriteService")
	private IBoard_WriteService writeService;
	
	@RequestMapping(path = "/community",method=RequestMethod.GET)
	public String boardPostList(Model model,String page, String pageSize,int board_id) {
		
		int pageStr = page == null ? 1 : Integer.parseInt(page);
		int pageSizeStr =  pageSize == null ? 10 : Integer.parseInt(pageSize);
		
		PageVo pageVo = new PageVo(pageStr,pageSizeStr);
		pageVo.setBoard_id(board_id);
		
		Map<String, Object> resultMap =  writeService.boardPostList(pageVo);
		
		List<Board_WriteVo> boardList = (List<Board_WriteVo>) resultMap.get("boardPostList");
		logger.debug("!@# boardList : {}",boardList);
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("board_id",board_id);
		model.addAttribute("boardList", boardList);
		
		return "/board/community/communityList.user.tiles";
	}
	
	@RequestMapping(path = "/postAdd",method=RequestMethod.GET)
	public String boardWrite(Model model,int boardnum) {
		
		model.addAttribute("boardnum", boardnum);
		return "/board/community/communityWrite.user.tiles";
	}
	
	@RequestMapping(path = "/postAdd",method=RequestMethod.POST)
	public String boardWrite(Model model,String subject,String smarteditor,String user_email,int boardnum) {
		
		String viewName ="";
		
		Board_WriteVo writeVo = new Board_WriteVo(boardnum, user_email, subject, smarteditor);
		logger.debug("!@# writeVo : {}",writeVo);
		int writeCnt = writeService.insertPost(writeVo);
		
		if(writeCnt == 1) {
			viewName="redirect:/postAdd";
		}else {
			viewName="redirect:/postAdd";
		}
		
		return "redirect:/postAdd";
	}
}

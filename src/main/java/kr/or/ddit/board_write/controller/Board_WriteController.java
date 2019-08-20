package kr.or.ddit.board_write.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.bd_inquiry.model.Bd_InquiryVo;
import kr.or.ddit.board_answer.model.Board_AnswerVo;
import kr.or.ddit.board_answer.service.IBoard_AnswerService;
import kr.or.ddit.board_write.model.Board_WriteVo;
import kr.or.ddit.board_write.model.PostReplyVo;
import kr.or.ddit.board_write.service.IBoard_WriteService;
import kr.or.ddit.likes.service.ILikesService;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.users.model.UserVo;

@Controller
public class Board_WriteController {
	
	private static final Logger logger = LoggerFactory.getLogger(Board_WriteController.class);
	
	@Resource(name="board_WriteService")
	private IBoard_WriteService writeService;
	
	@Resource(name="board_AnswerService")
	private IBoard_AnswerService answerService;
	
	@Resource(name="likesService")
	private ILikesService likeService;
	
	/**
	 * Method 		: boardPostList
	 * 작성자 			: 양한솔 
	 * 변경이력 		: 2019-07-30 최초 생성
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param board_id
	 * @return
	 * Method 설명 	: 게시판 게시글 페이징리스트
	 */
	@RequestMapping("/community")
	public String boardPostList(Model model,String page, String pageSize,int board_id,HttpSession session) {
		
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		int pageStr = page == null ? 1 : Integer.parseInt(page);
		int pageSizeStr =  pageSize == null ? 10 : Integer.parseInt(pageSize);
		
		PageVo pageVo = new PageVo(pageStr,pageSizeStr);
		pageVo.setBoard_id(board_id);
		pageVo.setUser_email(userVo.getUser_email());
		Map<String, Object> resultMap =  writeService.boardPostList(pageVo);
		
		List<Board_WriteVo> boardList = (List<Board_WriteVo>) resultMap.get("boardPostList");
		logger.debug("!@# boardList : {}",boardList);
		
		Map<Integer, Object> mapAnswerCnt = new HashMap<Integer, Object>();
		
		for(int i = 0;i<boardList.size();i++) {
			mapAnswerCnt.put(boardList.get(i).getWrite_id(),answerService.replyCnt(boardList.get(i).getWrite_id()));
		}
		
		logger.debug("!@# mapAnswerCnt@@@@@@@@@@@@@@@@ : {}",mapAnswerCnt);
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		//나의 게시판 리스트
		Map<String, Object> myResultMap =  writeService.myBoardPostList(pageVo);
		List<Board_WriteVo> myBoardList = (List<Board_WriteVo>) myResultMap.get("myBoardPostList");
		int myaginationSize = (Integer) myResultMap.get("myPaginationSize");
		
		
		
		model.addAttribute("board_id",board_id);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("mapAnswerCnt", mapAnswerCnt);
		
		return "/board/community/communityList.user.tiles";
	}
	@RequestMapping("/myCommunity")
	public String boardMyPostList(Model model,String page, String pageSize,int board_id,HttpSession session) {
		
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		int pageStr = page == null ? 1 : Integer.parseInt(page);
		int pageSizeStr =  pageSize == null ? 10 : Integer.parseInt(pageSize);
		
		PageVo pageVo = new PageVo(pageStr,pageSizeStr);
		pageVo.setBoard_id(board_id);
		pageVo.setUser_email(userVo.getUser_email());
		Map<String, Object> resultMap =  writeService.boardPostList(pageVo);
		
		List<Board_WriteVo> boardList = (List<Board_WriteVo>) resultMap.get("boardPostList");
		logger.debug("!@# boardList : {}",boardList);
		
		Map<Integer, Object> mapAnswerCnt = new HashMap<Integer, Object>();
		
		for(int i = 0;i<boardList.size();i++) {
			mapAnswerCnt.put(boardList.get(i).getWrite_id(),answerService.replyCnt(boardList.get(i).getWrite_id()));
		}
		
		logger.debug("!@# mapAnswerCnt@@@@@@@@@@@@@@@@ : {}",mapAnswerCnt);
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		//나의 게시판 리스트
		Map<String, Object> myResultMap =  writeService.myBoardPostList(pageVo);
		List<Board_WriteVo> myBoardList = (List<Board_WriteVo>) myResultMap.get("myBoardPostList");
		int myaginationSize = (Integer) myResultMap.get("myPaginationSize");
		
		
		
		model.addAttribute("board_id",board_id);
		model.addAttribute("myaginationSize",myaginationSize);
		model.addAttribute("myBoardList", myBoardList);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("mapAnswerCnt", mapAnswerCnt);
		
		return "/board/community/myCommunityList.user.tiles";
	}
	
	@RequestMapping("/communityAjax")
	public @ResponseBody Map<String, Object> boardPostListAjax(Model model,String page, String pageSize,String board_id,HttpSession session) {
		logger.debug("!@# communityAjax@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		logger.debug("!@# board_id : {}",board_id);
		logger.debug("!@# page : {}",page);
		logger.debug("!@# pageSize : {}",pageSize);
		
		int board_idNm = Integer.parseInt(board_id);
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		int pageStr = page == null ? 1 : Integer.parseInt(page);
		int pageSizeStr =  pageSize == null ? 10 : Integer.parseInt(pageSize);
		
		PageVo pageVo = new PageVo(pageStr,pageSizeStr);
		pageVo.setBoard_id(board_idNm);
		pageVo.setUser_email(userVo.getUser_email());
		Map<String, Object> resultMap =  writeService.boardPostList(pageVo);
		
		List<Board_WriteVo> boardList = (List<Board_WriteVo>) resultMap.get("boardPostList");
		logger.debug("!@# boardListAjax : {}",boardList);
		
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		Map<Integer, Object> mapAnswerCnt = new HashMap<Integer, Object>();
		
		for(int i = 0;i<boardList.size();i++) {
			mapAnswerCnt.put(boardList.get(i).getWrite_id(),answerService.replyCnt(boardList.get(i).getWrite_id()));
		}
		
		//나의 게시판 리스트
		Map<String, Object> myResultMap =  writeService.myBoardPostList(pageVo);
		List<Board_WriteVo> myBoardList = (List<Board_WriteVo>) myResultMap.get("myBoardPostList");
		int myaginationSize = (Integer) myResultMap.get("myPaginationSize");
		
		Map<String, Object> resultMap2 = new HashMap<String, Object>();
		
		resultMap2.put("board_id",board_id);
		resultMap2.put("paginationSize",paginationSize);
		resultMap2.put("boardList", boardList);
		resultMap2.put("myaginationSize",myaginationSize);
		resultMap2.put("myBoardList", myBoardList);
		resultMap2.put("pageVo", pageVo);
		resultMap2.put("mapAnswerCnt", mapAnswerCnt);
//		model.addAttribute("board_id",board_id);
//		model.addAttribute("paginationSize",paginationSize);
//		model.addAttribute("boardList", boardList);
//		model.addAttribute("myaginationSize",myaginationSize);
//		model.addAttribute("myBoardList", myBoardList);
//		model.addAttribute("pageVo", pageVo);
		
		return resultMap2;
	}
	
	/**
	 * Method 		: boardWrite
	 * 작성자 			: 양한솔 
	 * 변경이력 		: 2019-07-30 최초 생성
	 * @param model
	 * @param boardnum
	 * @return
	 * Method 설명 	: 게시글 작성하기위한 게시판 고유번호 값 가져오기.
	 */
	@RequestMapping(path = "/postAdd",method=RequestMethod.GET)
	public String boardWrite(Model model,int boardnum) {
		logger.debug("!@# boardnum : {}",boardnum);
		
		model.addAttribute("boardnum", boardnum);
		return "/board/community/communityWrite.user.tiles";
	}
	
	/**
	 * Method 		: boardWrite
	 * 작성자 			: 양한솔 
	 * 변경이력 		: 2019-07-30 최초 생성
	 * @param model
	 * @param subject
	 * @param smarteditor
	 * @param user_email
	 * @param boardnum
	 * @return
	 * Method 설명 	: 게시글 작성
	 */
	@RequestMapping(path = "/postAdd",method=RequestMethod.POST)
	public String boardWrite(Model model,String subject,String smarteditor,String user_email,int boardnum) {
		
		String viewName ="";
		
		Board_WriteVo writeVo = new Board_WriteVo(boardnum, user_email, subject, smarteditor);
		logger.debug("!@# writeVo : {}",writeVo);
		int writeCnt = writeService.insertPost(writeVo);
		
		if(writeCnt == 1) {
			viewName="redirect:/community?board_id="+boardnum;
		}else {
			viewName="redirect:/community?board_id="+boardnum;
		}
		
		return viewName;
	}
	
	/**
	 * Method 		: boardPost
	 * 작성자 			: 양한솔 
	 * 변경이력 		: 2019-07-30 최초 생성
	 * @param model
	 * @param write_id
	 * @param session
	 * @return
	 * Method 설명 	: 게시글 상세 정보
	 */
	@RequestMapping(path = "/postView", method=RequestMethod.GET)
	public String boardPost(Model model,int write_id,HttpSession session) {
		
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		
		// 게시글 정보
		Board_WriteVo writeVo = writeService.postInfo(write_id);
		
		// 게시글 조회수증가
		if(!writeVo.getUser_email().equals(userVo.getUser_email())) {
			int postViewCnt = writeService.postViewCnt(write_id);
		}

		// 게시글 댓글 리스트
		List<Board_AnswerVo> replyList = answerService.replyList(write_id);
		logger.debug("!@# replyList : {}",replyList);
		
		// 게시글 댓글 개수
		int replyCnt = answerService.replyCnt(write_id);
		
		//게시글 좋아요 눌렀나 확인
		Board_WriteVo likeChVo = new Board_WriteVo();
		likeChVo.setUser_email(userVo.getUser_email());
		likeChVo.setWrite_id(write_id);
		int likeCheck = likeService.likePushCheck(likeChVo);
		
		model.addAttribute("replyCnt",replyCnt);
		model.addAttribute("replyList", replyList);
		model.addAttribute("writeInfo", writeVo);
		model.addAttribute("likeCheck",likeCheck);
		
		return "/board/community/communityView.user.tiles";
	}
	
	@RequestMapping(path="/postView",method=RequestMethod.POST)
	public String boardPost(String r_content,int write_id, int board_id, String user_email) {
		
		String viewName ="";
		
		Board_AnswerVo replyVo = new Board_AnswerVo(write_id, user_email, r_content);
		
		int replyCnt = answerService.insertReply(replyVo);
		logger.debug("!@# replyVo : {}",replyVo);
		
		if(replyCnt == 1) {
			viewName ="redirect:/postView?write_id="+ write_id;
		}else {
			viewName ="redirect:/postView?write_id="+ write_id;
		}
		 
		return viewName;
	}
	
	
	@RequestMapping(path="/postModify",method=RequestMethod.GET)
	public String postModify(Model model, int board_id, int write_id) {
		
		// 게시글 정보
		Board_WriteVo writeVo = writeService.postInfo(write_id);
		
		model.addAttribute("writeInfo", writeVo);
		model.addAttribute("board_id", board_id);
		 
		return "/board/community/communityUpdate.user.tiles";
	}
	
	@RequestMapping(path="/postModify",method=RequestMethod.POST)
	public String postModify(Model model, int board_id, int write_id, String smarteditor, String subject) {
	
		String viewName="";
		Board_WriteVo writeVo = new Board_WriteVo(board_id, write_id, subject, smarteditor);
		
		int updateCnt = writeService.updatePost(writeVo);
		
		if(updateCnt == 1 ) {
			viewName = "redirect:/postView?write_id="+write_id;
		}else {
			viewName="redirect:/community";
		}
		
		
		return viewName;
	}
	
	@RequestMapping("/postDelete")
	public String postDelete(Model model,int board_id, int write_id) {
		
		String viewName = "";
		
		int deleteCnt = writeService.deletePost(write_id);
		
		if(deleteCnt == 1) {
			viewName = "redirect:/community?board_id="+board_id;
		}
		
		return viewName;
	}
	
	
	@RequestMapping(path="/replyDelete",method=RequestMethod.POST)
	public String replyDelete(int board_id, int write_id, int replynum1) {
		
		String viewName="";
		logger.debug("!@# replynum1 : {}",replynum1);
		int replyCnt = answerService.deleteReply(replynum1);
		
		if(replyCnt == 1) {
			viewName ="redirect:/postView?board_id="+board_id+"&write_id="+write_id;
		}else{
			viewName ="redirect:/postView?board_id="+board_id+"&write_id="+write_id;
		}
		
		
		return viewName;
	}
	
	@RequestMapping(path="/boardSearch",method=RequestMethod.POST)
	public String select(String search, String searchText, String boardnum02,String page, String pageSize, Model model) {
		
		int pageStr = page == null ? 1 : Integer.parseInt(page);
		int pageSizeStr =  pageSize == null ? 10 : Integer.parseInt(pageSize);
		
		PageVo pageVo = new PageVo(pageStr,pageSizeStr);
		pageVo.setBoard_id(Integer.parseInt(boardnum02));
		
		logger.debug("log search : {}, serchText : {}",search,searchText);
		if(search.equals("title")) {
			pageVo.setSubject(searchText);
			Map<String, Object> resultMap =  writeService.selectTitle(pageVo);
			
			List<Board_WriteVo> titleList = (List<Board_WriteVo>) resultMap.get("titleList");
			model.addAttribute("searchList",titleList);
			
			model.addAttribute("board_id",boardnum02);
			model.addAttribute("boardList", titleList);
			
		}else if(search.equals("content")) {
			pageVo.setContent(searchText);
			Map<String, Object> resultMap =  writeService.selectContent(pageVo);
			
			List<Board_WriteVo> contentList = (List<Board_WriteVo>) resultMap.get("contentList");
			
			model.addAttribute("board_id",boardnum02);
			model.addAttribute("boardList", contentList);
		}
		
		
		
		
		
		
		
		//int paginationSize = (Integer) resultMap.get("paginationSize");
		
		
		return "/board/community/communityList.user.tiles";
		
		
		
	}
}

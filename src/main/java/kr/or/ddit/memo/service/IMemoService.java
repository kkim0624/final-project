package kr.or.ddit.memo.service;

import java.util.List;

import kr.or.ddit.memo.model.MemoVo;

public interface IMemoService {
	
	String mergeMemo(MemoVo memoVo);

	String mergeMemoYd(MemoVo memoVo);
	
//	String memoList(MemoVo memoVo);
	
	List<MemoVo> memoList(MemoVo memoVo);
	
	List<MemoVo> getYdTdCon(MemoVo memoVo);
	
	String getMemo(MemoVo memoVo);
}

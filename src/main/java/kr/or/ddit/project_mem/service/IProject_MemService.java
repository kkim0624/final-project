package kr.or.ddit.project_mem.service;

import java.util.List;

import kr.or.ddit.project_mem.model.Project_MemVo;

public interface IProject_MemService {
	
	/**
	 * 
	 * Method 		: projectMemList
	 * 작성자 		: 박서경 
	 * 변경이력 		: 2019-07-21 최초 생성
	 * @return
	 * Method 설명 	: 프로젝트 멤버 리스트 조회
	 */
	List<Project_MemVo> projectMemList(Project_MemVo projectMemVo);
	
	/**
	 * 
	 * Method 		: insertProjectMem
	 * 작성자 		: 박서경 
	 * 변경이력 		: 2019-07-21 최초 생성
	 * @return
	 * Method 설명 	: 프로젝트 멤버 등록(초대)
	 */
	int insertProjectMem(Project_MemVo projectMemVo);
	
	
	/**
	 * 
	 * Method 		: updatePjojectMem
	 * 작성자 		: 박서경 
	 * 변경이력 		: 2019-07-21 최초 생성
	 * @param projectMemVo
	 * @return
	 * Method 설명 	: 프로젝트 멤버 업데이트(멤버레벨, 프로젝트 소유 유무, 프로젝트 멤버 닉네임)
	 */
	int updateProjectMem(Project_MemVo projectMemVo);
	
	
	/**
	 * 
	* Method : getMyProjectMemList
	* 작성자 : 김경호
	* 변경이력 : 2019-07-31
	* @param prj_id
	* @return
	* Method 설명 : 휴면 계정으로 전환하기 위하여 나의 프로젝트 멤버를 조회한다
	 */
	List<Project_MemVo> getMyProjectMemList(int prj_id);
}

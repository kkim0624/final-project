package kr.or.ddit.project_mem.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.friends.model.FriendsVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.project_mem.model.Project_MemVo;

public interface IProject_MemDao {

	/**
	 * 
	 * Method : projectMemList 작성자 : 박서경 변경이력 : 2019-07-21 최초 생성
	 * 
	 * @return Method 설명 : 프로젝트 관리자 추가를 위한 프로젝트 멤버 리스트 조회
	 */
	List<Project_MemVo> projectMemList(Project_MemVo projectMemVo);

	/**
	 * 
	 * Method : insertProjectMem 작성자 : 박서경 변경이력 : 2019-07-21 최초 생성
	 * 
	 * @return Method 설명 : 프로젝트 멤버 등록(초대)
	 */
	int insertProjectMem(Project_MemVo projectMemVo);

	/**
	 * 
	 * Method : updatePjojectMem 작성자 : 박서경 변경이력 : 2019-07-21 최초 생성
	 * 
	 * @param projectMemVo
	 * @return Method 설명 : 프로젝트 멤버 업데이트(멤버레벨, 프로젝트 소유 유무, 프로젝트 멤버 닉네임)
	 */
	int updateProjectMem(Project_MemVo projectMemVo);

	/**
	 * 
	 * Method : getMyProjectMemList 작성자 : 김경호 변경이력 : 2019-08-01
	 * 
	 * @param prj_id
	 * @return Method 설명 : 휴면 계정으로 전환하기 위하여 나의 프로젝트 멤버를 조회한다
	 */
	List<Project_MemVo> getMyProjectMemList(String user_email);
	
	
	List<Project_MemVo> getMyProjectMemList(int prj_id);
	
	/**
	 * 
	 * Method : projectMemPagingList 작성자 : 김경호 변경이력 : 2019-08-06
	 * 
	 * @param prj_id
	 * @return Method 설명 : 사용자가 멤버 탭에서 자신과 같은 프로젝트를 진행하는 멤버의 리스트를 페이징으로 조회한다.
	 */
	List<Project_MemVo> projectMemPagingList(Map<String, Object> map);

	/**
	 * 
	 * Method : projectMemCnt 작성자 : 김경호 변경이력 : 2019-08-06
	 * 
	 * @return Method 설명 : 프로젝트 멤버 전체수 조회
	 */
	int projectMemCnt(Map<String, Object> map);

	/**
	 * 
	 * Method : projectAllMemList 작성자 : 박서경 변경이력 : 2019-08-05 최초 생성
	 * 
	 * @param user_email
	 * @return Method 설명 : 프로젝트 멤버 추가를 위한 프로젝트 멤버 리스트 조회
	 */
	List<Project_MemVo> projectAllMemList(String user_email);

	/**
	 * 
	 * Method : deleteProjectMem 작성자 : 박서경 변경이력 : 2019-08-06 최초 생성
	 * 
	 * @param projectVo
	 * @return Method 설명 : 프로젝트 멤버 삭제
	 */
	int deleteProjectMem(Project_MemVo projectMemVo);

	/**
	 * 
	 * Method : getProjectMemInfo 작성자 : 박서경 변경이력 : 2019-08-07 최초 생성
	 * 
	 * @param projectMemVo
	 * @return Method 설명 : 현재 접속한 사용자의 프로젝트 멤버 정보 조회
	 */
	Project_MemVo getProjectMemInfo(Project_MemVo projectMemVo);

	/**
	 * 
	 * Method : headerChatFriendList 작성자 : 유다연 변경이력 : 2019-08-10 최초 생성
	 * 
	 * @param user_email
	 * @return Method 설명 : 헤더에 화상회의 친구목록 리스트
	 */
	List<Project_MemVo> headerChatFriendList(String user_email);
	
	/**
	 * 
	* Method : prjMemListForInactive
	* 작성자 : 김경호
	* 변경이력 : 2019-08-21
	* @param map
	* @return
	* Method 설명 : 휴면 계정을 하기 위해 session에서 가져온 user_email에서 나의 프로젝트 멤버를 리스트로 조회
	 */
	List<Project_MemVo> prjMemListForInactive(Map<String, Object> map);
	
	/**
	 * 
	* Method : prjMemListForInactiveCnt
	* 작성자 : 김경호
	* 변경이력 : 2019-08-21
	* @param map
	* @return
	* Method 설명 : 휴면 계정을 하기 위해 session에서 가져온 user_email에서 나의 프로젝트 멤버를 리스트로 조회한 갯수
	 */
	int prjMemListForInactiveCnt(Map<String, Object> map);
	
	/**
	 * 
	 * Method : updateInactiveMember
	 * 작성자 : 김경호
	 * 변경이력 : 2019-08-22
	 * @param projectMemVo
	 * @return
	 * Method 설명 : 휴면 계정 설정하기 위해 프로젝트 소유자의 멤버 레벨(String prj_mem_lv)를 'LV0'으로 업데이트 시키고
	 * 			       프로젝트 소유 유무(String prj_own_fl)를 'N'로 업데이트 시켜 준다. 
	 */
	int updateInactiveMember(Project_MemVo projectMemVo);
	
	/**
	 * 
	 * Method : updateTransferOwnership
	 * 작성자 : 김경호
	 * 변경이력 : 2019-08-22
	 * @param projectMemVo
	 * @return
	 * Method 설명 : 휴면 계정 설정하기 위해 프로젝트 소유자를 넘겨줄 자의 멤버 레벨(String prj_mem_lv)를 'LV1'으로 업데이트 시키고
	 * 			       프로젝트 소유 유무(String prj_own_fl)를 'Y'로 업데이트 시켜 준다. 
	 */
	int updateTransferOwnership(Project_MemVo projectMemVo);
	
}

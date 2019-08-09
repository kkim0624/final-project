package kr.or.ddit.friends.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.friends.model.ChatFriendsVo;
import kr.or.ddit.friends.model.FriendsVo;

public interface IFriendsService {

	/**
	 * 
	 * Method 		: friendList
	 * 작성자 			: 유다연
	 * 변경이력 		: 2019-07-28 최초 생성
	 * @param user_email
	 * @return
	 * Method 설명 	: 친구 리스트
	 */
	public List<ChatFriendsVo> friendList(String user_email);
	
	/**
	 * 
	 * Method : insertFriends
	 * 작성자 : 김경호
	 * 변경이력 : 2019-08-08
	 * @param friendsVo
	 * @return
	 * Method 설명 : 친구 등록
	 */
	int insertFriends(FriendsVo friendsVo);
	
	/**
	 * 
	* Method : friendPagingList
	* 작성자 : 김경호
	* 변경이력 : 2019-08-07
	* @param searcj
	* @return
	* Method 설명 : 일반 사용자가 자신의 친구 목록을  페이징으로 조회
	 */
	Map<String, Object> friendPagingList(Map<String, Object> map);
	
	/**
	 * 
	* Method : friendPagingListByEmail
	* 작성자 : 김경호
	* 변경이력 : 2019-08-07
	* @param user_email
	* @return
	* Method 설명 : 친구 페이징 리스트
	 */
	Map<String, Object> friendSearchByEmail(Map<String, Object> frd_email);
	
	/**
	 * 
	* Method : deleteFriends
	* 작성자 : 김경호
	* 변경이력 : 2019-08-07
	* @param frd_email
	* @return
	* Method 설명 : 일반 사용자가 친구 삭제
	 */
	int deleteFriends(String frd_email);
	
}


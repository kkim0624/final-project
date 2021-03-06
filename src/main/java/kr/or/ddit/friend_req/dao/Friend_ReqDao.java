package kr.or.ddit.friend_req.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.friend_req.model.Friend_ReqVo;

@Repository
public class Friend_ReqDao implements IFriend_ReqDao{
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : firendsRequest
	* 작성자 : 김경호
	* 변경이력 : 2019-08-09
	* @param friendsReqVo
	* @return
	* Method 설명 : 친구 요청
	 */
	@Override
	public int firendsRequest(Friend_ReqVo friendsReqVo) {
		return sqlSession.insert("friend.firendsRequest",friendsReqVo);
	}
	
	/**
	 * 
	* Method : friendsRequestList
	* 작성자 : 김경호
	* 변경이력 : 2019-08-19
	* @return
	* Method 설명 : 친구 요청 받은 목록
	 */
	@Override
	public List<Friend_ReqVo> friendsRequestList(String req_email) {
		return sqlSession.selectList("friend.friendsRequestList",req_email);
	}
	
	/**
	 * 
	* Method : deleteFriendRequest
	* 작성자 : 김경호
	* 변경이력 : 2019-08-20
	* @param user_email
	* @return
	* Method 설명 : 요청받은 친구 목록에서 거절 버튼을 누르면 
	* 			  Friend_reqVo에서 요청한 친구의 이메일 user_email을 찾아 삭제
	 */
	@Override
	public int deleteFriendRequest(String user_email) {
		return sqlSession.delete("friend.deleteFriendRequest",user_email);
	}

	/**
	 * 
	* Method : updateReqAccept
	* 작성자 : 김경호
	* 변경이력 : 2019-08-20
	* @param friendReqVo
	* @return
	* Method 설명 : 친구 요청 상태를 수락으로 바꿔줌
	 */
	@Override
	public int updateReqAccept(Friend_ReqVo friendReqVo) {
		return sqlSession.update("friend.updateReqAccept",friendReqVo);
	}
	
	/**
	 * 
	* Method : updateReqDeny
	* 작성자 : 김경호
	* 변경이력 : 2019-08-20
	* @param friendReqVo
	* @return
	* Method 설명 : 친구 요청 상태를 거절로 바꿔줌
	 */
	@Override
	public int updateReqDeny(Friend_ReqVo friendReqVo) {
		return sqlSession.update("friend.updateReqDeny",friendReqVo);
	}

}

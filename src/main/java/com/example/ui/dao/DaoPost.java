package com.example.ui.dao;

import com.example.ui.entity.Post;
import com.example.ui.entity.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DaoPost {
	@Autowired
	private RestTemplate restTemplate;

	public DaoPost() {

	}

	public DaoPost(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Post savePost(Post post) {
		Post post1 = restTemplate.postForObject("http://18.142.162.108:9191/post/", post, Post.class);
		return post1;
	}

	public List<VO> getAllPosts() {
		ResponseEntity<ArrayList<VO>> list = restTemplate.exchange("http://18.142.162.108:9191/post/", HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<VO>>() {

				});
		ArrayList<VO> list1 = list.getBody();
//		Collections.reverse(list1);
		return list1;
	}

	public VO getPostWithUser(Long id) {
		VO vo = restTemplate.getForObject("http://18.142.162.108:9191/post/" + id, VO.class);
		return vo;
	}

	public List<VO> getPostByUserId(Long userId) {
		ResponseEntity<ArrayList<VO>> list = restTemplate.exchange("http://18.142.162.108:9191/post/user/" + userId,
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<VO>>() {

				});
		ArrayList<VO> list1 = list.getBody();
//		Collections.reverse(list1);
		return list1;
	}
//    public List<ResponseTemplateVO> getPostByUserId(Long userId);
}

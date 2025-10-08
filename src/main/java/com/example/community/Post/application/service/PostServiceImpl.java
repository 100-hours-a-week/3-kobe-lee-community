package com.example.community.Post.application.service;

import com.example.community.Post.api.dto.CreatePostRequest;
import com.example.community.Post.domain.Post;
import com.example.community.Post.repository.PostRepository;
import com.example.community.auth.jwt.JwtUtils;
import com.example.community.global.exception.GeneralException;
import com.example.community.global.response.code.status.ErrorStatus;
import com.example.community.image.domain.Image;
import com.example.community.image.repository.ImageRepository;
import com.example.community.member.domain.Member;
import com.example.community.member.exception.MemberNotFoundException;
import com.example.community.member.repository.MemberRepository;
import com.example.community.postImage.domain.PostImage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final JwtUtils jwtUtils;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Post createPost(HttpServletRequest httpServletRequest, CreatePostRequest createPostRequest) {
        String accessToken = jwtUtils.resolveToken(httpServletRequest);
        Long memberId = Long.parseLong(jwtUtils.getUserNameFromToken(accessToken));
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        Post post = Post.builder()
                .title(createPostRequest.title())
                .content(createPostRequest.content())
                .writer(member)
                .build();

        for (Long imageId : createPostRequest.imageIds()) {
            Image image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.IMAGE_NOT_FOUND));

            PostImage postImage = PostImage.builder()
                    .post(post)
                    .image(image)
                    .build();

            post.addPostImage(postImage);
        }

        return postRepository.save(post);
    }
}

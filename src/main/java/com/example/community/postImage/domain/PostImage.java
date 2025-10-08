package com.example.community.postImage.domain;

import com.example.community.Post.domain.Post;
import com.example.community.image.domain.Image;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
    @EmbeddedId
    private PostImageId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToOne
    @MapsId("imageId")
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Column(nullable = false)
    private int displayOrder;

    @Column(nullable = false)
    private boolean isThumbnail;

    public static PostImage of(Post post, Image image, int order, boolean isThumbnail) {
        PostImage postImage = new PostImage();
        postImage.post = post;
        postImage.image = image;
        postImage.displayOrder = order;
        postImage.isThumbnail = isThumbnail;

        postImage.id = new PostImageId(post.getId(), image.getId());

        return postImage;
    }
}

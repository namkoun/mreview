package oeg.zerock.mreview.service;

import oeg.zerock.mreview.dto.ReviewDTO;
import oeg.zerock.mreview.entity.Member;
import oeg.zerock.mreview.entity.Movie;
import oeg.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno);

    Long register(ReviewDTO movieReviewDTO);

    void modify(ReviewDTO movieReviewDTO);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO){

        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;
    }

    default ReviewDTO entityToDto(Review movieReview){

    }
}

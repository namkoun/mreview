package oeg.zerock.mreview.repository;

import oeg.zerock.mreview.entity.Member;
import oeg.zerock.mreview.entity.Movie;
import oeg.zerock.mreview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {



    @Autowired
    private ReviewRepository reviewRepository;



    @Test
    public void insertMovieReviews(){

        IntStream.rangeClosed(1,200).forEach(i -> {

            Long mno = (long)(Math.random() * 100)+1;


            Long mid = ((long)(Math.random()* 100) +1 );
            Member member = Member.builder().mid(mid).build();

            Review moiveReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이영화에 대한 느낌"+i)
                    .build();

            reviewRepository.save(moiveReview);

        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(92L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview ->{
            System.out.print(movieReview.getReviewnum());
            System.out.print("\t"+movieReview.getGrade());
            System.out.print("\t"+movieReview.getText());
            System.out.print("\t"+movieReview.getMember().getEmail());
            System.out.println("_-_--_------__-_-__-____----_");
        });
    }


}

package com.embarkx.FirstSpring.review.impl;
import com.embarkx.FirstSpring.company.Company;
import com.embarkx.FirstSpring.company.CompanyService;
import com.embarkx.FirstSpring.review.Review;
import com.embarkx.FirstSpring.review.ReviewRepository;
import com.embarkx.FirstSpring.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        return reviewRepository.findByCompanyId(companyId) ;
    }



    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getById(companyId);
        if(company!=null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }

        }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        if(companyService.getById(companyId)!=null)
        {
            updateReview.setCompany(companyService.getById(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getById(companyId)!=null && reviewRepository.existsById(reviewId))
        {
            Review review=reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }
}


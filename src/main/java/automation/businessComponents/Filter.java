package automation.businessComponents;

import automation.core.Page;

public class Filter {

    public void applyCustomerReviewFilter(String starRating) {
        Page page = new Page();
        switch (starRating) {
            case "1":
                page.customerReview().select1StarRating();
                break;
            case "2":
                page.customerReview().select2StarRating();
                break;
            case "3":
                page.customerReview().select3StarRating();
                break;
            default:
                page.customerReview().select4StarRating();
                break;
        }
    }

    public void applyDiscountFilter(String discountPercentage) {
        Page page = new Page();
        switch (discountPercentage) {
            case "10":
                page.discount().applyTenPercentDiscount();
                break;
            case "25":
                page.discount().applyTwentyFivePercentDiscount();
                break;
            case "35":
                page.discount().applyThirtyFivePercentDiscount();
                break;
            case "50":
                page.discount().applyFiftyPercentDiscount();
                break;
            case "60":
                page.discount().applySixtyPercentDiscount();
                break;
            case "70":
                page.discount().applySeventyPercentDiscount();
                break;
            default:
                throw new IllegalArgumentException("Please pass a valid discount percentage");
        }
    }

    public void chooseBestSeller(){

    }

    public void chooseAmazonsChoice(){

    }
}

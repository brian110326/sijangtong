const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};

const reviewLoad = () => {
  fetch(`/review/${productId}/reviews`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      let result = "";
      data.forEach((review) => {
        result += `<div class="review-row" data-rno="${review.reviewId}">
            <h4 class="mb-4">${product.pName}</h4>
            <div class="media mb-4">
              <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px" />
              <div class="media-body">
                <h6>
                ${review.nickname}<small> - <i>${formatDate(review.createdDate)}</i></small>
                </h6>
                <div class="starrr"></div>
                <p>
                 ${review.text}
                </p>
              </div>
            </div>
            </div>`;
      });
    });
};
reviewLoad();

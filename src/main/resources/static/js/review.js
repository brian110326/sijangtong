// 날짜 포맷 변경 함수
const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};

const reviewsLoad = () => {
  fetch(`/review/${productId}/reviews`)
    .then((response) => response.json)
    .then((data) => {
      console.log(data);

      let result = "";
      data.forEach((review) => {
        result += `<h4 class="mb-4" data-rno="${review.reviewId}">1 review for "Product Name"</h4>
        <div class="media mb-4">
          <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px" />
          <div class="media-body">
            <h6>
            ${review.memberNickname}<small> - <i>${formatDate(review.createdDate)}</i></small>
            </h6>
            <div class="text-primary mb-2">
            <div class="starrr"></div>
            </div>
            <p>
              ${review.text}
            </p>
          </div>
        </div>`;
      });
    });
};
reviewsLoad();

// 리뷰 등록&수정 부분
// const reviewForm = document.querySelector(".review-form");
// reviewForm.addEventListener("submit", (e) => {
//   e.preventDefault();

//   const text = reviewForm.querySelector("#text");
//   const nickname = reviewForm.querySelector("#memberNickname");
//   const email = reviewForm.querySelector("#memberEmail");
//   const reviewId = reviewForm.querySelector("#reviewId");

//   const body = {
//     productId: productId,
//     text: text.value,
//     grade: grade,
//     email: memberEmail,
//     reviewId: reviewId.value,
//   };

//   if (!reviewId.value) {
//     // review 등록
//     fetch(`/review/${productId}`, {
//       headers: {
//         "content-type": "application/json",
//       },
//       body: JSON.stringify(body),
//       method: "post",
//     })
//       .then((response) => response.text())
//       .then((data) => {
//         console.log(data);

//         text.value = "";

//         reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();

//         if(data) alert(data+ " 번 리뷰가 등록되었습니다")
//             reviewsLoad();
//       });
//   } else{
//     // review 수정

//   }
// });

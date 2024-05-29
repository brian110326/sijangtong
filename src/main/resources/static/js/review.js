window.onload = function () {
  const reviewList = document.querySelector(".reviewList");
  const formatDate = (data) => {
    const date = new Date(data);

    return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
  };

  // 리뷰리스트 별점 보여주기
  const reviewStars = (grade) => {
    let stars = "";
    for (let i = 1; i <= 5; i++) {
      if (i <= grade) {
        stars += '<a class="fa-solid fa-star"></a>';
      } else {
        stars += '<a class="fa-regular fa-star"></a>';
      }
    }
    return stars;
  };

  const reviewLoad = () => {
    fetch(`/review/${productId}/reviews`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        let result = "";
        data.forEach((review) => {
          result += `<div class="review-row" data-rno="${review.reviewId}">
          <h4 class="mb-4">${review.memberEmail}</h4>
          <div class="media mb-4">
            <img src="" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px" />
            <div class="media-body">
              <h6>
                ${review.memberNickname}<small> - <i>${formatDate(review.createdDate)}</i></small>
              </h6>
              <div class="starrr">${reviewStars(review.grade)}</div>
              <p>
                ${review.text}
              </p>
            </div>`;
          if (`${review.memberEmail}` == user || userRole == "ADMIN") {
            result += `<div class="d-flex flex-column align-self-center">
            <div> <button  class="btn btn-primary btn-sm mb-2">수정</button></div>
              <div><button  class="btn btn-danger btn-sm">삭제</button>
            </div></div>`;
            result += `</div>`;
          }
          result += `</div>`;
        });
        reviewList.innerHTML = result;
      });
  };

  reviewLoad();
  const reviewForm = document.querySelector(".review_form");
  reviewForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const text = reviewForm.querySelector("#text");

    const reviewId = reviewForm.querySelector("#reviewId");

    const body = {
      productId: productId,
      text: text.value,
      memberEmail: user,
      grade: grade || 0,
      memberNickname: memberNickname,
      reviewId: reviewId.value,
    };

    // 등록
    if (!reviewId.value) {
      fetch(`/review/${productId}`, {
        headers: {
          "content-type": "application/json",
          "X-CSRF-TOKEN": csrfValue,
        },
        body: JSON.stringify(body),
        method: "POST",
      })
        .then((response) => response.text())
        .then((data) => {
          console.log(data);

          text.value = "";

          reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();

          if (data) alert(data + " 번 리뷰등록 완료.");
          reviewLoad();
        });
    } else {
      // 수정
      fetch(`/review/${productId}/${reviewId.value}`, {
        headers: {
          "content-type": "application/json",
          "X-CSRF-TOKEN": csrfValue,
        },
        body: JSON.stringify(body),
        method: "PUT",
      })
        .then((response) => response.text())
        .then((data) => {
          console.log(data);

          text.value = "";
          reviewId.value = "";
          reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();

          if (data) alert(data + " 번 리뷰수정 완료.");
          reviewForm.querySelector("button").innerHTML = "Leave Your Review";
          reviewLoad();
        });
    }
  });

  reviewList.addEventListener("click", (e) => {
    console.log("이벤트 대상 ", e.target);

    const target = e.target;

    const reviewId = target.closest(".review-row").dataset.rno;

    const memberEmail = reviewForm.querySelector("#memberEmail");

    if (target.classList.contains("btn-danger")) {
      if (!confirm("삭제하시겠습니까?")) return;

      const form = new FormData();
      form.append("memberEmail", memberEmail);

      fetch(`/review/${productId}/${reviewId}`, {
        headers: {
          "X-CSRF-TOKEN": csrfValue,
        },
        body: form,
        method: "DELETE",
      })
        .then((response) => response.text())
        .then((data) => {
          alert(data + " 번 리뷰삭제 완료");
          reviewLoad();
        });
    } else if (target.classList.contains("btn-primary")) {
      fetch(`/review/${productId}/${reviewId}`)
        .then((response) => response.json())
        .then((data) => {
          reviewForm.querySelector("#reviewId").value = data["reviewId"];
          reviewForm.querySelector("#text").value = data["text"];

          reviewForm.querySelector(".starrr a:nth-child(" + data["grade"] + ")").click();
          reviewForm.querySelector("button").innerHTML = "Update Your Review";
        });
    }
  });
};

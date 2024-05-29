const imgDiv = document.querySelector("#modal-body1");
const productDiv = document.querySelector("#modal-body2");
const productImgDiv = document.querySelector("#modal-body3");
const reviewDiv = document.querySelector("#modal-body4");
const storeId = document.querySelector("#scriptUseId").value;
const productId = document.querySelector("#scriptUsePId").value;

fetch(`/store/${storeId}/storeImages`)
  .then((response) => response.json())
  .then((data) => {
    console.log("Store Images : ", data);

    let tags = "";

    // 나오지만 나중에 수평 정렬하기
    data.forEach((img) => {
      tags += `<li data-bs-toggle="modal" data-bs-target="#imgModal" data-file="${img.imageURL}">
        <img class="block" th:if="${img.stPath != null}" src="/upload/display?fileName=${img.thumbImageURL}" /></li>`;

      imgDiv.innerHTML = tags;
    });
  });

fetch(`/store/${storeId}/products`)
  .then((response) => response.json())
  .then((data) => {
    console.log("Products : ", data);

    let pTags = "";
    let piTags = "";

    const dtoList = data.dtoList;

    dtoList.forEach((product) => {
      pTags += `<ul class="list-group"><li data-bs-toggle="modal" class="list-group-item">`;
      pTags += `<a href="/shop/storeDetail?storeId=${storeId}&page=${data.page}&type=&keyword=" class="list-group-item list-group-item-action">${product.productId} : ${product.pname}</a>`;
      pTags += `</li></ul>`;

      productDiv.innerHTML = pTags;
    });

    dtoList.forEach((product) => {
      product.productImgDtos.forEach((pImg) => {
        piTags += `<div class="col-md-3">`;
        piTags += `<li data-bs-toggle="modal" data-bs-target="#pImgModal" data-file="${pImg.imageURL}">
        <img class="block" th:if="${pImg.path != null}" src="/upload/display?fileName=${pImg.thumbImageURL}" /></li>`;
        piTags += `</div>`;
      });
    });

    productImgDiv.innerHTML = piTags;
  });

fetch(`/store/${productId}/reviews`)
  .then((response) => response.json())
  .then((data) => {
    let rTags = "";

    console.log(data);

    const reviewList = data.content;

    reviewList.forEach((review) => {
      rTags += `<ul class="list-group"><li data-bs-toggle="modal" class="list-group-item">`;
      rTags += `<a href="" class="list-group-item list-group-item-action">${review.reviewId} : ${review.text}</a>`;
      rTags += `</li></ul>`;
      reviewDiv.innerHTML = rTags;
    });
  });

const productDiv = document.querySelector("#modal-body2");
const storeDiv = document.querySelector("#modal-body1");

const storeId = document.querySelector("#scriptUseId").value;

fetch(`/store/${storeId}`)
  .then((response) => response.json())
  .then((data) => {
    let tags = "";

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeId : ${data.storeId}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeCategory : ${data.storeCategory}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeTel : ${data.storeTel}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `operationTime : ${data.openTime} ~ ${data.closeTime}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeAddress : ${data.storeAddress}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeName : ${data.storeName}`;
    tags += `</li>`;

    tags += `<li data-bs-toggle="modal" class="list-group-item">`;
    tags += `storeDetail : ${data.storeDetail}`;
    tags += `</li>`;

    storeDiv.innerHTML = tags;
  });

fetch(`/store/${storeId}/products`)
  .then((response) => response.json())
  .then((data) => {
    console.log("Products : ", data);

    let pTags = "";
    let piTags = "";

    const dtoList = data.dtoList;

    dtoList.forEach((product) => {
      pTags += `<li data-bs-toggle="modal" class="list-group-item">`;
      pTags += `<a href="/shop/storeproducts?storeId=${storeId}&page=${data.page}&type=&keyword=" class="list-group-item list-group-item-action">${product.productId} : ${product.pname}</a>`;
      pTags += `</li>`;

      productDiv.innerHTML = pTags;
    });
  });

document.querySelector("#productModal").addEventListener("click", (e) => {
  e.preventDefault();

  console.log("e.target : ", e.target);
  console.log("e.currentTarget : ", e.currentTarget);

  target = e.target;

  if (target.classList.contains("page-link")) {
    const storeId = target.getAttribute("data-store-id");
    const page = target.getAttribute("data-page");

    console.log("PAGE==>", page);

    if (storeId && page) {
      fetch(`/store/${storeId}/products/${page}`)
        .then((response) => response.json())
        .then((data) => {
          console.log("Products : ", data);

          let pTags = "";
          let piTags = "";

          const dtoList = data.dtoList;

          dtoList.forEach((product) => {
            pTags += `<li data-bs-toggle="modal" class="list-group-item">`;
            pTags += `<a href="/shop/storeproducts?storeId=${storeId}&page=${data.page}&type=&keyword=" class="list-group-item list-group-item-action">${product.productId} : ${product.pname}</a>`;
            pTags += `</li>`;

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
    }
  }
});

const deleteBtn = document.querySelector("#deleteBtn");
const actionForm = document.querySelector("#actionForm");

deleteBtn.addEventListener("click", () => {
  actionForm.submit();
});

// TypeError: Cannot read properties of null (reading 'addEventListener') 해결

// const actionForm = document.querySelector("#actionForm");
// document.querySelector(".btn-danger").addEventListener("click", (e) => {
//   console.log(e.target);

//   if (!confirm("Are you sure to remove this store?")) {
//     return;
//   }

//   actionForm.submit();
// });

const modalShowArea = document.querySelector("#modalShowArea");

// 페이지 버튼 클릭하는 이벤트함수
function loadProductDataEvent() {
  // document.querySelectorAll(".page-link").forEach((link) => {
  //   link.addEventListener("click", (e) => {
  //     e.preventDefault();
  //     const storeId = e.target.getAttribute("data-store-id");
  //     const pageNumber = e.target.getAttribute("data-page");
  //     loadProductData(storeId, pageNumber);
  //   });
  // });

  document.querySelector("#modalShowArea").addEventListener("click", (e) => {
    const target = e.target;
    console.log("e.target => ", target); // 페이지번호의 a태그
    console.log("e.currentTarget => ", e.currentTarget); // 감싸고있는 div의 modalShowArea 태그

    if (target.classList.contains("page-link")) {
      const storeId = target.getAttribute("data-store-id");
      const pageNumber = target.getAttribute("data-page");

      if (storeId && pageNumber) {
        loadProductData(storeId, pageNumber);
      }
    }

    // console.log("storeId => ", storeId);
    // console.log("pageNumber => ", pageNumber);
  });
}

function loadProductData(storeId, page) {
  fetch(`/store/${storeId}/products/${page}`)
    .then((response) => response.json())
    .then((data) => {
      console.log("Received Data : ", data);
      let pContent = "";
      let pTags = "";

      const dtoList = data.dtoList;

      // type, keyword 부분 th없애고 그냥 원주소 넣기
      dtoList.forEach((product) => {
        pContent += `<li data-bs-toggle="modal" class="list-group-item">`;
        pContent += `<a href="/shop/storeDetail?storeId=${storeId}&page=${data.page}&type=&keyword=" class="list-group-item list-group-item-action">${product.productId} : ${product.pname}</a>`;
        pContent += `</li>`;
      });

      const imgModal = document.querySelector("#imgModal");
      const getImgModal = bootstrap.Modal.getInstance(imgModal);
      getImgModal.hide();

      pTags += `<div class="modal fade" id="productModal" tabindex="-1"><div class="modal-dialog modal-fullscreen modal-xl">`;

      pTags += `<div class="modal-content"><div class="modal-header">`;

      pTags += `<h5 class="modal-title">Products will be DELETED</h5>`;

      pTags += `<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>`;

      pTags += `<div class="modal-body"><ul class="list-group">${pContent}</ul></div>`;

      // 페이지 나누기 영역
      if (data.prev || data.next || data.pageList.length > 1) {
        pTags += `<nav aria-label="Page navigation example"><ul class="pagination justify-content-center">`;

        if (data.prev) {
          pTags += `
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous" data-page="${data.start - 1}" data-store-id="${storeId}">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>`;
        }

        data.pageList.forEach((pageNum) => {
          pTags += `
              <li class="page-item ${pageNum == data.page ? "active" : ""}">
                <a class="page-link" href="#" data-page="${pageNum}" data-store-id="${storeId}">${pageNum}</a>
              </li>`;
        });

        if (data.next) {
          pTags += `
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next" data-page="${data.end + 1}" data-store-id="${storeId}">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>`;
        }

        pTags += `</ul></nav>`;
      }

      pTags += `<div class="modal-footer"><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;

      const nextBtn2 = `<button type="button" id="nextBtn2" class="btn btn-primary">See Next Data</button>`;
      pTags += nextBtn2;

      pTags += `</div></div></div></div>`;

      actionForm.insertAdjacentHTML("beforebegin", pTags);

      const productModal = new bootstrap.Modal(document.querySelector("#productModal"));
      productModal.show();
    });
}

const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  console.log(e.target);

  if (!confirm("Are you sure to remove this store?")) {
    return;
  }

  const storeId = e.target.value;

  fetch(`/store/${storeId}/storeImages`)
    .then((response) => response.json())
    .then((data) => {
      console.log("Store Images : ", data);

      let tags = "";
      let modalContent = "";

      data.forEach((img) => {
        modalContent += `<li data-bs-toggle="modal" data-bs-target="#imgModal" data-file="${img.imageURL}">
        <img class="block" th:if="${img.stPath != null}" src="/upload/display?fileName=${img.imageURL}" /></li>`;
      });

      tags += `<div class="modal fade uploadResult" id="imgModal" tabindex="-1">
        <div class="modal-dialog modal-fullscreen modal-xl">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Store Images will be DELETED</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            <ul>
              ${modalContent}
            </ul>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;

      const nextBtn = `<button type="button" id="nextBtn" data-toggle="modal" class="btn btn-primary">See Next Data</button>`;
      tags += nextBtn;

      tags += `</div></div></div></div>`;

      modalShowArea.innerHTML = tags;

      //actionForm.insertAdjacentHTML("beforebegin", tags);

      const modal = new bootstrap.Modal(document.querySelector(".modal"));

      // 이미지 클릭시 크게 보여주기
      // document.querySelectorAll(".uploadResult ul li").forEach((item) => {
      //   item.addEventListener("click", (e) => {
      //     //const file = e.target.closest("li");
      //     const file = item.getAttribute("data-file");
      //     console.log(file);
      //     const modalBody = document.querySelector(".modal-body ul");
      //     modalBody.innerHTML = `<img src="/upload/display?fileName=${file}" style="width:100%" />`;
      //   });
      // });

      modal.show();

      document.querySelector("#nextBtn").addEventListener("click", (e) => {
        // product, productImg, review 보여주기
        // order, orderItem 보여주기

        // 1. product list 보여주기
        fetch(`/store/${storeId}/products`)
          .then((response) => response.json())
          .then((data) => {
            console.log("Products : ", data);

            let pContent = "";
            let pTags = "";

            const dtoList = data.dtoList;

            // type, keyword 부분 th없애고 그냥 원주소 넣기
            dtoList.forEach((product) => {
              pContent += `<li data-bs-toggle="modal" class="list-group-item">`;
              pContent += `<a href="/shop/storeDetail?storeId=${storeId}&page=${data.page}&type=&keyword=" class="list-group-item list-group-item-action">${product.productId} : ${product.pname}</a>`;
              pContent += `</li>`;
            });

            const imgModal = document.querySelector("#imgModal");
            const getImgModal = bootstrap.Modal.getInstance(imgModal);
            getImgModal.hide();

            pTags += `<div class="modal fade" id="productModal" tabindex="-1"><div class="modal-dialog modal-fullscreen modal-xl">`;

            pTags += `<div class="modal-content"><div class="modal-header">`;

            pTags += `<h5 class="modal-title">Products will be DELETED</h5>`;

            pTags += `<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>`;

            pTags += `<div class="modal-body"><ul class="list-group">${pContent}</ul></div>`;

            // 페이지 나누기 영역
            if (data.prev || data.next || data.pageList.length > 1) {
              pTags += `<nav aria-label="Page navigation example"><ul class="pagination justify-content-center">`;

              if (data.prev) {
                pTags += `
                    <li class="page-item">
                      <a class="page-link" href="" aria-label="Previous" data-page="${data.start - 1}" data-store-id="${storeId}">
                        <span aria-hidden="true">&laquo;</span>
                      </a>
                    </li>`;
              }

              data.pageList.forEach((pageNum) => {
                pTags += `
                    <li class="page-item ${pageNum == data.page ? "active" : ""}">
                      <a class="page-link" href="" data-page="${pageNum}" data-store-id="${storeId}">${pageNum}</a>
                    </li>`;
              });

              if (data.next) {
                pTags += `
                    <li class="page-item">
                      <a class="page-link" href="" aria-label="Next" data-page="${data.end + 1}" data-store-id="${storeId}">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>`;
              }

              pTags += `</ul></nav>`;
            }

            pTags += `<div class="modal-footer"><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;

            const nextBtn2 = `<button type="button" id="nextBtn2" class="btn btn-primary">See Next Data</button>`;
            pTags += nextBtn2;

            pTags += `</div></div></div></div>`;

            //actionForm.insertAdjacentHTML("beforebegin", pTags);

            modalShowArea.innerHTML = pTags;

            const productModal = new bootstrap.Modal(document.querySelector("#productModal"));
            productModal.show();
          });

        loadProductDataEvent();
      });
    });

  // 최종 form submit
  //actionForm.submit();
});

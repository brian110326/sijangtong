// TypeError: Cannot read properties of null (reading 'addEventListener') 해결

// const actionForm = document.querySelector("#actionForm");
// document.querySelector(".btn-danger").addEventListener("click", (e) => {
//   console.log(e.target);

//   if (!confirm("Are you sure to remove this store?")) {
//     return;
//   }

//   actionForm.submit();
// });

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
        <img class="block" th:if="${img.stPath != null}" src="/upload/display?fileName=${img.thumbImageURL}" /></li>`;
      });

      tags += `<div class="modal uploadResult" id="imgModal" tabindex="-1">
        <div class="modal-dialog modal-xl">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Related Datas</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            <ul>
              ${modalContent}
            </ul>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`;

      const nextBtn = `<button type="button" class="btn btn-primary">See Next Data</button>`;
      tags += nextBtn;

      tags += `</div></div></div></div>`;

      actionForm.insertAdjacentHTML("beforebegin", tags);

      // nextBtn.addEventListener("click", (e) => {
      //   // product, productImg, review 보여주기
      //   // order, orderItem 보여주기
      // });

      const modal = new bootstrap.Modal(document.querySelector(".modal"));

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
    });

  //actionForm.submit();
});

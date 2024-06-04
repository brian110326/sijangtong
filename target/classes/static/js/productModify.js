// TypeError: Cannot read properties of null (reading 'addEventListener') 해결
window.onload = function () {
  const productDiv = document.querySelector("#modal-body1");
  const pList = document.querySelector("#pList");

  pList.addEventListener("click", (e) => {
    const target = e.target;

    console.log(e.target);
    console.log(e.currentTarget);

    if (target.classList.contains("btn-danger")) {
      const productId = target.getAttribute("data-product-id");

      fetch(`/store/${productId}/product`)
        .then((response) => response.json())
        .then((data) => {
          let tags = "";
          console.log(data);

          tags += `<li data-bs-toggle="modal" class="list-group-item">`;
          tags += `productId : ${data.productId}`;
          tags += `</li>`;

          tags += `<li data-bs-toggle="modal" class="list-group-item">`;
          tags += `price : ${data.price}`;
          tags += `</li>`;

          tags += `<li data-bs-toggle="modal" class="list-group-item">`;
          tags += `amount : ${data.amount}`;
          tags += `</li>`;

          tags += `<li data-bs-toggle="modal" class="list-group-item">`;
          tags += `avg : ${data.avg}`;
          tags += `</li>`;

          productDiv.innerHTML = tags;
        });
    }
  });

  const removeBtn = document.querySelector("#removeBtn");
  const productIdInsert = document.querySelector("#productIdInsert");
  pList.addEventListener("click", (e) => {
    // e.preventDefault();
    console.log("삭제한 버튼 : ", e.target);
    const productIdValue = e.target.getAttribute("data-product-id");
    productIdInsert.value = productIdValue;
  });

  document.querySelector("#deleteBtn").addEventListener("click", (e) => {
    const target = e.target;
    const pActionForm2 = document.querySelector("#pActionForm2");

    if (target.classList.contains("btn-danger")) {
      if (!confirm("Are you sure to remove this product?")) {
        return;
      } else {
        pActionForm2.submit();
      }
    }
  });
};

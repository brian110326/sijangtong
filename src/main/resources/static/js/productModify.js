// TypeError: Cannot read properties of null (reading 'addEventListener') 해결
window.onload = function () {
  const pList = document.querySelector("#pList");

  pList.addEventListener("click", (e) => {
    console.log(e.target);
    console.log(e.currentTarget);

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

// TypeError: Cannot read properties of null (reading 'addEventListener') 해결
window.onload = function () {
  const actionForm = document.getElementById("actionForm");
  document.querySelector(".btn-danger").addEventListener("click", (e) => {
    console.log(e.target);

    if (!confirm("Are you sure to remove this store?")) {
      return;
    }

    actionForm.submit();
  });
};

// X버튼 클릭시 이미지의 li 제거
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();

  console.log("e.target", e.target);
  console.log("e.currentTarget", e.currentTarget);

  const currentLi = e.target.closest("li");

  console.log(currentLi);

  if (!confirm("Are you sure to remove this img?")) {
    return;
  }

  currentLi.remove();
});

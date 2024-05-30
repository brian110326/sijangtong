window.onload = function () {
  const removeForm = document.querySelector("#removeForm");
  document.querySelector(".btn-danger").addEventListener("click", (e) => {
    e.preventDefault();

    const current_tr = e.target.closest("tr");
    console.log(current_tr.querySelector("#itemId").value);
    removeForm.querySelector("[name='orderItemId']").value = current_tr.querySelector("#itemId").value;
    if (confirm("정말로 삭제하시겠습니까?")) {
      current_tr.remove();
      removeForm.submit();
    }
  });
};

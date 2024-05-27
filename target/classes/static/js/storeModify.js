// TypeError: Cannot read properties of null (reading 'addEventListener') 해결

const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  console.log(e.target);

  if (!confirm("Are you sure to remove this store?")) {
    return;
  }

  actionForm.submit();
});

document.querySelector("#insert-from").addEventListener("click", (e) => {
  e.preventDefault();

  console.log("x 클릭", e.target);
  console.log("x 클릭", e.currentTarget);
});

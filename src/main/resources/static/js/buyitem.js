window.onload = function () {
  const addCart = document.querySelector("#addCart");
  const buyitemForm = document.querySelector("#buyitemForm");
  var amountValue = document.querySelector("#Value").value;

  $(".quantity button").on("click", function () {
    var button = $(this);
    var oldValue = button.parent().parent().find("input").val();
    if (button.hasClass("btn-plus")) {
      var newVal = parseInt(oldValue);
      console.log(newVal);
    } else {
      if (oldValue > 0) {
        var newVal = parseInt(oldValue);
      } else {
        newVal = 0;
      }
    }
    button.parent().parent().find("input").val(newVal);
    amountValue = newVal;
  });
  addCart.addEventListener("click", () => {
    buyitemForm.querySelector("[name='amount']").value = amountValue;

    buyitemForm.submit();
  });
};

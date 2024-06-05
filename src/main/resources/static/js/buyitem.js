window.onload = function () {
  const addCart = document.querySelector("#addCart");
  const buyitemForm = document.querySelector("#buyitemForm");
  var amountValue = document.querySelector("#Value").value;

  // 버튼을 글릭시 자동으로 카운트가 올라가는 jquery 방식
  $(".quantity button").on("click", function () {
    var button = $(this);
    //butten 근처의 input 버튼의 값을 가져와서
    var oldValue = button.parent().parent().find("input").val();
    if (button.hasClass("btn-plus")) {
      // 올라 간 버튼을 누를시 상승
      var newVal = parseInt(oldValue);
      console.log(newVal);
    } else {
      if (oldValue > 0) {
        // 마이너스 버튼을 누를시 하강
        var newVal = parseInt(oldValue);
      } else {
        newVal = 0;
      }
    }
    button.parent().parent().find("input").val(newVal);
    // 변경 값을  input 에 저장
    amountValue = newVal;
  });
  // addCart 클릭시 submit
  addCart.addEventListener("click", () => {
    // form 안에 변동된 수량 저장
    buyitemForm.querySelector("[name='amount']").value = amountValue;

    buyitemForm.submit();
  });
};

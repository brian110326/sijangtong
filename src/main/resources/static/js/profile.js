// 작은 포스터 클릭하면 큰 포스터 보여주기
window.onload = function () {
  const leaveModal = document.getElementById("leaveModal");

  if (leaveModal) {
    leaveModal.addEventListener("show.bs.modal", (e) => {
      // 모달을 뜨게 만든 li 가져오기
      const posterLi = e.relatedTarget;
    });
  }
  // js 파일이 body 쪽 html 보다 먼저 업로드 되기에 전부 업데이트 후 올라가게 설정
  window.onload = function () {
    const memberNickName = document.querySelector("[name='memberNickname']");
    const memberAddr = document.querySelector("[name='memberAddress']");
    const changeForm = document.getElementById("change_form");
    changeForm.addEventListener("submit", (e) => {
      e.preventDefault();

      if (!memberAddr.value) {
        memberAddr.value = addr;
      }
      if (!memberNickName.value) {
        memberNickName.value = nickName;
      }

      console.log(memberAddr.value);
      console.log(memberNickName.value);

      e.target.submit();
    });
  };
};

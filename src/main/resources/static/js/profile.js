// 작은 포스터 클릭하면 큰 포스터 보여주기
const leaveModal = document.getElementById("leaveModal");

if (leaveModal) {
  leaveModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만든 li 가져오기
    const posterLi = e.relatedTarget;
  });
}

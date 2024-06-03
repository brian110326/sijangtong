window.onload = function () {
  const fileInput = document.querySelector("#fileInput");
  const uploadResult = document.querySelector(".uploadResult ul");

  // png, gif, jpg 파일만 upload 가능
  function checkExtension(fileName) {
    const regex = /(.*?).(png|gif|jpg)$/;

    // 이미지 파일 => true, 그 외 파일 => false
    console.log(regex.test(fileName));

    return regex.test(fileName);
  }

  function showUploadImages(arr) {
    let tags = "";

    arr.forEach((obj, idx) => {
      tags += `<li data-name="${obj.fileName}" data-path="${obj.folderPath}" data-uuid="${obj.uuid}">`;
      tags += `<div>`;
      tags += `<a href=""><img src="/upload/display?fileName=${obj.thumbImageURL}" class="block"></a>`;
      tags += `<span class="text-sm d-inline-block mx-1">${obj.fileName}</span>`;
      tags += `<a href="#" data-file="${obj.imageURL}">`;
      tags += `<i class="fa-solid fa-xmark"></i></a>`;
      tags += `</div></li>`;
    });

    uploadResult.insertAdjacentHTML("beforeend", tags);
  }

  fileInput.addEventListener("change", (e) => {
    const files = e.target.files;
    const formData = new FormData();

    for (let index = 0; index < files.length; index++) {
      if (checkExtension(files[index].name)) {
        formData.append("uploadFiles", files[index]);
      }
    }

    for (const value of formData.values()) {
      console.log("값: ", value);
    }

    fetch("/upload/uploadAjax", { method: "post", body: formData, headers: { "X-CSRF-TOKEN": csrfValue } })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        showUploadImages(data);
        document.querySelector("#fileInput").value = "";
      });
  });

  document.querySelector("#register-form").addEventListener("submit", (e) => {
    e.preventDefault();

    // 첨부파일 정보 수집
    const attachInfos = document.querySelectorAll(".uploadResult ul li");

    console.log(attachInfos);

    // 수집된 정보를 form 태그 자식으로 붙여넣기
    const form = e.target;
    let result = "";
    attachInfos.forEach((obj, idx) => {
      result += `<input type="hidden" value="${obj.dataset.path}" name="storeImgDtos[${idx}].stPath" />`;
      result += `<input type="hidden" value="${obj.dataset.uuid}" name="storeImgDtos[${idx}].stUuid" />`;
      result += `<input type="hidden" value="${obj.dataset.name}" name="storeImgDtos[${idx}].stImgName" />`;
    });

    form.insertAdjacentHTML("beforeend", result);

    console.log(form.innerHTML);

    form.submit();
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

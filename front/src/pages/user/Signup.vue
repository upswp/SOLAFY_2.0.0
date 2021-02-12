<template>
  <div>
    <div>
      <img src="~src/assets/user/signup.png" style=" width: 100%;" />
    </div>
    <div class="column items-center" style="height: 1900px ">
      <!-- 상단 text 위치 -->
      <div id="textInput" class="col-1">
        <p>
          여러분의 행복한 학습을 위한 <br />
          <strong>SOLAFY</strong> <br />
          함께 해주셔서 감사합니다.<br />
        </p>
      </div>
      <!-- 프로필 사진 위치 -->
      <div class="col-2">
        <div class="column items-center">
          <div v-if="imageUrl != null">
            <q-item-section>
              <q-avatar size="150px">
                <img :src="imageUrl" @click="onClickImageUpload" />
              </q-avatar>
            </q-item-section>
          </div>
          <div v-if="imageUrl == null">
            <q-item-section>
              <q-avatar size="150px">
                <img :src="defaultimg" @click="onClickImageUpload" />
              </q-avatar>
            </q-item-section>
          </div>
        </div>
        <input ref="imageInput" type="file" hidden @change="onChangeImages" />
        <q-btn id="insertFile" type="button" @click="onClickImageUpload"
          >프로필 등록하기</q-btn
        >
      </div>
      <!-- input -->
      <div class="col-4">
        <div class="insert">
          <q-input
            outlined
            label="name@example.com"
            placeholder="email"
            hint="SOLAFY ID로 사용될 주소입니다."
          />
          <br />
          <br />
          <q-input outlined label="비밀번호" />
          <br />
          <q-input outlined label="비밀번호 확인" />
          <br />
          <br />
          <q-input outlined label="이름" />
          <br />
          <br />
          <q-input
            outlined
            label="SSAFY 기수"
            hint="SSAFY 기수를 숫자로 입력해주세요. 4기 -> 4"
          />
          <br />
          <br />
          <q-input outlined label="휴대폰 번호" />
        </div>
      </div>
      <!-- 명찰사진 위치 -->
      <div class="col-3">
        <div class="column items-center">
          <div v-if="imagename != null">
            <q-item-section>
              <img
                id="imgsize"
                :src="imagename"
                @click="onClickNameImageUpload"
              />
            </q-item-section>
          </div>
          <div v-if="imagename == null">
            <q-item-section>
              <img
                id="imgsize"
                :src="defaultname"
                @click="onClickNameImageUpload"
              />
            </q-item-section>
          </div>
          <input
            ref="NameimageInput"
            type="file"
            hidden
            @change="onChangeNameImages"
          />
          <q-btn id="insertFile" type="button" @click="onClickNameImageUpload"
            >SSAFY 명찰 사진선택하기</q-btn
          >
          <div id="text">
            <p>
              인증받으실 SSAFY 명찰사진을 정면을 바라본 촬영 사진을 업로드
              해주십시오.
            </p>
          </div>

          <div id="text">
            <q-checkbox v-model="val"
              >SOLAFY 개인정보 동의처리 방침 에 따라 개인정보를 수집, 사용,
              처리하는데 동의합니다.</q-checkbox
            >
            <q-btn
              flat
              type="a"
              href=""
              target="_blank"
              color="primary"
              label="자세히보기"
            />
          </div>
        </div>
      </div>

      <div class="col-1">
        <q-btn id="loginbtn" label="Home" />
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "components/footer.vue";

export default {
  components: { Footer },
  name: "UserCreate",
  data() {
    return {
      //* 회원 정보
      email: "",
      password: "",
      // 비밀번호 확인
      pwdcnf: "",
      // 프로필 사진
      imageUrl: null,
      imagename: null,
      profileimg: null,
      defaultimg: require("src/assets/user/defaultimg.png"),
      defaultname: require("src/assets/user/defaultname.png"),
      // 명찰 사진
      nametagimg: { name: "" },
      val: false
    };
  },
  methods: {
    onRejected() {
      notify("red", "white", "warning", "사진 등록 실패");
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.imageUrl = URL.createObjectURL(file); // Create File URL
    },
    onClickNameImageUpload() {
      this.$refs.NameimageInput.click();
    },
    onChangeNameImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.imagename = URL.createObjectURL(file); // Create File URL
    }
  }
};
</script>

<style>
#loginbtn {
  width: 190px;
  height: 35px;
  font-size: 12px;
  color: white;
  align-content: center;
  background-color: #0094ff;
  margin-bottom: 50px;
}
#textInput {
  margin-top: 30px;
  margin-bottom: 30px;
  text-align: center;
  line-height: 50px;
  color: #000000;
}
#point {
  padding-top: 10px;
  padding-bottom: 10px;
}
#insertFile {
  width: 420px;
  height: 50px;
  margin-top: 20px;
  font-size: 14px;
  color: #b0b0b0;
}
.insert {
  width: 420px;
  height: 50px;
  margin-top: 10px;
  font-size: 14px;
  color: #b0b0b0;
}
#imgsize {
  width: 230px;
  height: 230px;
}
#text {
  margin-top: 20px;
  color: #b0b0b0;
}
</style>

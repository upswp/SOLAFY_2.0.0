<template>
  <div>
    <div>
      <img src="~src/assets/user/signup.png" style=" width: 100%;" />
    </div>
    <div class="column items-center" style="height: 2000px ">
      <!-- 상단 text 위치 -->
      <div class="col-2">
        <div id="textInput">
          <p>
            여러분의 행복한 학습을 위한 <br />
            <strong>SOLAFY</strong> <br />
            함께 해주셔서 감사합니다.<br />
          </p>
        </div>
      </div>
      <!-- 프로필 사진 위치 -->
      <div class="col-2">
        <div class="column items-center">
          <div v-if="signupData.imageUrl != null">
            <q-item-section>
              <q-avatar size="150px">
                <img :src="signupData.imageUrl" @click="onClickImageUpload" />
              </q-avatar>
            </q-item-section>
          </div>
          <div v-if="signupData.imageUrl == null">
            <q-item-section>
              <q-avatar size="150px">
                <img :src="signupData.defaultimg" @click="onClickImageUpload" />
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
            v-model="signupData.email"
            :error="!checkEmailForm"
          >
            <template v-slot:prepend>
              <q-btn
                flat
                color="primary"
                label="email 중복확인"
                @click="checkEmail()"
              />
            </template>
            <template v-slot:error>
              올바른 형식의 이메일이 아닙니다.
            </template>
          </q-input>

          <br />
          <br />
          <q-input
            outlined
            label="비밀번호"
            type="password"
            v-model="signupData.password"
            :error="!checkPasswordForm"
          >
            <template v-slot:error>
              영문, 숫자, 특수문자 포함 8 자리 이상입력해주세요!
            </template>
          </q-input>
          <br />
          <q-input
            outlined
            label="비밀번호 확인"
            type="password"
            v-model="signupData.passwordConfirm"
            :error="!checkPasswordConfirmationForm"
          >
            <template v-slot:error>
              비밀번호 확인이 실패하였습니다.
            </template>
          </q-input>
          <br />
          <br />
          <q-input outlined label="이름" v-model="signupData.name" />
          <br />
          <br />
          <q-input
            outlined
            label="SSAFY 기수"
            hint="SSAFY 기수를 숫자로 입력해주세요. 4기 -> 4"
            v-model="signupData.ssafy"
            suffix=""
          />
          <br />
          <br />
          <q-input outlined label="휴대폰 번호" v-model="signupData.phone" />
        </div>
      </div>
      <!-- 명찰사진 위치 -->
      <div class="col-3">
        <div class="column items-center">
          <div v-if="signupData.imagename != null">
            <q-item-section>
              <img
                id="imgsize"
                :src="signupData.imagename"
                @click="onClickNameImageUpload"
              />
            </q-item-section>
          </div>
          <div v-if="signupData.imagename == null">
            <q-item-section>
              <img
                id="imgsize"
                :src="signupData.defaultname"
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
            <q-checkbox v-model="signupData.val"
              >SOLAFY 개인정보 동의처리 방침 에 따라 개인정보를 수집, 사용,
              처리하는데 동의합니다.</q-checkbox
            >
            //TODO : 개인정보 동의 관련하여 페이지 생성하기.
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
        <UserButton @click="goSuccessSignup" label="회원가입"></UserButton>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "components/footer.vue";
import UserButton from "components/user/UserButton.vue";
import Axios from "axios";
import { notify } from "src/api/common.js";

export default {
  components: { Footer, UserButton },
  name: "UserCreate",
  data() {
    return {
      signupData: {
        //* 회원 정보
        // 회원 이메일
        email: "",
        // 이메일 체크
        checkEmail: false,
        // 회원 이름
        name: "",
        // 비밀번호
        password: "",
        // 비밀번호 확인
        passwordConfirm: "",
        // SSAFY 기수
        ssafy: 0,
        // 회원 전화번호
        phone: "",
        // 프로필 사진 주소
        imageUrl: null,
        // 명찰 사진 주소
        imagename: null,
        // 프로필 기본 이미지
        defaultimg: require("src/assets/user/defaultimg.png"),
        // 명찰 기본 이미지
        defaultname: require("src/assets/user/defaultname.png"),
        //개인정보 동의 여부
        val: false
      },
      error: {
        email: false,
        password: false,
        passwordConfirm: false
      },
      isSubmit: false
    };
  },
  computed: {
    /**
     * @Method설명 : 이메일 형식 검사(abcs@email.com)
     * @변경이력 :
     */
    checkEmailForm() {
      var flag = true;
      if (
        this.signupData.email.length > 0 &&
        !this.validEmail(this.signupData.email)
      ) {
        flag = false;
        this.checkEmilError();
      } else flag = true;
      return flag;
    },

    /**
     * @Method설명 : 비밀번호 형식 검사(영문, 숫자 포함 8자리 이상)
     * @변경이력 :
     */
    checkPasswordForm() {
      var flag = true;
      if (
        this.signupData.password.length > 0 &&
        this.signupData.password.length < 8
      ) {
        flag = false;
        this.checkPasswordError();
      } else if (
        this.signupData.password.length >= 8 &&
        !this.validPassword(this.signupData.password)
      ) {
        flag = false;
        this.checkPasswordError();
      } else flag = true;
      return flag;
    },
    /**
     * @Method설명 : 비밀번호 확인
     * @변경이력 :
     */
    checkPasswordConfirmationForm() {
      var flag = true;
      if (
        this.signupData.passwordConfirm.length > 0 &&
        this.signupData.password != this.signupData.passwordConfirm
      ) {
        flag = false;
        this.checkPasswordConfirmError();
      } else flag = true;
      return flag;
    }
  },
  methods: {
    checkEmilError() {
      this.error.email = true;
    },
    checkPasswordError() {
      this.error.password = true;
    },
    checkPasswordConfirmError() {
      this.error.passwordConfirm = true;
    },
    /**
     * @Method설명 : 정규식을 이용한 이메일 유효성 검사
     * @변경이력 :
     */
    validEmail(email) {
      // eslint-disable-next-line
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

      return re.test(email);
    },
    /**
     * @Method설명 : 정규식을 이용한 비밀번호 유효성 검사
     * @변경이력 :
     */
    validPassword(password) {
      var va = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
      return va.test(password);
    },

    // 사진등록관련 methods
    /**
     * @Method설명 : 사진 등록 실패시 경고창
     * @변경이력 :
     */
    onRejected() {
      notify("red", "white", "warning", "사진 등록 실패");
    },
    /**
     * @Method설명 : 클릭 시 이미지 불러오기
     * @변경이력 :
     */
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    /**
     * @Method설명 : 프로필 이미지를 불러와서 file url 저장하기
     * @변경이력 :
     */
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.signupData.imageUrl = URL.createObjectURL(file); // Create File URL
    },
    /**
     * @Method설명 : 클릭 시 이미지 불러오기
     * @변경이력 :
     */
    onClickNameImageUpload() {
      this.$refs.NameimageInput.click();
    },
    /**
     * @Method설명 : 명찰 이미지를 불러와서 file url 저장하기
     * @변경이력 :
     */
    onChangeNameImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.signupData.imagename = URL.createObjectURL(file); // Create File URL
    },
    /**
     * @Method설명 : 이메일 중복 체크
     * @변경이력 :
     */
    checkEmail() {
      Axios.get("members/email" + this.signupData.email)
        .then(response => {
          if (response.data == "success") {
            this.signupData.checkEmail = true;
            notify("green", "white", "check", "사용 가능한 이메일입니다");
          } else {
            this.signupData.email = "";
            notify("red-6", "white", "warning", "사용할 수 없는 이메일입니다");
          }
        })
        .catch(error => {
          console.log(error);
          notify("red", "white", "error", "중복 검사 중 오류 발생");
        });
    },

    /**
     * @Method설명 :[POST] 회원가입 성공시 페이지 이동
     * @변경이력 :
     */
    goSuccessSignup() {
      if (this.error.email) {
        notify("red", "white", "error", "이메일을 다시 확인해 주세요.");
        this.isSubmit = false;
      } else if (!this.signupData.checkEmail) {
        notify(
          "red",
          "white",
          "error",
          "이메일 중복검사를 다시 확인해 주세요."
        );
        this.isSubmit = false;
      } else if (this.error.password || this.error.passwordConfirm) {
        notify("red", "white", "error", "비밀번호를 다시 확인해 주세요.");
        this.isSubmit = false;
      } else if (!this.signupData.val) {
        notify("red", "white", "error", "개인정보 동의를 다시 확인해주세요.");
        this.isSubmit = false;
      } else if (this.signupData.name == null) {
        notify("red", "white", "error", "이름 입력 해주세요!");
        this.isSubmit = false;
      } else if (this.signupData.ssafy == null) {
        notify("red", "white", "error", "기수 입력 해주세요!");
        this.isSubmit = false;
      } else if (this.signupData.phone == null) {
        notify("red", "white", "error", "휴대폰 번호 입력 해주세요!");
        this.isSubmit = false;
      } else if (this.signupData.imagename == null) {
        notify("red", "white", "error", "명찰사진을 입력 해주세요!");
        this.isSubmit = false;
      } else {
        this.isSubmit = true;
      }
      if (this.isSubmit) {
        Axios.post("members/signup" + this.signupData)
          .then(response => {
            notify("positive", "white", "done", "회원가입 신청 완료");
            this.$router.push({
              name: "SuccessSignup"
            });
          })
          .catch(error => {
            console.log(error);
            notify("red", "white", "error", "회원가입 신청 실패");
          });
      } else {
        notify("red", "white", "error", "회원가입 정보를 확인해주세요.");
      }
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

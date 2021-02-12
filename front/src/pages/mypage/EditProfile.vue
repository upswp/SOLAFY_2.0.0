<template>
  <div class="q-pa-md">
    <div class="row justify-center" style="margin: 20px;">
      <div class="col-3"></div>
      <div class="col-6" style="max-width: 420px">
        <div class="row">
          <q-file
            outlined
            bottom-slots
            v-model="profileImage"
            label="프로필 사진"
            counter
            max-files="1"
            accept="image/*"
          >
            <template v-slot:before>
              <q-avatar size="60px">
                <img src="https://i.imgur.com/XplLIk9.png" />
              </q-avatar>
            </template>

            <template v-slot:append>
              <q-icon
                v-if="profileImage !== null"
                name="close"
                @click.stop="profileImage = null"
                class="cursor-pointer"
              />
              <q-icon name="search" @click.stop />
            </template>
          </q-file>
        </div>

        <q-input
          outlined
          v-model="email"
          label="이메일"
          style="margin-top: 20px"
        />

        <div class="row">
          <q-btn
            outline
            size="14px"
            label="비밀번호 변경하기"
            @click="$router.push('/mypage/changepassword')"
            style="margin-top: 20px; padding: 6px 10px"
          />
        </div>

        <q-input
          outlined
          v-model="nickname"
          label="닉네임"
          style="margin-top: 20px"
        />

        <q-input
          outlined
          v-model="statusMessage"
          label="상태메세지"
          style="margin-top: 20px"
        />

        <q-input
          outlined
          v-model="github"
          label="github"
          style="margin-top: 20px"
        />

        <q-input
          outlined
          v-model="blog"
          label="blog"
          style="margin-top: 20px"
        />

        <div class="row justify-center" style="margin: 40px 0 0">
          <q-btn
            dense
            outline
            size="14px"
            label="완료"
            style="padding: 3px 30px; margin: 0 10px"
          />
          <q-btn
            dense
            outline
            size="14px"
            label="취소"
            @click="$router.push('/mypage/profile')"
            style="padding: 3px 30px;  margin: 0 10px; "
          />
        </div>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      profileImage: null,
      profileImageUrl: null,
      email: "",
      nickname: "",
      statusMessage: "",
      github: "",
      blog: ""
    };
  },
  watch: {
    profileImage: function() {
      console.log(this.profileImage);
    }
  },
  methods: {
    pickFile(event) {
      console.log("dd");
      console.log(this.profileImage);
    }
  }
};
</script>

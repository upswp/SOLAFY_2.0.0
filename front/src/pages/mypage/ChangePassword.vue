<template>
  <div class="q-pa-md">
    <div class="row justify-center" style="margin: 20px;">
      <div class="col-3"></div>
      <div class="col-6" style="max-width: 420px">
        <q-input
          outlined
          type="password"
          v-model="beforepw"
          label="현재 비밀번호"
          style="margin-top: 20px"
        />

        <q-input
          outlined
          type="password"
          v-model="afterpw"
          label="새로운 비밀번호"
          style="margin-top: 40px"
        />

        <q-input
          outlined
          type="password"
          v-model="confirmpw"
          label="새로운 비밀번호 확인"
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
      beforepw: "",
      afterpw: "",
      confirmpw: ""
    };
  },

  methods: {}
};
</script>

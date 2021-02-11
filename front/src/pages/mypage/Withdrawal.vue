<template>
  <div class="q-pa-md">
    <div class="row justify-center" style="margin: 20px;">
      <div class="col-3"></div>
      <div class="col-6" style="max-width: 420px">
        <q-input
          outlined
          type="password"
          v-model="password"
          label="비밀번호 확인"
          style="margin-top: 20px"
        />

        <div class="row justify-center" style="margin: 40px 0 0">
          <q-btn
            outline
            size="14px"
            label="탈퇴하기"
            style="padding: 6px 10px; margin: 0 10px"
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
      password: ""
    };
  },
  methods: {}
};
</script>

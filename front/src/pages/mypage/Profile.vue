<template>
  <div class="q-pa-md">
    <div class="row justify-center" style="margin: 20px;">
      <div class="col-3" align="right">
        <q-img
          src="https://i.imgur.com/XplLIk9.png"
          spinner-color="red"
          style="height: 70px; max-width: 70px"
        />
      </div>
      <div class="col-9">
        <div class="row" style="margin: 10px">
          <span style="margin-left: 30px; margin-top: 3px">alice</span>
          <span style="margin-left: 30px; margin-top: 3px">|</span>
          <span style="margin-left: 30px; margin-top: 3px">SSAFY 4기</span>
          <span style="margin-left: 30px; margin-top: 3px">|</span>
          <span style="margin-left: 30px; margin-top: 3px">platinum</span>
          <q-btn
            flat
            dense
            icon="settings"
            size="12px"
            @click="$router.push('/mypage/edit')"
            style="margin-left: 30px"
          />
        </div>
        <div class="row" style="margin: 10px">
          <span style="margin-left: 30px; margin-top: 3px"
            >SSAFY 백엔드 장인을 꿈 꿉니다</span
          >
          <q-btn
            flat
            dense
            icon="cloud"
            size="12px"
            style="margin-left: 20px"
          />
          <q-btn
            flat
            dense
            icon="cloud"
            size="12px"
            style="margin-left: 20px"
          />
        </div>
      </div>
    </div>
    <div class="row justify-center" style="margin-top: 50px;">
      <div class="col-2"></div>
      <div class="col-8">
        <q-btn-dropdown
          style=" background-color: #6E9488; color:white; width:120px "
          :label="dropdownlabel"
        >
          <q-list>
            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>전체</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>알고리즘</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>Vue</q-item-label>
              </q-item-section>
            </q-item>

            <q-item clickable v-close-popup @click="onItemClick">
              <q-item-section>
                <q-item-label>Spring</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dropdownlabel: "전체"
    };
  },
  methods: {
    onItemClick(item) {
      console.log(item);
      this.dropdownlabel = item.target.innerText;
    }
  }
};
</script>

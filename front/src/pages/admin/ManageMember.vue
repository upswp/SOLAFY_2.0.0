<template>
  <div class="body">
    <div class="text">
      <p style="font-size:26px">회원 관리 페이지입니다</p>
      <p style="font-size:12px">전체 회원 리스트를 알려드리겠습니다</p>
    </div>
    <div class="bg-grey-4">
      <div class="content">
        <div class="row topBar">
          <div class="col">
            <MenuButton
              @click="clickMenu('전체')"
              :active="activeBtn === '전체'"
              label="전체 리스트"
              padding="85px"
            />
            <MenuButton
              @click="clickMenu('승인대기')"
              :active="activeBtn === '승인대기'"
              label="승인대기 리스트"
              padding="70px"
            />
          </div>
          <div class="col" style="float:right">
            <q-input
              class="search"
              borderless
              dense
              bg-color="white"
              v-model="keyword"
              input-class="text-right"
            >
              <template v-slot:prepend>
                <q-select
                  borderless
                  dense
                  v-model="type"
                  :options="types"
                  style="min-width: 120px; padding-left:15px"
                />
                <q-separator />
              </template>
              <template v-slot:append>
                <q-icon
                  v-if="keyword === ''"
                  name="search"
                  style="padding-right:15px"
                />
                <q-icon
                  v-else
                  name="clear"
                  class="cursor-pointer"
                  @click="keyword = ''"
                  style="padding-right:15px"
                />
              </template>
            </q-input>
          </div>
        </div>
        <div class="table" style="margin-bottom:10px;">
          <q-table
            ref="table"
            :data="tableData"
            :columns="columns"
            row-key="no"
            :pagination.sync="pagination"
            hide-pagination
            :selection="tableSelect"
            :selected.sync="selected"
          >
            <template v-slot:body-cell-비고="props">
              <q-td :props="props">
                <div>
                  <q-chip
                    v-if="activeBtn == '승인대기'"
                    clickable
                    align="center"
                    class="submit-btn"
                    label="승인"
                  />
                  <q-chip
                    clickable
                    align="center"
                    class="detail-btn"
                    label="상세보기"
                    @click="dialog = true"
                  />
                </div>
              </q-td>
            </template>
            <template v-slot:bottom-row>
              <q-td colspan="100%">
                <div class="row justify-center">
                  <q-pagination
                    v-model="pagination.page"
                    color="grey-8"
                    :max="pagesNumber"
                    size="sm"
                  />
                </div>
              </q-td>
            </template>
          </q-table>
        </div>
      </div>
    </div>
    <!-- dialog -->
    <q-dialog v-model="dialog" persistent>
      <q-card style="width: 500px; max-width: 70vw;">
        <q-bar>
          <q-icon name="laptop_chromebook" />
          <div>회원정보</div>
          <q-space />
          <q-btn dense flat icon="close" v-close-popup>
            <q-tooltip>Close</q-tooltip>
          </q-btn>
        </q-bar>

        <q-card-section horizontal class="justify-center">
          <q-card-section>
            <q-markup-table flat separator=none> 
              <tbody>
                <DialogContent v-if="activeBtn == '승인대기'" title="명찰 사진" img="defaultname.png" />
                <DialogContent title="이메일" content="lay0711@naver.com" />
                <DialogContent title="이름" content="박싸피" />
                <DialogContent title="기수" content="4기" />
                <DialogContent title="전화번호" content="010-1234-5678" />
                <DialogContent title="가입일자" content="2021.01.21" />
              </tbody>
            </q-markup-table>
          </q-card-section>
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>
<script>
import MenuButton from "components/MenuButton.vue";
import DialogContent from "components/DialogContent.vue";

export default {
  name: "ManageMember",
  components: { MenuButton, DialogContent },
  data() {
    return {
      activeBtn: "",
      dialog: false,
      keyword: "",
      type: "이름",
      types: ["이름", "아이디", "기수"],
      tableSelect: "none",
      selected: [],
      lastIndex: null,
      columns: [
        {
          name: "회원번호",
          required: true,
          label: "회원번호",
          align: "center",
          field: "no"
        },
        {
          name: "회원이름",
          align: "center",
          label: "회원이름",
          field: "name"
        },
        {
          name: "회원아이디",
          align: "center",
          label: "회원아이디",
          field: "email"
        },
        {
          name: "ssafy기수",
          align: "center",
          label: "ssafy기수",
          field: "ssafy"
        },
        {
          name: "회원가입일자",
          align: "center",
          label: "회원가입일자",
          field: "createDate"
        },
        { name: "비고", align: "center", label: "비고" }
      ],
      tableData: [],
      // 테이블 샘플 데이터
      memberData: [
        {
          no: 1,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 2,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 3,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 4,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 5,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 6,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 7,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 8,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 9,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 10,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 11,
          name: "승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        }
      ],
      unapprovedData: [
        {
          no: 1,
          name: "미승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 2,
          name: "미승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 3,
          name: "미승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        },
        {
          no: 4,
          name: "미승인된회원",
          email: "lay0711@naver.com",
          ssafy: "4기",
          createDate: "2021.02.12"
        }
      ],
      pagination: {
        sortBy: "desc",
        descending: false,
        page: 1,
        rowsPerPage: 10
      }
    };
  },
  methods: {
    clickMenu(menuName) {
      this.activeBtn = menuName;
      if (this.activeBtn == "전체") {
        this.tableSelect = "none";
        this.selected = [];
        this.tableData = this.memberData;
      } else {
        this.tableSelect = "multiple";
        this.tableData = this.unapprovedData;
      }
    }
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.tableData.length / this.pagination.rowsPerPage);
    }
  },
  created() {
    // 테이블 데이터 초기화
    this.activeBtn = "전체";
    this.tableData = this.memberData;
  }
};
</script>
<style lang="sass" scoped>
.body
    width:100%
.text
    text-align: center
    margin-top: 20px
    margin-bottom: 20px
.content
    padding: 22px
.topBar
    line-height: 35px
    margin-bottom: 22px
.search
    float: right
    max-width: 420px
    height:35px
    border: 0
    border-radius: 22px
    overflow: hidden //radius적용 시 필수
.submit-btn
    width: 120px
    height: 22px
    font-size: 12px
    background: #ff8c8c
    padding-left: 47px
.detail-btn
    width: 120px
    height: 22px
    font-size: 12px
    background: #bae2ff
    padding-left: 35px
</style>

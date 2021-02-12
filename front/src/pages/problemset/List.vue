<template>
  <div class="q-pa-md">
    <q-table
      :data="data"
      :columns="columns"
      row-key="name"
      :selected-rows-label="getSelectedString"
      selection="multiple"
      :selected.sync="selected"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      selected: [],
      columns: [
        {
          name: "bookmark",
          align: "center",
          field: "bookmark",
          sortable: true
        },
        {
          name: "no",
          align: "center",
          label: "문제집 번호",
          field: "problemSetNo",
          sortable: true
        },
        {
          name: "category",
          align: "center",
          label: "대분류",
          field: "category",
          sortable: true
        },
        {
          name: "title",
          required: true,
          label: "제목",
          align: "left",
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        {
          name: "writter",
          align: "center",
          label: "작성자",
          field: "writter",
          sortable: true
        },
        {
          name: "writedate",
          align: "center",
          label: "작성일",
          field: "writedate",
          sortable: true
        },
        {
          name: "view",
          align: "center",
          label: "조회수",
          field: "view",
          sortable: true
        }
      ],
      data: []
    };
  },
  methods: {
    getSelectedString() {
      return this.selected.length === 0
        ? ""
        : `${this.selected.length} record${
            this.selected.length > 1 ? "s" : ""
          } selected of ${this.data.length}`;
    }
  }
};
</script>

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
          name: "no",
          align: "center",
          label: "글번호",
          field: "articleNo",
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
          name: "writeDate",
          align: "center",
          label: "작성일",
          field: "writeDate",
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

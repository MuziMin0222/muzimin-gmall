<template>
  <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
export default {
  data () {
    return {
      data: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/category/list/tree'),
        method: 'get'
      }).then(data => {
        console.log('成功获取到数据。。。', data)
      })
    }
  },
  created () {
    this.getMenus()
  }
}
</script>

<style>

</style>
